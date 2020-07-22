package com.kingc.safetystandard.assessstandardtool.service.impl;

import com.kingc.assessstandardtool.business.base.DefaultBaseServiceImpl;
import com.kingc.safetystandard.assessstandardtool.dao.StandardFileBankMapper;
import com.kingc.safetystandard.assessstandardtool.entity.StandardFileBank;
import com.kingc.safetystandard.assessstandardtool.service.StandardFileBankService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class StandardFileBankServiceImpl extends DefaultBaseServiceImpl<StandardFileBank, StandardFileBankMapper> implements StandardFileBankService {
}
