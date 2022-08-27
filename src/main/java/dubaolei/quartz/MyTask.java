package dubaolei.quartz;

import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定测试定时任务执行类
 *
 * @Author: xin.qin
 * @Date: 2022/07/12 14:57
 */

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Component
public class MyTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobKey jobKey = context.getJobDetail().getKey();
        JobDataMap map = context.getJobDetail().getJobDataMap();
        String userId = map.getString("userId");
        System.out.println("开始执行任务: " + jobKey + ", userId: " + userId + " executing at " + new Date());
    }
}
