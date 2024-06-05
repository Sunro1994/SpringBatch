package com.sunro.BatchPractice.global.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("작업 수행");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("작업 완료");
    }
}
