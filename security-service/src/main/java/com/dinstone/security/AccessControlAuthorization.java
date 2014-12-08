
package com.dinstone.security;

public interface AccessControlAuthorization {

    /**
     * authorize the operation.
     * 
     * @param authentication
     * @param operation
     */
    void authorize(Authentication authentication, Object operation);
}
