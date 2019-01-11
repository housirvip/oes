package vip.housir.exam.mqhandler;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: housirvip
 */
public interface ExamInput {

    String SCORE = "score-input";

    /**
     * 输入信道，考试打分专用
     *
     * @return SubscribableChannel
     */
    @Input(SCORE)
    SubscribableChannel score();
}
