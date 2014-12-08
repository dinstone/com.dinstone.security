
package com.dinstone.security.dao;

import com.dinstone.security.model.User;

public interface AccessControlDao {

    User findUserByName(String username);

}
