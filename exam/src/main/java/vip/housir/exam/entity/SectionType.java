package vip.housir.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum SectionType {

    /**
     * 数量分析
     */
    Quantity("quantity"),
    /**
     * 资料分析
     */
    DataAnalysis("data_analysis"),
    /**
     * 常识判断
     */
    CommonSense("common_sense"),
    /**
     * 知觉与速度
     */
    Perception("perception"),
    /**
     * 推理推断
     */
    Inference("inference"),
    /**
     * 言语表达与理解
     */
    Expression("expression");

    private String value;
}
