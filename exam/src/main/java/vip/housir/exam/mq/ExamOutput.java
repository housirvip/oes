package vip.housir.exam.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author: housirvip
 */
public interface ExamOutput {

    String SCORE = "score-output";

    /**
     * 考试打分
     *
     * @return MessageChannel
     */
    @Output(SCORE)
    MessageChannel score();
}
