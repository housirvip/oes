package vip.housir.exam.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: housirvip
 */
public interface ExamInput {

    String SCORE = "score-input";

    /**
     * 考试打分
     *
     * @return SubscribableChannel
     */
    @Input(SCORE)
    SubscribableChannel score();
}
