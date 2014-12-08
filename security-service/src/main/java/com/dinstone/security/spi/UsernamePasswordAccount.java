
package com.dinstone.security.spi;

import java.io.Serializable;

import com.dinstone.security.Account;

public class UsernamePasswordAccount implements Account, Serializable {

    /** serialVersionUID = 1L */
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    public UsernamePasswordAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getPrincipal() {
        return username;
    }

    @Override
    public String getCredential() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
