package com.kingc.safetystandard.assessstandardtool.entity;

import com.kingc.assessstandardtool.business.entity.DelEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "management_factor")
@Getter
@Setter
public class ManagementFactor extends DelEntity {
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
     * 管理要素编码
     */
    @Column(name = "code")
    private String code;
    /**
     * 父节点ID
     */
    @Column(name = "parent_id")
    private String parentId;
    /**
     * 父节点路径
     */
    @Column(name = "parent_path")
    private String parentPath;
    /**
     * 层级
     */
    @Column(name = "level")
    private String level;
    /**
     * 描述
     */
    @Column(name = "description")
    private String description;
    /**
     * 排序
     */
    @Column(name = "order_num")
    private String orderNum;
    /**
     * 是否缺项
     */
    @Column(name = "lacuna_flag")
    private String lacunaFlag;



}

