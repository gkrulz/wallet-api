package com.wallet.api.core.repository.impl;

import com.wallet.api.beans.User;
import com.wallet.api.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public User saveUser(User user) {
        logger.info("[User] Saving user {}", user.getId());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", user.getId());
        parameters.addValue("name", user.getName());

        try {
            String query = "INSERT INTO USER (ID, NAME) VALUES (:id, :name)";
            namedParameterJdbcTemplate.update(query, parameters);
        } catch (Exception e) {
            logger.error("Failed to create user ", e);
        }

        return user;
    }
}
