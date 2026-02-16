package com.example.rechargesbatch.job;

import com.example.rechargesbatch.service.RechargesStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RechargesApiTasklet implements Tasklet {

    private final RechargesStatusService service;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        log.info("Tasklet: llamando PATCH a /recharges/status");
        service.updateStatusViaApi();
        return RepeatStatus.FINISHED;
    }
}
