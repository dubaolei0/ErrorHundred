package dubaolei.quartz;

import java.io.Serializable;

/**
 * jobName 和 jobClass 常量定义类（可有可无）
 * @Author: xin.qin
 * @Date: 2022/07/17 22:14
 */
public class QuartzConstant implements Serializable {
    /**
     * 点播转直播类路径
     */
    public static final String JOB_VODTOLIVE_JOBCLASS = "dubaolei.quartz.MyTask";
}
