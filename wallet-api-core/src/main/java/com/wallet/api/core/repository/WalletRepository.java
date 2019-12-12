package com.wallet.api.core.repository;

import com.wallet.api.beans.Wallet;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
public interface WalletRepository {

    Wallet createUserWallet(Wallet wallet);

    String createWallet(String walletId);
}
