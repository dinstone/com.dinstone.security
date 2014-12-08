
package com.dinstone.security.spi;

import java.util.Set;

import com.dinstone.security.AccessControlAuthentication;
import com.dinstone.security.AccessControlAuthorization;
import com.dinstone.security.Account;
import com.dinstone.security.Authentication;
import com.dinstone.security.Permission;
import com.dinstone.security.SecurityException;
import com.dinstone.security.SecurityExceptionType;

public abstract class AccessControlAdapter implements AccessControlAuthentication, AccessControlAuthorization {

    @Override
    public Authentication authenticate(Account account) {
        // check Principal
        Object subject = checkPrincipal(account);
        if (subject == null) {
            throw new SecurityException(SecurityExceptionType.INVALID_PRINCIPAL).setProperty("account", account);
        }

        // check Credential
        if (!checkCredential(account, subject)) {
            throw new SecurityException(SecurityExceptionType.INVALID_CREDENTIAL).setProperty("account", account);
        }

        Authentication authentication = createAuthentication(account, subject);
        Set<Permission> permissions = findPermissions(subject);
        if (permissions != null) {
            authentication.addPermissions(permissions);
        }

        return authentication;
    }

    @Override
    public void authorize(Authentication authentication, Object operation) {
        if (operation == null) {
            return;
        }

        Permission expectPermission = checkOperationPermission(operation);
        if (expectPermission == null) {
            return;
        }

        if (authentication != null) {
            Set<Permission> hadPermissions = authentication.getPermissions();
            for (Permission permission : hadPermissions) {
                if (permission.implies(expectPermission)) {
                    return;
                }
            }
        }

        SecurityException securityException = new SecurityException(SecurityExceptionType.DENIED);
        securityException.setProperty("authentication", authentication);
        securityException.setProperty("operation", operation);
        throw securityException;
    }

    /**
     * create authentication object.
     *
     * @param account
     * @param subject
     * @return
     */
    protected abstract Authentication createAuthentication(Account account, Object subject);

    /**
     * check operation's permission.
     *
     * @param operation
     * @return
     */
    protected abstract Permission checkOperationPermission(Object operation);

    /**
     * check principal and return subject.
     *
     * @param account
     * @return if the principal is valid then return Subject, otherwise return null.
     */
    protected abstract Object checkPrincipal(Account account);

    /**
     * check credential.
     *
     * @param account
     * @param subject
     * @return
     */
    protected abstract boolean checkCredential(Account account, Object subject);

    /**
     * find subject's permissions.
     *
     * @param subject
     * @return
     */
    protected abstract Set<Permission> findPermissions(Object subject);

}
