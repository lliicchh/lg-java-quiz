package com.dubbo.a.controller;

import com.dubbo.api.b.BIPService;
import com.dubbo.api.c.CIPService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IPController {

    @Reference
    private BIPService bIpService;

    @Reference
    private CIPService cIpService;

    @GetMapping("/ip")
    public void getRemoteIP() {
        bIpService.ipPrint();
        cIpService.ipPrint();
    }
}
