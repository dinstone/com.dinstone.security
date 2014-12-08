
package com.dinstone.security.web.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.dinstone.security.ApplicationException;
import com.dinstone.security.BusinessException;
import com.dinstone.security.ExceptionType;
import com.dinstone.security.SecurityException;
import com.dinstone.security.SecurityExceptionType;

public class ApplicationHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        try {
            if (ex instanceof SecurityException) {
                ExceptionType et = ((SecurityException) ex).getExceptionType();
                if (et != null && et == SecurityExceptionType.DENIED) {
                    response.setHeader("AC-Status", SecurityExceptionType.DENIED.name());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "access denied");
                } else if (et != null && et == SecurityExceptionType.UNAUTHORIZED) {
                    response.setHeader("AC-Status", SecurityExceptionType.UNAUTHORIZED.name());
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "session invalidation or not logged in");
                } else {
                    response.setHeader("AC-Status", SecurityExceptionType.FORBIDDEN.name());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "access forbidden");
                }
                return new ModelAndView();
            } else if (ex instanceof BusinessException) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();
            } else if (ex instanceof ApplicationException) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
                return new ModelAndView();
            }
        } catch (Exception e) {
            logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", e);
        }
        return null;
    }
}
