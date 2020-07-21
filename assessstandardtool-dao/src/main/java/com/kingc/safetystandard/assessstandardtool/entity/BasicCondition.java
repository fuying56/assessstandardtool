package com.kingc.safetystandard.assessstandardtool.entity;

import com.kingc.assessstandardtool.business.entity.DelEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "basic_condition")
@Getter
@Setter
public class BasicCondition extends DelEntity {
    /**
     * 公司id
     */
    @Column(name = "mine_code")
    private String mineCode;
    /**
     * 管理要素名称
     */
    @Column(name = "label")
    private String label;
    /**
     * 排序
     */
    @Column(name = "order_num")
    private String orderNum;
}
