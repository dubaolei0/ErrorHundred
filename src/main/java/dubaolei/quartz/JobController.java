package dubaolei.quartz;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 测试 Controller
 *
 * @Author: xin.qin
 * @Date: 2022/07/12 14:57
 */
@RestController
@RequestMapping("/JobController")
public class JobController{

    @Autowired
    private QuartzJobService quartzJobService;

    @GetMapping("/startSimpleJobOnly")
    public String startSimpleJobOnly(String time) {
        QuartzBean quartzBean = new QuartzBean();
        quartzBean.setJobClass("dubaolei.quartz.MyTask");
        quartzBean.setJobName("job2");
        JobDataMap map = new JobDataMap();
        map.put("userId", "789987");
        quartzBean.setJobDataMap(map);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        quartzBean.setStartTime(date);
//        quartzBean.setInterval(1);
//        quartzBean.setEndTime(DateUtils.addMinutes(new Date(), 10));
        try {
            quartzJobService.createScheduleJobSimple(quartzBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "startJob Success!";
    }

    @GetMapping("/updateSimpleJobOnly")
    public String updateSimpleJobOnly(String userId, String time) {
        QuartzBean quartzBean = new QuartzBean();
        quartzBean.setJobClass("dubaolei.quartz.MyTask");
        quartzBean.setJobName("job2");
        JobDataMap map = new JobDataMap();
        map.put("userId", userId);
        quartzBean.setJobDataMap(map);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        quartzBean.setStartTime(date);

        //quartzBean.setInterval(1);
        //quartzBean.setEndTime(DateUtils.addMinutes(now, 1));
        try {
            quartzJobService.updateScheduleJobSimple(quartzBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "updateJob Success!";
    }

    @GetMapping("/corn")
    public String createJob() {
        try {
            QuartzBean quartzBean = new QuartzBean();
            //进行测试所以写死
            quartzBean.setJobClass("dubaolei.quartz.MyTask");
            quartzBean.setJobName("job1");
            quartzBean.setCronExpression("*/5 * * * * ?");
            JobDataMap map = new JobDataMap();
            map.put("userId", "111");
            quartzBean.setJobDataMap(map);
            quartzJobService.createScheduleJobCron(quartzBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "创建成功";
    }


    public String pauseJob(String jobName, String jobGroup) {
        try {
            quartzJobService.pauseScheduleJob(jobName, StringUtils.isNotBlank(jobGroup) ? jobGroup : null);
        } catch (Exception e) {
            return "暂停失败";
        }
        return "暂停成功";
    }

    public String resume(String jobName, String jobGroup) {
        try {
            quartzJobService.resumeScheduleJob(jobName,StringUtils.isNotBlank(jobGroup) ? jobGroup : null);
        } catch (Exception e) {
            return "启动失败";
        }
        return "启动成功";
    }

    public String delete(String jobName, String jobGroup) {
        try {
            quartzJobService.deleteScheduleJob(jobName,StringUtils.isNotBlank(jobGroup) ? jobGroup : null);
        } catch (Exception e) {
            return "删除失败";
        }
        return "删除成功";
    }

    public String check(String jobName, String jobGroup) {
        try {
            if (quartzJobService.checkExistsScheduleJob(jobName,StringUtils.isNotBlank(jobGroup) ? jobGroup : null)) {
                return "存在定时任务：" + jobName;
            } else {
                return "不存在定时任务：" + jobName;
            }
        } catch (Exception e) {
            return "查询任务失败";
        }
    }

    public String status(String jobName, String jobGroup) {
        try {
            return quartzJobService.getScheduleJobStatus(jobName,StringUtils.isNotBlank(jobGroup) ? jobGroup : null);
        } catch (Exception e) {
            return "获取状态失败";
        }
        //return "获取状态成功";
    }

}
