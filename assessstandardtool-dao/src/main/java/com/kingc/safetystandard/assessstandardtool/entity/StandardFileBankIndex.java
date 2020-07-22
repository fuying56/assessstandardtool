package com.kingc.safetystandard.assessstandardtool.entity;

import com.kingc.assessstandardtool.business.entity.DelEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "standard_file_bank_index")
@Getter
@Setter
public class StandardFileBankIndex extends DelEntity {
    /**
     * 企业编码
     */
    @Column(name = "mine_code")
    private String mineCode;
    /**
     * 索引名称
     */
    @Column(name = "index_name")
    private String indexName;

}
