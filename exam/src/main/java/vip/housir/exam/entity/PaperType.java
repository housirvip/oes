package vip.housir.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum PaperType {

    /**
     * 单项测试
     */
    SinglePaper("single_paper"),
    /**
     * 模拟题
     */
    Simulation("simulation"),
    /**
     * 真题
     */
    PastPaper("past_paper");

    private String value;
}
