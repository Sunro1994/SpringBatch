package com.sunro.BatchPractice.global.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberDeletionScheduler {

    private final JobLauncher jobLauncher;
    private final Job memberDeletionJob;

    @Scheduled(cron = "0 0 0 * * ?")// 매일 자정에 실행
    public void performMemberDeletion() throws Exception {
        jobLauncher.run(memberDeletionJob, new JobParameters());
    }
}
