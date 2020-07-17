package com.kingc.assessstandardtool.business.base.service.impl;



import com.kingc.assessstandardtool.business.base.service.BaseService;
import com.kingc.assessstandardtool.mybatis.base.dao.MybatisBaseMapper;
import com.kingc.assessstandardtool.mybatis.base.service.impl.AbstractBaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * 通用业务实现类
 *
 * @author Administrator
 */
@Transactional(rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T extends Serializable, D extends MybatisBaseMapper> extends AbstractBaseServiceImpl<T, D> implements BaseService<T> {
    @Override
    public String getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
