package com.example.rechargesbatch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class RechargesJobScheduler {

    private final JobLauncher jobLauncher;
    private final Job rechargesStatusJob;

    @Scheduled(cron = "${scheduler.cron}")
    public void runJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("run.at", Instant.now().toEpochMilli())
                .toJobParameters();

        log.info("Scheduler: lanzando Job con run.at={}", params.getLong("run.at"));
        jobLauncher.run(rechargesStatusJob, params);
    }
}
