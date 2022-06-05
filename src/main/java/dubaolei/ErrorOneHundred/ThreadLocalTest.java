package dubaolei.ErrorOneHundred;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName ThreadLocal.java
 * @Description 1、没有意识到线程重用导致用户信息错乱的 Bug
 * @createTime 2022年05月22日 17:18:00
 */
@RestController
public class ThreadLocalTest{

    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);
    @GetMapping("wrong")
    public Map wrong(@RequestParam("userId") Integer userId) {

        //设置用户信息之前先查询一次ThreadLocal中的用户信息

        String before  = Thread.currentThread().getName() + ":" + currentUser.get();

        //设置用户信息到ThreadLocal

        currentUser.set(userId);

        //设置用户信息之后再查询一次ThreadLocal中的用户信息

        String after  = Thread.currentThread().getName() + ":" + currentUser.get();

        //汇总输出两次查询结果

        Map result = new HashMap();

        result.put("before", before);

        result.put("after", after);

        return result;

    }

    @GetMapping("right")
    public Map right(@RequestParam("userId") Integer userId) {

        String before  = Thread.currentThread().getName() + ":" + currentUser.get();

        currentUser.set(userId);

        try {

            String after = Thread.currentThread().getName() + ":" + currentUser.get();

            Map result = new HashMap();

            result.put("before", before);

            result.put("after", after);

            return result;

        } finally {

            //在finally代码块中删除ThreadLocal中的数据，确保数据不串

            currentUser.remove();

        }

    }

}
