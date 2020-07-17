package com.kingc.assessstandardtool.mybatis.log;//package com.seed.mybatis.plugin.log;
//
//import lombok.extern.log4j.Log4j;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//
//import java.util.Properties;
//
//@Log4j
//@Intercepts({
//        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
//        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
//public class SqlLogPlugin implements Interceptor {
//
//    public Object intercept(Invocation invocation) throws Throwable {
//        if (log.isDebugEnabled()) {
//            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
//            Object parameter = null;
//            if (invocation.getArgs().length > 1) {
//                parameter = invocation.getArgs()[1];
//            }
//            String sqlId = mappedStatement.getId();
//            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
//            Object returnValue;
//            long start = System.currentTimeMillis();
//            returnValue = invocation.proceed();
//            long end = System.currentTimeMillis();
//            long time = (end - start);
//            if (time > 1) {
//                log.debug("SQL执行时间为" + time + "ms   SQL" + sqlId + ":" + boundSql.getSql());
//            }
//            return returnValue;
//        }
//        return invocation.proceed();
//    }
//
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    public void setProperties(Properties properties0) {
//    }
//}