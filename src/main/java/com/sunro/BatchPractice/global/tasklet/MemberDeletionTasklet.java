package com.sunro.BatchPractice.global.tasklet;

import com.sunro.BatchPractice.domain.member.entity.Member;
import com.sunro.BatchPractice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberDeletionTasklet implements Tasklet {

    private final MemberRepository memberRepository;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<Member> membersToDelete = memberRepository.findAll();
        memberRepository.deleteAll(membersToDelete);

        return RepeatStatus.FINISHED;
    }
}
