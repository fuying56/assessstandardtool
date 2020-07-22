package com.kingc.safetystandard.assessstandardtool.controller;

import com.kingc.assessstandardtool.business.base.controller.impl.BaseControllerImpl;
import com.kingc.safetystandard.assessstandardtool.entity.BasicCondition;
import com.kingc.safetystandard.assessstandardtool.service.BasicConditionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basicCondition")
public class BasicConditionController extends BaseControllerImpl<BasicCondition, BasicConditionService> {
}
