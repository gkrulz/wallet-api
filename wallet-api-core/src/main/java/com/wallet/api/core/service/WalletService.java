package com.wallet.api.core.service;

import com.wallet.api.beans.Transaction;
import com.wallet.api.beans.TransactionRequest;
import com.wallet.api.beans.Wallet;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
public interface WalletService {

    Transaction credit(TransactionRequest request);

    void debit(TransactionRequest request);

    Double checkBalance(String userId, String walletId);

    Wallet createWallet(Wallet wallet);
}
