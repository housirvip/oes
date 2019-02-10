package vip.housir.exam.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @author housirvip
 */
@RequiredArgsConstructor
@EnableBinding(ExamOutput.class)
public class ExamSender {

    private final ExamOutput examOutput;

    public void sendExamId(Integer examId) {

        examOutput.score().send(MessageBuilder.withPayload(examId).build());
    }
}
