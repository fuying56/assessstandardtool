package com.kingc.safetystandard.assessstandardtool.entity;

import com.kingc.assessstandardtool.business.entity.DelEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "task_assign")
@Getter
@Setter
public class TaskAssign extends DelEntity {
    /**
     * 企业编码
     */
    @Column(name = "mine_code")
    private String mineCode;
    /**
     * 完成时间
     */
    @Column(name = "complete_date")
    private Date completeDate;
    /**
     * 是否完成
     */
    @Column(name = "finished")
    private String finished;
    /**
     * 任务类型（整表下达还是分条目下达）
     */
    @Column(name = "task_type")
    private String taskType;

}
