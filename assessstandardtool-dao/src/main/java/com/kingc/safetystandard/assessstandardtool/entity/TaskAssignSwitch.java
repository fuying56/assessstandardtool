package com.kingc.safetystandard.assessstandardtool.entity;

import com.kingc.assessstandardtool.business.entity.DelEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;


@Table(name = "task_assign_switch")
@Getter
@Setter
public class TaskAssignSwitch extends DelEntity {
    /**
     * 企业编码
     */
    @Column(name = "mine_code")
    private String mineCode;
    /**
     * 是否进行任务分配
     */
    @Column(name = "task_assign_flag")
    private String taskAssignFlag;
}
