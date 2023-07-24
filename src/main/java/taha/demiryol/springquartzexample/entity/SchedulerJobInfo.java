package taha.demiryol.springquartzexample.entity;


import jakarta.persistence.*;
import lombok.*;


/* While we can use the quartz tables for retrieving and persisting info about job it's not recommended because quartz
can update and fix it, this class is for that purpose, keeping track of our jobs.*/
@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scheduler_job_info")
public class SchedulerJobInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String jobName;
    private String jobGroup;
    private String jobStatus;
    private String jobClass;
    private String cronExpression;
    private String desc;
    private String interfaceName;
    private Long repeatTime;
    private Boolean cronJob;
}