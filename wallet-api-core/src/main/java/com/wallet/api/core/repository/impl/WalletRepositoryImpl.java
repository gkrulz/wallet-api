package com.wallet.api.core.repository.impl;

import com.wallet.api.beans.Wallet;
import com.wallet.api.core.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
@Repository
public class WalletRepositoryImpl implements WalletRepository {

    @Autowired
    private DataSource dataSource;

    private static final Logger logger = LoggerFactory.getLogger(WalletRepositoryImpl.class);

    public Wallet createUserWallet(Wallet wallet) {
        String userId = wallet.getUser().getId();
        logger.info("[Wallet] Saving user wallet for userId {}", userId);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", wallet.getId());
        parameters.addValue("userId", userId);

        try {
            String query = "INSERT INTO WALLET_USER (WALLET_ID, USER_ID) VALUES (:id, :userId)";
            namedParameterJdbcTemplate.update(query, parameters);
        } catch (Exception e) {
            logger.error("Failed to create wallet ", e);
        }

        return wallet;
    }

    public String createWallet(String walletId) {
        logger.info("[Wallet] Saving wallet");

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", walletId);
        parameters.addValue("balance", 0);

        try {
            String query = "INSERT INTO WALLET(ID, BALANCE) VALUES (:id, :balance)";
            namedParameterJdbcTemplate.update(query, parameters);
        } catch (Exception e) {
            logger.error("Failed to create wallet ", e);
        }

        return walletId;
    }
}
