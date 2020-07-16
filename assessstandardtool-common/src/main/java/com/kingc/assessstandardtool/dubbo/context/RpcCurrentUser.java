package com.kingc.assessstandardtool.dubbo.context;


import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 */
@Getter
@Setter
public class RpcCurrentUser {

    private String id;

    private String tenantName;

    private String tenantId;

    private String userName;

    private String departmentId;

    private String departmentName;
}
