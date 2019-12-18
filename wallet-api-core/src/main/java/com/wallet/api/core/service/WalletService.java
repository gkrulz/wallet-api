package com.wallet.api.core.service;

import com.wallet.api.beans.Transaction;
import com.wallet.api.beans.TransactionRequest;
import com.wallet.api.beans.Wallet;

import java.util.List;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
public interface WalletService {

    Transaction credit(TransactionRequest request);

    Transaction debit(TransactionRequest request);

    Wallet checkBalance(String walletId);

    Wallet createWallet(Wallet wallet);

    List<Transaction> getWalletHistory(String walletId);
}
