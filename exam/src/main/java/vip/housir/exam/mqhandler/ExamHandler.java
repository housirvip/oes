package vip.housir.exam.mqhandler;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import vip.housir.exam.service.ExamService;

/**
 * @author: housirvip
 * @date: 2019-01-11 19:31
 */
@EnableBinding(ExamInput.class)
@RequiredArgsConstructor
public class ExamHandler {

    private final ExamService examService;

    @StreamListener(ExamInput.SCORE)
    public void scoreByExamId(Message<Integer> msg) {

        examService.score(msg.getPayload());
    }
}
