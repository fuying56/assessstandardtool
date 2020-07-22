package com.kingc.safetystandard.assessstandardtool.service.impl;

import com.kingc.assessstandardtool.business.base.DefaultBaseServiceImpl;
import com.kingc.safetystandard.assessstandardtool.dao.VetoConditionMapper;
import com.kingc.safetystandard.assessstandardtool.entity.VetoCondition;
import com.kingc.safetystandard.assessstandardtool.service.VetoConditionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class VetoConditionServiceImpl extends DefaultBaseServiceImpl<VetoCondition, VetoConditionMapper> implements VetoConditionService {
}
