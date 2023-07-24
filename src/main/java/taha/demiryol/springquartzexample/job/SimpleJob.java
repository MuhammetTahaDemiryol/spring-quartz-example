package taha.demiryol.springquartzexample.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.stream.IntStream;

/* We could either implement the Job interface or extend the QuartzJobBean Class for defining a job, the QuartzJobBean
class have the JobDataMap and SchedulerContext as property values and Job interface doesn't, and we need to implement
execute() method for Job interface but executeInternal() for the QuartzJobBean class  */
@Slf4j
public class SimpleJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("SimpleJob Start................");
        IntStream.range(0, 5).forEach(i -> {
            log.info("Counting Simple- {}", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        });
        log.info("SimpleJob End................");
    }
}