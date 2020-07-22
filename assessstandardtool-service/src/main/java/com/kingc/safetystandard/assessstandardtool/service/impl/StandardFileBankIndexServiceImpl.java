package com.kingc.safetystandard.assessstandardtool.service.impl;

import com.kingc.assessstandardtool.business.base.DefaultBaseServiceImpl;
import com.kingc.safetystandard.assessstandardtool.dao.StandardFileBankIndexMapper;
import com.kingc.safetystandard.assessstandardtool.entity.StandardFileBankIndex;
import com.kingc.safetystandard.assessstandardtool.service.StandardFileBankIndexService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class StandardFileBankIndexServiceImpl extends DefaultBaseServiceImpl<StandardFileBankIndex, StandardFileBankIndexMapper> implements StandardFileBankIndexService {
}
