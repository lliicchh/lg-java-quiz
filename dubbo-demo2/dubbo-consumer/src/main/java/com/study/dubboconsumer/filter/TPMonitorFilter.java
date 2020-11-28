package com.study.dubboconsumer.filter;

import com.study.dubboconsumer.TPMonitorUtil;
import com.study.dubboconsumer.pojo.TPInfo;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

@Activate(group = {CommonConstants.CONSUMER})
public class TPMonitorFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Long startTime = System.currentTimeMillis();
        Result result = invoker.invoke(invocation);;
        Long consume = System.currentTimeMillis() - startTime;
        String methodName = invocation.getMethodName();
        TPMonitorUtil.addTPInfo(new TPInfo(consume, methodName));
        return result;
    }
}
