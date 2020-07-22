package com.kingc.safetystandard.assessstandardtool.controller;

import com.kingc.assessstandardtool.business.base.controller.impl.BaseControllerImpl;
import com.kingc.safetystandard.assessstandardtool.entity.TaskAssign;
import com.kingc.safetystandard.assessstandardtool.service.TaskAssignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taskAssign")
public class TaskAssignController extends BaseControllerImpl<TaskAssign, TaskAssignService> {
}
