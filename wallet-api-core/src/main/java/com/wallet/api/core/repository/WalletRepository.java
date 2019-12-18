package com.wallet.api.core.repository;

import com.wallet.api.beans.Transaction;
import com.wallet.api.beans.TransactionRequest;
import com.wallet.api.beans.Wallet;

import java.util.List;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
public interface WalletRepository {

    Wallet createUserWallet(Wallet wallet);

    Transaction saveTransaction(TransactionRequest request, Double balance);

    Double getBalance(String walletId);

    List<Transaction> getWalletHistory(String walletId);
}
