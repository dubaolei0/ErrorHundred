package dubaolei.requestForViolence;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName IpUrlLimitInterceptor.java
 * @Description 拦截器 + Redis分布式锁实现 接口恶意刷新和暴力请求
 * 通过intercept和redis针对url+ip在一定时间内访问的次数来将ip禁用，可以根据自己的需求进行相应的修改，来打打自己的目的
 * @createTime 2022年06月22日 15:21:00
 */
public class IpUrlLimitInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(IpUrlLimitInterceptor.class);

    private RedisUtil getRedisUtil() {
        return SpringContextUtil.getBean("redisUtil");
    }

    private static final String LOCK_IP_URL_KEY = "lock_ip_";

    private static final String IP_URL_REQ_TIME = "ip_url_times_";

    private static final long LIMIT_TIMES = 5;

    private static final int IP_LOCK_TIME = 60;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("request请求地址uri={},ip={}", httpServletRequest.getRequestURI(), IpAdrressUtil.getIpAdrress(httpServletRequest));
        //  判断ip是否被禁用 是否存在key:  LOCK_IP_URL_KEY + ip
        if (ipIsLock(IpAdrressUtil.getIpAdrress(httpServletRequest))) {
            log.info("ip访问被禁止={}", IpAdrressUtil.getIpAdrress(httpServletRequest));
            ApiResult result = new ApiResult("LOCK_IP");
            returnJson(httpServletResponse, JSON.toJSONString(result));
            return false;
        }
        //判断IP+URL次数,，根据IP+URL获取value(次数) ，如果次数超过就给IP加锁，没超过就 IP+URL+1
        if (!addRequestTime(IpAdrressUtil.getIpAdrress(httpServletRequest), httpServletRequest.getRequestURI())) {
            ApiResult result = new ApiResult("LOCK_IP");
            returnJson(httpServletResponse, JSON.toJSONString(result));
            return false;
        }
        return true;
    }

    /**
     * @param ip
     * @return java.lang.Boolean
     * @Description: 判断ip是否被禁用
     * @author: shuyu.wang
     * @date: 2019-10-12 13:08
     */
    private Boolean ipIsLock(String ip) {
        RedisUtil redisUtil = getRedisUtil();
        if (redisUtil.hasKey(LOCK_IP_URL_KEY + ip)) {
            return true;
        }
        return false;
    }

    /**
     * @param ip
     * @param uri
     * @return java.lang.Boolean
     * @Description: 记录请求次数
     * @author: shuyu.wang
     * @date: 2019-10-12 17:18
     */
    private Boolean addRequestTime(String ip, String uri) {
        String key = IP_URL_REQ_TIME + ip + uri;
        RedisUtil redisUtil = getRedisUtil();
        if (redisUtil.hasKey(key)) {
            long time = redisUtil.incr(key, (long) 1);
            // 如果超过次数，以IP为key加锁,value为IP
            if (time >= LIMIT_TIMES) {
                redisUtil.getLock(LOCK_IP_URL_KEY + ip, ip, IP_LOCK_TIME);
                return false;
            }
        } else {
            //没有超过次数，以IP+Uri为key,value为次数
            redisUtil.getLock(key, (long) 1, 1);
        }
        return true;
    }

    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            log.error("LoginInterceptor response error ---> {}", e.getMessage(), e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
