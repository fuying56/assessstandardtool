package com.kingc.safetystandard.assessstandardtool.service.impl;

import com.kingc.assessstandardtool.business.base.DefaultBaseServiceImpl;
import com.kingc.safetystandard.assessstandardtool.dao.ScoringTableMapper;
import com.kingc.safetystandard.assessstandardtool.entity.ScoringTable;
import com.kingc.safetystandard.assessstandardtool.service.ScoringTableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ScoringTableServiceImpl extends DefaultBaseServiceImpl<ScoringTable, ScoringTableMapper> implements ScoringTableService {
}
