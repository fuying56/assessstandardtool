package com.kingc.assessstandardtool.business.web.config.realm;

import com.kingc.assessstandardtool.business.base.entity.User;
import com.kingc.assessstandardtool.shiro.cache.Constants;
import com.kingc.assessstandardtool.shiro.cache.internal.HashCache;
import com.kingc.assessstandardtool.shiro.cache.internal.builder.HashCacheBuilder;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;


/**
 * 获取用户的角色和权限信息
 *
 * @author Administrator
 * @date 2017/5/10
 */
public abstract class AuthorizingRealmAdaptor extends AuthorizingRealm {

    @Autowired
    private HashCacheBuilder hashCacheManager;

    /**
     * 授权对象.
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //验证主体.
        Object uUser = principalCollection.getPrimaryPrincipal();

        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        if (uUser == null) {
            return null;
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");

        //用户的权限集合,将当前用户的权限放入权限管理对象中.
        simpleAuthorizationInfo.addStringPermissions(new ArrayList<>());

        return simpleAuthorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //进行验证
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = getUserByToken(token);
        if (user == null) {
            throw new UnknownAccountException();
        }

        /*创建用户缓存*/
        HashCache<Object, Object> hashCache = hashCacheManager.getHashCache(Constants.USER_CACHE_NAME);
        if (hashCache.get(user.getId()) == null) {
            hashCacheManager.getHashCache(Constants.USER_CACHE_NAME).put(user.getId(), user);
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes("kingc"), getName());
    }


    protected abstract User getUserByToken(AuthenticationToken token);
}