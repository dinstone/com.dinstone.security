
package com.dinstone.security.web.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;

import com.dinstone.security.Authentication;
import com.dinstone.security.SecurityException;
import com.dinstone.security.SecurityExceptionType;

public class AuthenticationHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationHandlerInterceptor.class);

    private static final String AUTHENTICATION_KEY = Authentication.class.getName();

    private UrlPathHelper urlPathHelper = new UrlPathHelper();

    private List<String> ignoredOperations = new ArrayList<String>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String operation = urlPathHelper.getLookupPathForRequest(request);
        logger.info("authentication intercept a request operation[{}]", operation);

        // ignored operation
        if (!ignoredOperations.contains(operation)) {
            Authentication authentication = (Authentication) WebUtils.getSessionAttribute(request, AUTHENTICATION_KEY);
            if (authentication == null) {
                throw new SecurityException(SecurityExceptionType.UNAUTHORIZED);
            }
        }
        return true;
    }

    public void setIgnoredOperations(List<String> ignoredOperations) {
        if (ignoredOperations != null) {
            this.ignoredOperations.addAll(ignoredOperations);
        }
    }

}
