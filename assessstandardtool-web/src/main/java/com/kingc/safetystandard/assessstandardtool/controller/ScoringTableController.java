package com.kingc.safetystandard.assessstandardtool.controller;

import com.kingc.assessstandardtool.business.base.controller.impl.BaseControllerImpl;
import com.kingc.safetystandard.assessstandardtool.entity.ScoringTable;
import com.kingc.safetystandard.assessstandardtool.service.ScoringTableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scoringTable")
public class ScoringTableController extends BaseControllerImpl<ScoringTable, ScoringTableService> {
}
