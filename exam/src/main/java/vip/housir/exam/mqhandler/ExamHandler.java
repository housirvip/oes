package vip.housir.exam.mqhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import vip.housir.exam.service.ExamService;

/**
 * @author: housirvip
 */
@Slf4j
@EnableBinding(ExamInput.class)
@RequiredArgsConstructor
public class ExamHandler {

    private final ExamService examService;

    @StreamListener(ExamInput.SCORE)
    public void scoreByExamId(Message<Integer> msg) {

        try {
            examService.score(msg.getPayload());
        } catch (Exception e) {
            log.error("考试打分失败", e);
        }
    }
}
