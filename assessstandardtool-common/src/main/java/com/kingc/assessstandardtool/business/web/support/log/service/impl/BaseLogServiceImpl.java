package com.kingc.assessstandardtool.business.web.support.log.service.impl;//package com.stem.web.config.mvc.support.log.service.impl;
//
//import com.stem.base.common.service.impl.BaseServiceImpl;
//import com.stem.modules.base.user.BaseAccount;
//import com.stem.web.config.mvc.support.log.dao.BaseLogMapper;
//import com.stem.web.config.mvc.support.log.entity.BaseLog;
//import com.stem.web.config.mvc.support.log.service.BaseLogService;
//import org.apache.shiro.SecurityUtils;
//import org.springframework.stereotype.Service;
//import seed.tool.utils.UtilsDate;
//
//import java.lang.reflect.Method;
//
//@Service
//public class BaseLogServiceImpl extends BaseServiceImpl<BaseLog, BaseLogMapper> implements BaseLogService {
//
//
//    @Override
//    public void saveLog(Class beanClz, Method method) throws Exception {
//        String userName = "未登陆用户";
//        if (SecurityUtils.getSubject() != null && SecurityUtils.getSubject().getPrincipal() != null) {
//            userName = ((BaseAccount) SecurityUtils.getSubject().getPrincipal()).getAccount();
//        }
//
//        StringBuilder content = new StringBuilder();
//        content.append(userName)
//                .append("在")
//                .append(UtilsDate.currentDateTime())
//                .append("调用了:")
//                .append(beanClz.getSimpleName())
//                .append("的")
//                .append(method.getName())
//                .append("方法");
//
//        // TODO: 2018/8/1 0001 日志相关的操作
//        super.save(new BaseLog(userName, content.toString()));
//    }
//}
