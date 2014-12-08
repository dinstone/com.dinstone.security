
package com.dinstone.security.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;

import com.dinstone.security.AccessControlAuthorization;
import com.dinstone.security.Authentication;

public class AuthorizationHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationHandlerInterceptor.class);

    private static final String AUTHENTICATION_KEY = Authentication.class.getName();

    private AccessControlAuthorization accessControlAuthorization;

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String operation = urlPathHelper.getLookupPathForRequest(request);
        logger.info("access control intercept a request operation[{}]", operation);

        Authentication authentication = (Authentication) WebUtils.getSessionAttribute(request, AUTHENTICATION_KEY);
        accessControlAuthorization.authorize(authentication, operation);
        return true;
    }

    public void setAccessControlAuthorization(AccessControlAuthorization accessControlAuthorization) {
        this.accessControlAuthorization = accessControlAuthorization;
    }

}
