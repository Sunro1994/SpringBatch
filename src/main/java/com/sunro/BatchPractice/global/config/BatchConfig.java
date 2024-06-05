package com.sunro.BatchPractice.global.config;

import com.sunro.BatchPractice.global.listener.JobCompletionNotificationListener;
import com.sunro.BatchPractice.global.tasklet.MemberDeletionTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final MemberDeletionTasklet memberDeletionTasklet;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;


    @Bean
    public Job memberDeletionJob() {
        return new JobBuilder("memberDeletionJob",jobRepository)
                .start(step1())
                .listener(listener())
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("step1", jobRepository)
                .tasklet(memberDeletionTasklet, transactionManager)
                .build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionNotificationListener();
    }
}
