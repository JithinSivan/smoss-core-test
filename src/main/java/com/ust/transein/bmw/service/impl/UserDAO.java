package com.ust.transein.bmw.service.impl;

import com.ust.transein.bmw.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User checkUser(String username) {
        String findUserQuery = "\n" +
                "select username, password, enabled from customer_master \n"
                + "where username=? \n";

        User userInfo = (User) jdbcTemplate.queryForObject(findUserQuery, new Object[]{username},
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setEnabled(rs.getBoolean("enabled"));
                        return user;
                    }
                });
        String findUserRole = "\n" +
                "select auth.name from customer_master cust \n" +
                "inner join customer_authority user_auth on\n" +
                "(cust.id=user_auth.user_id) \n" +
                "inner join authority_name auth on(auth.id=user_auth.authority_id) where cust.username=? ";

        List<String> userRoles = jdbcTemplate.query(findUserRole, new Object[]{username},
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getString("name");
                    }
                });
        userInfo.setAuthorities(userRoles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList()));
        return userInfo;
    }


}