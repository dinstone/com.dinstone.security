
package com.dinstone.security;

public class SecurityException extends ApplicationException {

    /**  */
    private static final long serialVersionUID = 1L;

    public SecurityException(ExceptionType errorType) {
        super(errorType);
    }

    public SecurityException(ExceptionType errorType, Throwable cause) {
        super(errorType, cause);
    }

}
