# Quartz Module Overview

Quartz is a powerful open-source job scheduling framework that allows developers to schedule and execute tasks (jobs) at specified intervals or times. It is widely used in Java applications to manage various job-related functionalities. This  is my attempt at explaining the basics of Quartz.

## Jobs

A job in Quartz represents a task or unit of work that needs to be executed. To define a job, you can implement the `org.quartz.Job` interface or create a job class that extends `org.springframework.scheduling.quartz.QuartzJobBean`.

Example of a simple job class that implements the `Job` interface:

```java
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Your job logic goes here
    }
}
```

You can find the QuartzJobBean example in [CronJob](/src/main/java/taha/demiryol/springquartzexample/job/CronJob.java) and [SimpleJob](/src/main/java/taha/demiryol/springquartzexample/job/SimpleJob.java)

## Triggers

Triggers in Quartz are used to schedule jobs for execution. A trigger defines the time and frequency when a job should be run. There are different types of triggers available in Quartz, but the most commonly used ones are:

1. **SimpleTrigger**: Allows you to schedule a job to run at a specific time or repeat after a fixed interval.

2. **CronTrigger**: Enables you to set up more complex schedules using cron expressions.

Example of a cron trigger is in [CronTrigger](/src/main/java/taha/demiryol/springquartzexample/component/JobScheduleCreator.java)


## Cron Expression

A cron expression is a string that defines the schedule of a cron trigger. It consists of six or seven fields separated by whitespace, representing the following time components:

```
second, minute, hour, day-of-month, month, day-of-week, [year]
```

Each field can contain a specific value, a list of values, a range, or wildcards. For instance, `"0 */5 * ? * *"` means "every 5 minutes."

## Misfire Instruction

A misfire occurs when a trigger's fire time is missed due to system overload, server downtime, or other reasons. Quartz provides misfire instructions to handle such situations. You can specify how Quartz behaves when a misfire happens.

Common misfire instructions:

1. `MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY`: The default behavior. Quartz ignores the misfire and reschedules the trigger.

2. `MISFIRE_INSTRUCTION_FIRE_NOW`: The trigger is fired immediately when the scheduler starts after a misfire.

3. `MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT`: The trigger is rescheduled to run now and maintains its original repeat count.

## SpringBeanJobFactory

The `SpringBeanJobFactory` is a specialized `JobFactory` in Quartz designed to work seamlessly with Spring's dependency injection capabilities. It enables the use of Spring-managed beans as Quartz jobs, allowing you to benefit from features like dependency injection.

To integrate `SpringBeanJobFactory`, you need to configure it in the Quartz scheduler as in [SchedulerJobFactory](src/main/java/taha/demiryol/springquartzexample/config/SchedulerJobFactory.java)

With this setup, you can now use Spring's `@Autowired` or other annotations within your Quartz job classes.

## Summary

Quartz is a powerful job scheduling framework for Java applications. Here's a summary of the key points covered:

- **Jobs**: Represent tasks or units of work that need to be executed.

- **Triggers**: Schedule jobs for execution using simple triggers or cron triggers.

- **Cron Expression**: Defines the schedule of a cron trigger with six or seven time components.

- **Misfire Instruction**: Handles missed trigger fire times due to system overload or downtime.

- **SpringBeanJobFactory**: Integrates Quartz jobs with Spring's dependency injection capabilities.


## Running the Application

To run the application, you can use the `./mvnw spring-boot:run` command. The application will start on port 8080.
TO stop the application, you can use the `CTRL + C` command in the same terminal window. Or you can use the eclipse or intellij ides.

Screenshots of the application:
![image](https://github.com/MuhammetTahaDemiryol/rentacar-microservice/assets/69295311/3fc006c8-0be1-4af2-a92b-9d7af8a7c838)



## References

- [Quartz Scheduler Documentation](http://www.quartz-scheduler.org/documentation/)
- [Quartz Scheduler Tutorial](https://stackabuse.com/guide-to-quartz-with-spring-boot-job-scheduling-and-automation/)