package com.woniuxy.cq.ssmboot39.controller;

import me.maiz.tool.KuaiDiNiaoQueryAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private KuaiDiNiaoQueryAPI kuaiDiNiaoQueryAPI;


    @GetMapping("kuaidiQuery")
    public String get(String code,String no) throws Exception {

        return kuaiDiNiaoQueryAPI.getOrderTracesByJson(code,no);

    }


}
