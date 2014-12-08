
package com.dinstone.security.spi;

import java.io.Serializable;

import com.dinstone.security.Permission;

public class DefaultPermission implements Permission, Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    private String operation;

    public DefaultPermission() {
        super();
    }

    public DefaultPermission(String operation) {
        this.operation = operation;
    }

    @Override
    public boolean implies(Permission permission) {
        if (permission == this) {
            return true;
        }

        if (permission instanceof DefaultPermission) {
            DefaultPermission other = (DefaultPermission) permission;
            this.operation.equals(other.operation);
        }

        return false;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((operation == null) ? 0 : operation.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DefaultPermission other = (DefaultPermission) obj;
        if (operation == null) {
            if (other.operation != null) {
                return false;
            }
        } else if (!operation.equals(other.operation)) {
            return false;
        }
        return true;
    }

}