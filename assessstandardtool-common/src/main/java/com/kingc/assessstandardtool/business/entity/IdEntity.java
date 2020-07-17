package com.kingc.assessstandardtool.business.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class IdEntity implements Serializable{

    @Id
    @Column(length=32)
    private String id;

}
