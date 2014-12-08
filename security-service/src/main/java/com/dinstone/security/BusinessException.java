
package com.dinstone.security;

/**
 * BusinessException表示违法业务规则导致的异常，需要调用者根据异常类型来处理；
 *
 * @author guojinfei
 * @version 1.0.0
 */
public class BusinessException extends ApplicationException {

    /**  */
    private static final long serialVersionUID = 1L;

    public BusinessException(ExceptionType errorType) {
        super(errorType);
    }

    public BusinessException(ExceptionType errorType, Throwable cause) {
        super(errorType, cause);
    }

}
