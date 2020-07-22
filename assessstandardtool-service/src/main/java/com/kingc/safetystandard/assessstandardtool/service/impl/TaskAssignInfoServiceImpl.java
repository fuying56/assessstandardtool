package com.kingc.safetystandard.assessstandardtool.service.impl;

import com.kingc.assessstandardtool.business.base.DefaultBaseServiceImpl;
import com.kingc.safetystandard.assessstandardtool.dao.TaskAssignInfoMapper;
import com.kingc.safetystandard.assessstandardtool.entity.TaskAssignInfo;
import com.kingc.safetystandard.assessstandardtool.service.TaskAssignInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskAssignInfoServiceImpl extends DefaultBaseServiceImpl<TaskAssignInfo, TaskAssignInfoMapper> implements TaskAssignInfoService {
}
