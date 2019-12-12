package com.wallet.api.core.service.impl;

import com.wallet.api.beans.User;
import com.wallet.api.beans.Wallet;
import com.wallet.api.core.repository.WalletRepository;
import com.wallet.api.core.repository.impl.UserRepositoryImpl;
import com.wallet.api.core.repository.impl.WalletRepositoryImpl;
import com.wallet.api.core.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
@Service
public class WalletServiceImpl  implements WalletService {

    private static final Logger logger = LoggerFactory.getLogger(WalletRepositoryImpl.class);

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public void credit(String userId, String walletId, Double amount) {

    }

    @Override
    public void debit(String userId, String walletId, Double amount) {

    }

    @Override
    public Double checkBalance(String userId, String walletId) {
        return null;
    }

    public Wallet createWallet(Wallet wallet) {
        logger.info("[Wallet] create wallet for user {}", wallet.getUser().getId());

        walletRepository.createWallet(wallet.getId());
        walletRepository.createUserWallet(wallet);

        return wallet;
    }
}
