package com.wallet.api.core.repository.impl;

import com.wallet.api.beans.Transaction;
import com.wallet.api.beans.TransactionRequest;
import com.wallet.api.beans.Wallet;
import com.wallet.api.core.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
            String query = "INSERT INTO WALLET (ID, USER_ID) VALUES (:id, :userId)";
            namedParameterJdbcTemplate.update(query, parameters);
        } catch (Exception e) {
            logger.error("Failed to create wallet ", e);
        }

        return wallet;
    }

    public Transaction saveTransaction(TransactionRequest request, Double balance) {
        logger.info("[Wallet] Saving transaction for wallet ID [{}] by user ID [{}]", request.getWalletId(), request.getUserId());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        Long timeStamp = System.currentTimeMillis();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", UUID.randomUUID().toString());
        parameters.addValue("walletId", request.getWalletId());
        parameters.addValue("debit", request.getAmount() > 0 ? 0 : request.getAmount());
        parameters.addValue("credit", request.getAmount() > 0 ? request.getAmount() : 0);
        parameters.addValue("balance", balance);
        parameters.addValue("date", timeStamp);

        try {
            String query = "INSERT INTO WALLET_HISTORY (ID, WALLET_ID, DEBIT, CREDIT, BALANCE, DATE_TS) " +
                  "VALUES (:id, :walletId, :debit, :credit, :balance, :date)";
            namedParameterJdbcTemplate.update(query, parameters);
        } catch (Exception e) {
            logger.error("Failed to complete the transaction ", e);
        }

        Transaction transaction = new Transaction();
        transaction.setId(request.getId());
        transaction.setCredit(request.getAmount() > 0 ? request.getAmount() : 0);
        transaction.setDebit(request.getAmount() > 0 ? 0 : request.getAmount());
        transaction.setBalance(balance);
        transaction.setDateTs(timeStamp);

        return transaction;
    }

    public Double getBalance(String walletId) {
        logger.info("[Wallet] Retrieving wallet balance for wallet ID [{}]", walletId);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", walletId);

        Map<String, Object> results = null;
        try {
            String query = "SELECT BALANCE FROM WALLET_HISTORY WHERE WALLET_ID=:id ORDER BY DATE_TS DESC LIMIT 1";
            results = namedParameterJdbcTemplate.queryForMap(query, parameters);
        } catch (Exception e) {
            logger.error("Failed to retrieve the wallet balance ", e);
        }

        logger.info("result - {}", results);
        return results == null ? 0 : Double.parseDouble(results.get("BALANCE").toString());
    }
}
