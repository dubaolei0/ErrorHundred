package dubaolei.quartz;

import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.quartz.Scheduler;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * job 实现 （基本不用动）
 * @Author: xin.qin
 * @Date: 2022/07/12 14:38
 */
@Service
public class QuartzJobService {

    @Autowired
    private Scheduler scheduler;

    /**
     * 创建定时任务Simple
     * quartzBean.getInterval()==null表示单次提醒，
     * 否则循环提醒（quartzBean.getEndTime()!=null）
     *
     * @param quartzBean
     */
    public void createScheduleJobSimple(QuartzBean quartzBean) throws Exception {
        //获取到定时任务的执行类  必须是类的绝对路径名称
        //定时任务类需要是job类的具体实现 QuartzJobBean是job的抽象类。
        Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(quartzBean.getJobClass());
        // 构建定时任务信息
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(quartzBean.getJobName(), StringUtils.isNotBlank(quartzBean.getJobGroup()) ? quartzBean.getJobGroup() : null)
                .setJobData(quartzBean.getJobDataMap())
                .build();
        // 设置定时任务执行方式
        SimpleScheduleBuilder simpleScheduleBuilder = null;
        if (quartzBean.getInterval() == null) { //单次
            simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        } else { //循环
            simpleScheduleBuilder = SimpleScheduleBuilder.repeatMinutelyForever(quartzBean.getInterval());
        }
        // 构建触发器trigger
        Trigger trigger = null;
        if (quartzBean.getInterval() == null) { //单次
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(quartzBean.getJobName(), StringUtils.isNotBlank(quartzBean.getJobGroup()) ? quartzBean.getJobGroup() : null)
                    .withSchedule(simpleScheduleBuilder)
                    .startAt(quartzBean.getStartTime())
                    .build();
        } else { //循环
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(quartzBean.getJobName(), StringUtils.isNotBlank(quartzBean.getJobGroup()) ? quartzBean.getJobGroup() : null)
                    .withSchedule(simpleScheduleBuilder)
                    .startAt(quartzBean.getStartTime())
                    .endAt(quartzBean.getEndTime())
                    .build();
        }
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 创建定时任务Simple可修改JobDataMap
     * quartzBean.getInterval()==null表示单次提醒，
     * 否则循环提醒（quartzBean.getEndTime()!=null）
     *
     * @param quartzBean
     */
    public void createScheduleJobSimpleData(QuartzBean quartzBean) throws Exception {
        //获取到定时任务的执行类  必须是类的绝对路径名称
        //定时任务类需要是job类的具体实现 QuartzJobBean是job的抽象类。
        Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(quartzBean.getJobClass());
        // 构建定时任务信息
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(quartzBean.getJobName(), StringUtils.isNotBlank(quartzBean.getJobGroup()) ? quartzBean.getJobGroup() : null)
                .setJobData(quartzBean.getJobDataMap())
                .storeDurably(true)//数据持久化，默认不是持久化，没有设置修改JobDataMap会报错，适合循环执行任务
                .build();
        // 设置定时任务执行方式
        SimpleScheduleBuilder simpleScheduleBuilder = null;
        if (quartzBean.getInterval() == null) { //单次
            simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        } else { //循环
            simpleScheduleBuilder = SimpleScheduleBuilder.repeatMinutelyForever(quartzBean.getInterval());
        }
        // 构建触发器trigger
        Trigger trigger = null;
        if (quartzBean.getInterval() == null) { //单次
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(quartzBean.getJobName(), StringUtils.isNotBlank(quartzBean.getJobGroup()) ? quartzBean.getJobGroup() : null)
                    .withSchedule(simpleScheduleBuilder)
                    .startAt(quartzBean.getStartTime())
                    .build();
        } else { //循环
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(quartzBean.getJobName(), StringUtils.isNotBlank(quartzBean.getJobGroup()) ? quartzBean.getJobGroup() : null)
                    .withSchedule(simpleScheduleBuilder)
                    .startAt(quartzBean.getStartTime())
                    .endAt(quartzBean.getEndTime())
                    .build();
        }
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 创建定时任务Cron
     * 定时任务创建之后默认启动状态
     *
     * @param quartzBean 定时任务信息类
     * @throws Exception
     */
    public void createScheduleJobCron(QuartzBean quartzBean) throws Exception {
        //获取到定时任务的执行类  必须是类的绝对路径名称
        //定时任务类需要是job类的具体实现 QuartzJobBean是job的抽象类。
        Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(quartzBean.getJobClass());
        // 构建定时任务信息
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(quartzBean.getJobName(), StringUtils.isNotBlank(quartzBean.getJobGroup()) ? quartzBean.getJobGroup() : null).setJobData(quartzBean.getJobDataMap()).build();
        // 设置定时任务执行方式
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzBean.getCronExpression());
        // 构建触发器trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(quartzBean.getJobName()).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 根据任务名称暂停定时任务
     *
     * @param jobName  定时任务名称
     * @param jobGroup 任务组（没有分组传值null）
     * @throws Exception
     */
    public void pauseScheduleJob(String jobName, String jobGroup) throws Exception {
        JobKey jobKey = JobKey.jobKey(jobName, StringUtils.isNotBlank(jobGroup) ? jobGroup : null);
        scheduler.pauseJob(jobKey);
    }

    /**
     * 根据任务名称恢复定时任务
     *
     * @param jobName  定时任务名
     * @param jobGroup 任务组（没有分组传值null）
     * @throws SchedulerException
     */
    public void resumeScheduleJob(String jobName, String jobGroup) throws Exception {
        JobKey jobKey = JobKey.jobKey(jobName, StringUtils.isNotBlank(jobGroup) ? jobGroup : null);
        scheduler.resumeJob(jobKey);
    }

    /**
     * 根据任务名称立即运行一次定时任务
     *
     * @param jobName  定时任务名称
     * @param jobGroup 任务组（没有分组传值null）
     * @throws SchedulerException
     */
    public void runOnce(String jobName, String jobGroup) throws Exception {
        JobKey jobKey = JobKey.jobKey(jobName, StringUtils.isNotBlank(jobGroup) ? jobGroup : null);
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新定时任务Simple
     * 注意，此方法在 create创建的时间还未执行时起作用，已执行过了不好使因为已经消费掉了
     * @param quartzBean 定时任务信息类
     * @throws SchedulerException
     */
    public void updateScheduleJobSimple(QuartzBean quartzBean) throws Exception {
        //获取到对应任务的触发器
        TriggerKey triggerKey = TriggerKey.triggerKey(quartzBean.getJobName(), StringUtils.isNotBlank(quartzBean.getJobGroup()) ? quartzBean.getJobGroup() : null);
        // 设置定时任务执行方式
        SimpleScheduleBuilder simpleScheduleBuilder = null;
        if (quartzBean.getInterval() == null) { //单次
            simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        } else { //循环
            simpleScheduleBuilder = SimpleScheduleBuilder.repeatMinutelyForever(quartzBean.getInterval());
        }
        // 构建触发器trigger
        Trigger trigger = null;
        if (quartzBean.getInterval() == null) { //单次
            trigger = TriggerBuilder.newTrigger()
                    .withIdentity(quartzBean.getJobName(), StringUtils.isNotBlank(quartzBean.getJobGroup()) ? quartzBean.getJobGroup() : null)
                    .withSchedule(simpleScheduleBuilder)
                    .startAt(quartzBean.getStartTime())
                    .build();
        } else { //循环
            TriggerBuilder.newTrigger()
                    .withIdentity(quartzBean.getJobName(), StringUtils.isNotBlank(quartzBean.getJobGroup()) ? quartzBean.getJobGroup() : null)
                    .withSchedule(simpleScheduleBuilder)
                    .startAt(quartzBean.getStartTime())
                    .endAt(quartzBean.getEndTime())
                    .build();
        }

        //重置对应的job
        scheduler.rescheduleJob(triggerKey, trigger);
        System.out.println("update设置成功");
    }

    /**
     * 更新定时任务Simple
     *
     * @param quartzBean 定时任务信息类
     * @throws SchedulerException
     */
    public void updateScheduleJobSimpleJobDataMap(QuartzBean quartzBean) throws Exception {
        JobKey jobKey = JobKey.jobKey(quartzBean.getJobName(), StringUtils.isNotBlank(quartzBean.getJobGroup()) ? quartzBean.getJobGroup() : null);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        jobDetail.getJobDataMap().put("userId", quartzBean.getJobDataMap().get("userId"));
        //为此，作业需要是持久的，否则将抛出异常，默认情况下新作业是不持久的，因此需要通过在“JobBuilder”上调用“.storeDurably(true)”来设置它
        scheduler.addJob(jobDetail, true);
    }
    /**
     * 更新定时任务Cron
     *
     * @param quartzBean 定时任务信息类
     * @throws SchedulerException
     */
    public void updateScheduleJobCron(QuartzBean quartzBean) throws Exception {
        //获取到对应任务的触发器
        TriggerKey triggerKey = TriggerKey.triggerKey(quartzBean.getJobName());
        //设置定时任务执行方式
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzBean.getCronExpression());
        //重新构建任务的触发器trigger
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        //重置对应的job
        scheduler.rescheduleJob(triggerKey, trigger);
    }

    /**
     * 根据定时任务名称从调度器当中删除定时任务
     *
     * @param jobName  定时任务名称
     * @param jobGroup 任务组（没有分组传值null）
     * @throws SchedulerException
     */
    public void deleteScheduleJob(String jobName, String jobGroup) throws Exception {
        JobKey jobKey = JobKey.jobKey(jobName, StringUtils.isNotBlank(jobGroup) ? jobGroup : null);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 获取任务状态
     *
     * @param jobName
     * @param jobGroup 任务组（没有分组传值null）
     * @return (" BLOCKED ", " 阻塞 ");
     * ("COMPLETE", "完成");
     * ("ERROR", "出错");
     * ("NONE", "不存在");
     * ("NORMAL", "正常");
     * ("PAUSED", "暂停");
     */
    public String getScheduleJobStatus(String jobName, String jobGroup) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, StringUtils.isNotBlank(jobGroup) ? jobGroup : null);
        Trigger.TriggerState state = scheduler.getTriggerState(triggerKey);
        return state.name();
    }

    /**
     * 根据定时任务名称来判断任务是否存在
     *
     * @param jobName  定时任务名称
     * @param jobGroup 任务组（没有分组传值null）
     * @throws SchedulerException
     */
    public Boolean checkExistsScheduleJob(String jobName, String jobGroup) throws Exception {
        JobKey jobKey = JobKey.jobKey(jobName, StringUtils.isNotBlank(jobGroup) ? jobGroup : null);
        return scheduler.checkExists(jobKey);
    }

    /**
     * 根据任务組刪除定時任务
     *
     * @param jobGroup 任务组
     * @throws SchedulerException
     */
    public Boolean deleteGroupJob(String jobGroup) throws Exception {
        GroupMatcher<JobKey> matcher = GroupMatcher.groupEquals(jobGroup);
        Set<JobKey> jobkeySet = scheduler.getJobKeys(matcher);
        List<JobKey> jobkeyList = new ArrayList<JobKey>();
        jobkeyList.addAll(jobkeySet);
        return scheduler.deleteJobs(jobkeyList);
    }

    /**
     * 根据任务組批量刪除定時任务
     *
     * @param jobkeyList
     * @throws SchedulerException
     */
    public Boolean batchDeleteGroupJob(List<JobKey> jobkeyList) throws Exception {
        return scheduler.deleteJobs(jobkeyList);
    }

    /**
     * 根据任务組批量查询出jobkey
     *
     * @param jobGroup 任务组
     * @throws SchedulerException
     */
    public void batchQueryGroupJob(List<JobKey> jobkeyList, String jobGroup) throws Exception {
        GroupMatcher<JobKey> matcher = GroupMatcher.groupEquals(jobGroup);
        Set<JobKey> jobkeySet = scheduler.getJobKeys(matcher);
        jobkeyList.addAll(jobkeySet);
    }

    /**
     * 根据jobkey查询job详情
     *
     * @param jobKey
     * @return
     */
    public JobDetail queryJobDetail(JobKey jobKey) {
        JobDetail jobDetail = null;
        try {
            jobDetail = scheduler.getJobDetail(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobDetail;
    }
}
