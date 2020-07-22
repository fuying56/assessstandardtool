package com.kingc.safetystandard.assessstandardtool.entity;

import com.kingc.assessstandardtool.business.entity.IdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "task_assign_info")
@Getter
@Setter
public class TaskAssignInfo extends IdEntity {
    /**
     * 企业编码
     */
    @Column(name = "mine_code")
    private String mineCode;
    /**
     * 下达任务ID
     */
    @Column(name = "task_assign_id")
    private String task_assign_id;
    /**
     * 管理要素ID
     */
    @Column(name = "management_factor_id")
    private String management_factor_id;
    /**
     * 管理要素name
     */
    @Column(name = "management_factor_label")
    private String management_factor_label;
    /**
     * 评分表ID
     */
    @Column(name = "scoring_table_id")
    private String scoring_table_id;
    /**
     * 评分表Name
     */
    @Column(name = "scoring_table_label")
    private String scoring_table_label;
    /**
     * 评分表细项ID
     */
    @Column(name = "scoring_table_detail_id")
    private String scoring_table_detail_id;
    /**
     * 评分表细项Name
     */
    @Column(name = "scoring_table_detail_label")
    private String scoring_table_detail_label;

}
