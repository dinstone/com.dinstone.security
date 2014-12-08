
package com.dinstone.security.model;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private int roleId;

    private String name;

    private String description;

    private List<Privilege> privList;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Privilege> getPrivList() {
        return privList;
    }

    public void setPrivList(List<Privilege> privList) {
        this.privList = privList;
    }

}