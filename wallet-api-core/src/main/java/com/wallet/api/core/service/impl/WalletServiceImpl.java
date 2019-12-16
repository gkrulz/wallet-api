package com.wallet.api.core.service.impl;

import com.wallet.api.beans.Transaction;
import com.wallet.api.beans.TransactionRequest;
import com.wallet.api.beans.User;
import com.wallet.api.beans.Wallet;
import com.wallet.api.core.repository.WalletRepository;
import com.wallet.api.core.repository.impl.UserRepositoryImpl;
import com.wallet.api.core.repository.impl.WalletRepositoryImpl;
import com.wallet.api.core.service.UserService;
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

    @Autowired
    private UserService userService;

    @Override
    public Transaction credit(TransactionRequest request) {
        logger.info("[Wallet] Credit [{}] for wallet ID [{}] by user ID [{}]", request.getAmount(), request.getWalletId(), request.getUserId());

        // TODO Process promo codes

        // TODO call credit repo method
        Double balance = walletRepository.getBalance(request.getWalletId());
        Transaction transaction = walletRepository.saveTransaction(request, balance + request.getAmount());

        return transaction;
    }

    @Override
    public void debit(TransactionRequest request) {

    }

    @Override
    public Double checkBalance(String userId, String walletId) {
        return null;
    }

    public Wallet createWallet(Wallet wallet) {
        logger.info("[Wallet] create wallet for user {}", wallet.getUser().getId());

        User user = new User();
        user.setId(wallet.getUser().getId());
        user.setName(wallet.getUser().getName());
        userService.createUser(user);
        walletRepository.createUserWallet(wallet);

        return wallet;
    }
}
