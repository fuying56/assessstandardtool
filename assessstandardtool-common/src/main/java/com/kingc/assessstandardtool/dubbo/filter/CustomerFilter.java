package com.kingc.assessstandardtool.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.extension.SPI;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;

/**
 * @author Administrator
 */
@SPI
@Activate(group = {Constants.CONSUMER})
public class CustomerFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (null != SecurityUtils.getSubject().getPrincipal()) {
            RpcContext.getContext().setAttachment("user", JSON.toJSONString(SecurityUtils.getSubject().getPrincipal()));
        }

        return invoker.invoke(invocation);
    }
}
