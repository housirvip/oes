package vip.housir.exam.mqhandler;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author: housirvip
 */
public interface ExamOutput {

    String SCORE = "score-output";

    /**
     * 输出信道，考试打分专用
     *
     * @return MessageChannel
     */
    @Output(SCORE)
    MessageChannel score();
}
