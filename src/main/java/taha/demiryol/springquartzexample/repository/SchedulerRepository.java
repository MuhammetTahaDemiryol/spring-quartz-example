package taha.demiryol.springquartzexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taha.demiryol.springquartzexample.entity.SchedulerJobInfo;

// For Crud functionality
@Repository
public interface SchedulerRepository extends JpaRepository<SchedulerJobInfo, Long> {
    SchedulerJobInfo findByJobName(String jobName);
}