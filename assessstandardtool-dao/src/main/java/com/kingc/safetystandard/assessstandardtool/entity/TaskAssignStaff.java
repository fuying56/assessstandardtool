package com.kingc.safetystandard.assessstandardtool.entity;

import com.kingc.assessstandardtool.business.entity.IdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;
@Table(name = "task_assign_staff")
@Getter
@Setter
public class TaskAssignStaff extends IdEntity {
    /**
     * 企业编码
     */
    @Column(name = "mine_code")
    private String mineCode;
    /**
     * 任务下达ID
     */
    @Column(name = "task_assign_id")
    private String taskAssignId;
    /**
     * 任务下达部门ID
     */
    @Column(name = "assign_depart_id")
    private String assignDepartId;
    /**
     * 任务下达部门name
     */
    @Column(name = "assign_depart_name")
    private String assignDepartName;
    /**
     * 任务下达员工ID
     */
    @Column(name = "assign_staff_id")
    private String assignStaffId;
    /**
     * 任务下达员工name
     */
    @Column(name = "assign_staff_name")
    private String assignStaffName;
}
