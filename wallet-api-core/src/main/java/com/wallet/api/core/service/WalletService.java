package com.wallet.api.core.service;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
public interface WalletService {

    void credit(String userId, String walletId, Double amount);

    void debit(String userId, String walletId, Double amount);

    Double checkBalance(String userId, String walletId);
}
