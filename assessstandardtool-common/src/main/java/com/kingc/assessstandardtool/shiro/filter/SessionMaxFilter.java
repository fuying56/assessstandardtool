package com.kingc.assessstandardtool.shiro.filter;


import com.kingc.assessstandardtool.shiro.utils.ShiroSessionUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author 作者 z77z
 * @date 创建时间：2017年3月5日 下午1:16:38
 * 思路：
 * 1.读取当前登录用户名，获取在缓存中的sessionId队列
 * 2.判断队列的长度，大于最大登录限制的时候，按踢出规则
 * 将之前的sessionId中的session域中存入kickout：true，并更新队列缓存
 * 3.判断当前登录的session域中的kickout如果为true，
 * 想将其做退出登录处理，然后再重定向到踢出登录提示页面
 */

public class SessionMaxFilter extends AccessControlFilter {

    @Getter
    @Setter
    private String kickOutUrl;

    @Getter
    @Setter
    private int maxSession = 1;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }

        LinkedList<Session> linkedList = new LinkedList<>();
        Object principal = subject.getPrincipals().getPrimaryPrincipal();

        /*获取全部session*/
        Collection<Session> sessions = ShiroSessionUtils.getActivaSession();

        /*对比当前session*/
        for (Session session : sessions) {
            SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != simplePrincipalCollection) {
                Object primaryPrincipal = simplePrincipalCollection.getPrimaryPrincipal();
                if (null != primaryPrincipal && null != principal && principal.equals(primaryPrincipal)) {
                    linkedList.add(session);
                }
            }
        }

        if (!linkedList.isEmpty() && linkedList.size() > maxSession) {
            Collections.reverse(linkedList);

            int a = linkedList.size() - maxSession;
            for (int i = 0; i < a; i++) {
                ShiroSessionUtils.delete(linkedList.get(i).getId());
            }
            return false;
        }
        return true;
    }
}
