package com.kingc.safetystandard.assessstandardtool.service.impl;

import com.kingc.assessstandardtool.business.base.DefaultBaseServiceImpl;
import com.kingc.safetystandard.assessstandardtool.dao.TaskAssignMapper;
import com.kingc.safetystandard.assessstandardtool.entity.TaskAssign;
import com.kingc.safetystandard.assessstandardtool.service.TaskAssignService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskAssignServiceImpl extends DefaultBaseServiceImpl<TaskAssign, TaskAssignMapper> implements TaskAssignService {
}
