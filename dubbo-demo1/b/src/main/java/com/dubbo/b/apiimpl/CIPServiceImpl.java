package com.dubbo.b.apiimpl;

import com.dubbo.api.b.BIPService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

@Service
public class CIPServiceImpl implements BIPService {

    @Override
    public void ipPrint() {
        String remoteIP = RpcContext.getContext().getAttachment("remoteIP");
        System.out.println("IP: " + remoteIP);
    }
}
