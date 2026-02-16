package com.example.rechargesbatch.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchJobConfig {

    @Bean
    public Job rechargesStatusJob(JobRepository jobRepository, Step rechargesStatusStep) {
        return new JobBuilder("rechargesStatusJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(rechargesStatusStep)
                .build();
    }

    @Bean
    public Step rechargesStatusStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            RechargesApiTasklet tasklet
    ) {
        return new StepBuilder("rechargesStatusStep", jobRepository)
                .tasklet(tasklet, transactionManager)
                .build();
    }
}
