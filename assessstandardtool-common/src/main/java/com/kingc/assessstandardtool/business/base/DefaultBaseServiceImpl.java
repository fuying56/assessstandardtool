package com.kingc.assessstandardtool.business.base;



import com.kingc.assessstandardtool.business.base.entity.User;
import com.kingc.assessstandardtool.business.base.service.impl.BaseServiceImpl;
import com.kingc.assessstandardtool.mybatis.base.dao.MybatisBaseMapper;
import org.apache.shiro.SecurityUtils;

import java.io.Serializable;


/**
 * @author Administrator
 */
public class DefaultBaseServiceImpl<T extends Serializable, D extends MybatisBaseMapper>  extends BaseServiceImpl<T,D> {


    @Override
    public User getCurrentUser() {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal == null) {
            return null;
        }
        return (User) principal;
    }

    public Object getAccountInfo() {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal == null) {
            return null;
        }
        return  principal;
    }
}
