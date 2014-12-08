
package com.dinstone.security.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dinstone.security.dao.AccessControlDao;
import com.dinstone.security.model.User;

public class AccessControlDaoImpl implements AccessControlDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findUserByName(String username) {
        String sql = "SELECT * FROM tb_user u WHERE u.username = ?";
        List<User> ul = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class), username);
        if (ul != null && ul.size() > 0) {
            return ul.get(0);
        }
        return null;
    }

}
