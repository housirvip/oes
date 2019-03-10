package vip.housir.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum QuestionType {

    /**
     * 单选题
     */
    SingleSelection("single_selection"),
    /**
     * 多选题
     */
    MultipleSelection("multiple_selection"),
    /**
     * 简答题
     */
    ShortAnswer("short_answer");

    private String value;
}
