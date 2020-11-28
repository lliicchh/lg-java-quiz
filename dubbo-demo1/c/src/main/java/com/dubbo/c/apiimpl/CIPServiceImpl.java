package com.dubbo.c.apiimpl;

import com.dubbo.api.c.CIPService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

@Service
public class CIPServiceImpl implements CIPService {

    @Override
    public void ipPrint() {
        String remoteIP = RpcContext.getContext().getAttachment("remoteIP");
        System.out.println("IP: " + remoteIP);
    }
}
