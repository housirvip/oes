package vip.housir.exam.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import vip.housir.exam.entity.Exam;

/**
 * @author housirvip
 */
@RequiredArgsConstructor
@EnableBinding(ExamOutput.class)
public class ExamSender {

    private final ExamOutput examOutput;

    public void sendExam(Exam exam) {

        examOutput.score().send(MessageBuilder.withPayload(exam).build());
    }
}
