package com.kingc.safetystandard.assessstandardtool.service.impl;

import com.kingc.assessstandardtool.business.base.DefaultBaseServiceImpl;
import com.kingc.safetystandard.assessstandardtool.dao.FilingCycleMapper;
import com.kingc.safetystandard.assessstandardtool.entity.FilingCycle;
import com.kingc.safetystandard.assessstandardtool.service.FilingCycleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class FilingCycleServiceImpl extends DefaultBaseServiceImpl<FilingCycle, FilingCycleMapper> implements FilingCycleService {
}
