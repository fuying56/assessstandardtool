package com.kingc.assessstandardtool.business.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

/**
 * @author Administrator
 */
@Setter
@Getter
@ToString
public class DelEntity extends BaseEntity {

    @Column(name = "del_flag")
    private String delFlag = "0";

}
