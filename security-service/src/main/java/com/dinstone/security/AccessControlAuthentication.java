
package com.dinstone.security;

public interface AccessControlAuthentication {

    /**
     * authenticate the account.
     *
     * @param account
     * @return
     */
    public Authentication authenticate(Account account);

}
