package com.kingc.safetystandard.assessstandardtool.service.impl;

import com.kingc.assessstandardtool.business.base.DefaultBaseServiceImpl;
import com.kingc.safetystandard.assessstandardtool.dao.TaskAssignStaffMapper;
import com.kingc.safetystandard.assessstandardtool.entity.TaskAssignStaff;
import com.kingc.safetystandard.assessstandardtool.service.TaskAssignStaffService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskAssignStaffServiceImpl extends DefaultBaseServiceImpl<TaskAssignStaff, TaskAssignStaffMapper> implements TaskAssignStaffService {
}
