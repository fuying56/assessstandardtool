package com.kingc.assessstandardtool.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.extension.SPI;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import com.kingc.assessstandardtool.dubbo.context.RpcCurrentUser;
import com.kingc.assessstandardtool.tool.utils.UtilsString;


/**
 * @author Administrator
 */
@SPI
@Activate(group = {Constants.PROVIDER})
public class ProviderFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String userJson = RpcContext.getContext().getAttachment("user");
        if(UtilsString.isNoneBlank(userJson)){
            RpcCurrentUser rpcCurrentUser = JSON.parseObject(userJson, RpcCurrentUser.class);
            DubboContext.setSubjectThreadLocal(rpcCurrentUser);
        }
        return invoker.invoke(invocation);
    }
}
