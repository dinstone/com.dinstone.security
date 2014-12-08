
package com.dinstone.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

public interface Authentication extends Serializable {

    public Object getPrincipal();

    public Object getSubject();

    public Set<Permission> getPermissions();

    public void addPermissions(Collection<Permission> permissions);

}
