
package com.dinstone.security;

public interface Account {

    /**
     * 当事人标识
     *
     * @return
     */
    public Object getPrincipal();

    /**
     * 信任凭证
     *
     * @return
     */
    public Object getCredential();

}
