package com.kingc.assessstandardtool.business.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import com.kingc.assessstandardtool.mybatis.base.service.annotation.CreateBy;
import com.kingc.assessstandardtool.mybatis.base.service.annotation.CreateDate;
import com.kingc.assessstandardtool.mybatis.base.service.annotation.UpdateBy;
import com.kingc.assessstandardtool.mybatis.base.service.annotation.UpdateDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author Administrator
 */
@Getter
@Setter
@ToString
public class BaseEntity extends IdEntity {

    @CreateBy
    @Column(name = "create_by", length = 32, updatable = false)
    private String createBy;

    @CreateDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_date", updatable = false)
    private Date createDate;


    @UpdateBy
    @Column(name = "update_by", length = 32)
    private String updateBy;

    @UpdateDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_date")
    private Date updateDate;
}
