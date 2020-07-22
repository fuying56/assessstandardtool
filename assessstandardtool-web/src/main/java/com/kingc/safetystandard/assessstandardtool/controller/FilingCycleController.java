package com.kingc.safetystandard.assessstandardtool.controller;

import com.kingc.assessstandardtool.business.base.controller.impl.BaseControllerImpl;
import com.kingc.safetystandard.assessstandardtool.entity.FilingCycle;
import com.kingc.safetystandard.assessstandardtool.service.FilingCycleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filingCycle")
public class FilingCycleController extends BaseControllerImpl<FilingCycle, FilingCycleService> {
}
