
package com.dinstone.security.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.dinstone.security.Account;
import com.dinstone.security.Authentication;
import com.dinstone.security.Permission;
import com.dinstone.security.SecurityException;
import com.dinstone.security.SecurityExceptionType;
import com.dinstone.security.dao.AccessControlDao;
import com.dinstone.security.model.User;
import com.dinstone.security.service.AccessControlService;
import com.dinstone.security.spi.AccessControlAdapter;
import com.dinstone.security.spi.DefaultAuthentication;

public class AccessControlServiceImpl extends AccessControlAdapter implements AccessControlService {

    @Autowired
    private AccessControlDao accessControlDao;

    @Override
    protected Permission checkOperationPermission(Object operation) {
        Permission permission = null;
        // permission = userDao.findOperationPermission(operation);
        return permission;
    }

    @Override
    protected Object checkPrincipal(Account account) {
        String username = (String) account.getPrincipal();
        if (StringUtils.isEmpty(username)) {
            throw new SecurityException(SecurityExceptionType.INVALID_PRINCIPAL);
        }
        return accessControlDao.findUserByName(username);
    }

    @Override
    protected boolean checkCredential(Account account, Object subject) {
        String password = (String) account.getCredential();
        if (StringUtils.isEmpty(password)) {
            throw new SecurityException(SecurityExceptionType.INVALID_CREDENTIAL);
        }

        User user = (User) subject;
        if (password.equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    protected Set<Permission> findPermissions(Object subject) {
        Set<Permission> permissions = new HashSet<Permission>();
        // permissions = userDao.findPermissions(user.getUsername());
        return permissions;
    }

    @Override
    protected Authentication createAuthentication(Account account, Object subject) {
        return new DefaultAuthentication(account.getPrincipal(), subject);
    }

}
