package com.kingc.safetystandard.assessstandardtool.controller;

import com.kingc.assessstandardtool.business.base.controller.impl.BaseControllerImpl;
import com.kingc.safetystandard.assessstandardtool.entity.TaskAssignSwitch;
import com.kingc.safetystandard.assessstandardtool.service.TaskAssignSwitchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taskAssignSwitch")
public class TaskAssignSwitchController extends BaseControllerImpl<TaskAssignSwitch, TaskAssignSwitchService> {
}
