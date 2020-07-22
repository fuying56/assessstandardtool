package com.kingc.safetystandard.assessstandardtool.service.impl;

import com.kingc.assessstandardtool.business.base.DefaultBaseServiceImpl;
import com.kingc.safetystandard.assessstandardtool.dao.TaskAssignSwitchMapper;
import com.kingc.safetystandard.assessstandardtool.entity.TaskAssignSwitch;
import com.kingc.safetystandard.assessstandardtool.service.TaskAssignSwitchService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskAssignSwitchServiceImpl  extends DefaultBaseServiceImpl<TaskAssignSwitch, TaskAssignSwitchMapper> implements TaskAssignSwitchService {
}
