
package com.dinstone.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public interface Authentication extends Serializable {

    public String getToken();

    public Object getPrincipal();

    public Object getSubject();

    public int getExpiration();

    public Set<Permission> getPermissions();

    public void addPermissions(Collection<Permission> permissions);

}
