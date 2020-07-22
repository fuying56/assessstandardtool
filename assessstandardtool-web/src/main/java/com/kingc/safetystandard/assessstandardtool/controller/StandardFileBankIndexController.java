package com.kingc.safetystandard.assessstandardtool.controller;

import com.kingc.assessstandardtool.business.base.controller.impl.BaseControllerImpl;
import com.kingc.safetystandard.assessstandardtool.entity.StandardFileBankIndex;
import com.kingc.safetystandard.assessstandardtool.service.StandardFileBankIndexService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/standardFileBankIndex")
public class StandardFileBankIndexController extends BaseControllerImpl<StandardFileBankIndex, StandardFileBankIndexService> {
}
