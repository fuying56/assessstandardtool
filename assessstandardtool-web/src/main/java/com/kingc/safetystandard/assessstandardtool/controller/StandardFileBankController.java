package com.kingc.safetystandard.assessstandardtool.controller;

import com.kingc.assessstandardtool.business.base.controller.impl.BaseControllerImpl;
import com.kingc.safetystandard.assessstandardtool.entity.StandardFileBank;
import com.kingc.safetystandard.assessstandardtool.service.StandardFileBankService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/standardFileBank")
public class StandardFileBankController extends BaseControllerImpl<StandardFileBank, StandardFileBankService> {
}
