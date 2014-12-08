
package com.dinstone.security.spi;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.dinstone.security.Authentication;
import com.dinstone.security.Permission;

public class DefaultAuthentication implements Authentication, Serializable {

    /** serialVersionUID = 1L */
    private static final long serialVersionUID = 1L;

    /** 当事人标识信息 */
    private Object principal;

    /** 当事人主体信息 */
    private Object subject;

    private Set<Permission> permissions = new HashSet<Permission>();

    public DefaultAuthentication() {
        super();
    }

    public DefaultAuthentication(Object principal, Object subject) {
        this.principal = principal;
        this.subject = subject;
    }

    @Override
    public void addPermissions(Collection<Permission> permissions) {
        if (permissions != null) {
            this.permissions.addAll(permissions);
        }
    }

    @Override
    public Set<Permission> getPermissions() {
        return permissions;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getSubject() {
        return subject;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public void setSubject(Object subject) {
        this.subject = subject;
    }

    public void setPermissions(Set<Permission> permissions) {
        addPermissions(permissions);
    }

}
