package taha.demiryol.springquartzexample.component;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Slf4j // Logger
@Component // Spring Bean Creation
public class JobScheduleCreator {

    /* There are two triggers in quartz Cron and Simple, the difference is cron trigger acts on cron expressions
    simple trigger acts on defined intervals */

     // CronTrigger's creation method depends on respective factoryBean in quartz
    public CronTrigger createCronTrigger(String triggerName, Date startTime, String cronExpression,
                                         int misfireInstruction) {

        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setName(triggerName);
        factoryBean.setStartTime(startTime);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setMisfireInstruction(misfireInstruction);
        try {
            factoryBean.afterPropertiesSet(); // This method here fills remaining fields in the bean.
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return factoryBean.getObject();
    }

    // Same for this method only SimpleTrigger
    public SimpleTrigger createSimpleTrigger(String triggerName, Date startTime, Long repeatTime,
                                             int misfireInstruction) {

        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setName(triggerName);
        factoryBean.setStartTime(startTime);
        factoryBean.setRepeatInterval(repeatTime);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        factoryBean.setMisfireInstruction(misfireInstruction);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    // This is a generic method for creating any jobs in quartz
    public JobDetail createJob(Class<? extends QuartzJobBean> jobClass, boolean isDurable, ApplicationContext context,
                                             String jobName, String jobGroup) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(isDurable);
        factoryBean.setApplicationContext(context);
        factoryBean.setName(jobName);
        factoryBean.setGroup(jobGroup);

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobName + jobGroup, jobClass.getName());
        factoryBean.setJobDataMap(jobDataMap);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }
}