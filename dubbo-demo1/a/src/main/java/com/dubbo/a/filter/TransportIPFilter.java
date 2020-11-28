package com.dubbo.a.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Activate(group = {CommonConstants.CONSUMER})
public class TransportIPFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        String remoteAddr = requestAttributes.getRequest().getRemoteAddr();
        RpcContext.getContext().setAttachment("remoteIP", remoteAddr);
        try {
            return invoker.invoke(invocation);
        } finally {
            RpcContext.getContext().clearAttachments();
        }
    }
}
