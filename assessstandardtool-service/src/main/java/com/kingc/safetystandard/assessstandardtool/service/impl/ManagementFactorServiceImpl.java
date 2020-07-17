package com.kingc.safetystandard.assessstandardtool.service.impl;

import com.kingc.assessstandardtool.business.base.DefaultBaseServiceImpl;
import com.kingc.safetystandard.assessstandardtool.dao.ManagementFactorMapper;
import com.kingc.safetystandard.assessstandardtool.entity.ManagementFactor;
import com.kingc.safetystandard.assessstandardtool.service.ManagementFactorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ManagementFactorServiceImpl  extends DefaultBaseServiceImpl<ManagementFactor, ManagementFactorMapper> implements ManagementFactorService {
}
