package com.kingc.safetystandard.assessstandardtool.controller;

import com.kingc.assessstandardtool.business.base.controller.impl.BaseControllerImpl;
import com.kingc.safetystandard.assessstandardtool.entity.VetoCondition;
import com.kingc.safetystandard.assessstandardtool.service.VetoConditionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vetoCondition")
public class VetoConditionController extends BaseControllerImpl<VetoCondition, VetoConditionService> {
}
