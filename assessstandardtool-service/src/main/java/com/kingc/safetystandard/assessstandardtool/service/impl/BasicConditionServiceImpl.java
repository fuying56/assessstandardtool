package com.kingc.safetystandard.assessstandardtool.service.impl;

import com.kingc.assessstandardtool.business.base.DefaultBaseServiceImpl;
import com.kingc.safetystandard.assessstandardtool.dao.BasicConditionMapper;
import com.kingc.safetystandard.assessstandardtool.entity.BasicCondition;
import com.kingc.safetystandard.assessstandardtool.service.BasicConditionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasicConditionServiceImpl extends DefaultBaseServiceImpl<BasicCondition, BasicConditionMapper> implements BasicConditionService {
}
