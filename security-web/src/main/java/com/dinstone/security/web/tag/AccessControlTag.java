
package com.dinstone.security.web.tag;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import com.dinstone.security.AccessControlAuthorization;
import com.dinstone.security.Authentication;

public class AccessControlTag extends TagSupport {

    /** serialVersionUID = 1L */
    private static final long serialVersionUID = 1L;

    private String operation;

    private String decision = "accessControlAuthorization";

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public int doEndTag() throws JspException {
        return Tag.EVAL_PAGE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
     */
    @Override
    public int doStartTag() throws JspException {
        ServletContext servletContext = pageContext.getServletContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        WebApplicationContext wac = RequestContextUtils.getWebApplicationContext(request, servletContext);

        AccessControlAuthorization accessControlAuthorization = (AccessControlAuthorization) wac.getBean(decision);
        Authentication ae = (Authentication) WebUtils.getSessionAttribute(request, Authentication.class.getName());
        try {
            accessControlAuthorization.authorize(ae, operation);
        } catch (SecurityException e) {
            return Tag.SKIP_BODY;
        }

        return Tag.EVAL_BODY_INCLUDE;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

}
