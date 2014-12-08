
package com.dinstone.security;

public interface Permission {

    boolean implies(Permission permission);
}
