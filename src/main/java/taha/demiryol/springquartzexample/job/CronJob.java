package taha.demiryol.springquartzexample.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.stream.IntStream;

// Similar the SimpleJob only fires on cron expression
@Slf4j
@DisallowConcurrentExecution // For disallowing the  multiple schedulers acting on this job at the same time
public class CronJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("SimpleCronJob Start................");
        IntStream.range(0, 10).forEach(i -> {
            log.info("Counting Cron - {}", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        });
        log.info("SimpleCronJob End................");
    }
}