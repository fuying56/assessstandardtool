package com.kingc.safetystandard.assessstandardtool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value="/test")
    public String index(){
        return "hello muti module!!!";
    }

}
