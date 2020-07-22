package com.kingc.safetystandard.assessstandardtool.entity;

import com.kingc.assessstandardtool.business.entity.DelEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "standard_file_bank")
@Getter
@Setter
public class StandardFileBank extends DelEntity {
    /**
     * 企业编码
     */
    @Column(name = "mine_code")
    private String mineCode;
    /**
     * 外部ID(评分表ID或基本条件ID或否决条件ID)
     */
    @Column(name = "rela_id")
    private String relaId;
    /**
     * 管理要素id
     */
    @Column(name = "management_factor_id")
    private String managementFactorId;
    /**
     * 数据来源
     */
    @Column(name = "data_source")
    private String dataSource;
    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;
    /**
     * 文件大小
     */
    @Column(name = "file_size")
    private String fileSize;
    /**
     * 保存路径
     */
    @Column(name = "file_url")
    private String fileUrl;
    /**
     * 是否符合（针对否决条件）
     */
    @Column(name = "according")
    private String according;
    /**
     * 是否归档
     */
    @Column(name = "in_bank")
    private String inBank;
    /**
     * 是否备份
     */
    @Column(name = "back_up")
    private String backUp;
    /**
     * 排序
     */
    @Column(name = "order_num")
    private String orderNum;
    /**
     * 档案归档索引id
     */
    @Column(name = "bank_index_id")
    private String bankIndexId;
    /**
     * 档案归档索引name
     */
    @Column(name = "bank_index_name")
    private String bankIndexName;















}
