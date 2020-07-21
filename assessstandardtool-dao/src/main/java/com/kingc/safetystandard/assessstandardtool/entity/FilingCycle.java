package com.kingc.safetystandard.assessstandardtool.entity;

import com.kingc.assessstandardtool.business.entity.DelEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "filing_cycle")
@Getter
@Setter
public class FilingCycle extends DelEntity {
    /**
     * 公司id
     */
    @Column(name = "mine_code")
    private String mineCode;
    /**
     * 归档周期
     */
    @Column(name = "filing_cycle")
    private String filingCycle;
    /**
     * 归档周期编码
     */
    @Column(name = "filing_cycle_code")
    private String filingCycleCode;
}
