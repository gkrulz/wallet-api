package com.wallet.api.core.service.impl;

import com.wallet.api.beans.PromoType;
import com.wallet.api.beans.Transaction;
import com.wallet.api.beans.TransactionRequest;
import com.wallet.api.beans.User;
import com.wallet.api.beans.Wallet;
import com.wallet.api.core.repository.WalletRepository;
import com.wallet.api.core.repository.impl.WalletRepositoryImpl;
import com.wallet.api.core.service.UserService;
import com.wallet.api.core.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
@Service
public class WalletServiceImpl implements WalletService {

    private static final Logger logger = LoggerFactory.getLogger(WalletRepositoryImpl.class);

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserService userService;

    @Override
    public synchronized Transaction credit(TransactionRequest request) {
        logger.info("[Wallet] Credit [{}] for wallet ID [{}] by user ID [{}]", request.getAmount(), request.getWalletId(), request.getUserId());

        // Process promo codes
        if (!Objects.isNull(request.getPromoType())) {
            addPromos(request);
        }

        // Save transaction
        Double balance = walletRepository.getBalance(request.getWalletId());
        Transaction transaction = walletRepository.saveTransaction(request, balance + request.getAmount());

        return transaction;
    }

    @Override
    public synchronized Transaction debit(TransactionRequest request) {
        logger.info("[Wallet] Debit [{}] from wallet ID [{}] by user ID [{}]", request.getAmount(), request.getWalletId(), request.getUserId());

        // Check if the funds are sufficient
        Double balance = walletRepository.getBalance(request.getWalletId());
        Transaction transaction;
        if (balance - request.getAmount() >= 50) {
            request.setAmount(request.getAmount() * -1);
            transaction = walletRepository.saveTransaction(request, balance + request.getAmount());
        } else {
            throw new RuntimeException("Insufficient Funds ");
        }

        return transaction;
    }

    @Override
    public synchronized Wallet checkBalance(String walletId) {

        Double balance = walletRepository.getBalance(walletId);

        Wallet wallet = new Wallet();
        wallet.setId(walletId);
        wallet.setBalance(balance);

        return wallet;
    }

    @Override
    public Wallet createWallet(Wallet wallet) {
        logger.info("[Wallet] create wallet for user {}", wallet.getUser().getId());

        User user = new User();
        user.setId(wallet.getUser().getId());
        user.setName(wallet.getUser().getName());
        userService.createUser(user);
        walletRepository.createUserWallet(wallet);

        return wallet;
    }

    public synchronized List<Transaction> getWalletHistory(String walletId) {
        logger.info("[Wallet] retrieving wallet history for wallet ID [{}]", walletId);

        return walletRepository.getWalletHistory(walletId);
    }

    private void addPromos(TransactionRequest request) {
        logger.info("[Wallet] Checking for promo codes");

        List<Transaction> transactions = walletRepository.getWalletHistory(request.getWalletId());

        if (request.getPromoType().equals(PromoType.WELCOME)) {
            Boolean firstTransactionMoreThan50 = false;
            for (Transaction t : transactions) {
                if (t.getCredit() > 50.0) {
                    firstTransactionMoreThan50 = true;
                }
            }

            if (!firstTransactionMoreThan50 && request.getAmount() > 50.0) {
                logger.info("Adding 20$ for 'WELCOME' promo code");

                Double balance = walletRepository.getBalance(request.getWalletId());
                TransactionRequest tr = new TransactionRequest();
                tr.setAmount(20.0);
                tr.setUserId(request.getUserId());
                tr.setWalletId(request.getWalletId());
                walletRepository.saveTransaction(tr, balance + 20.0);
            }
        }

        if (request.getPromoType().equals(PromoType.SALTSIDE)) {

            Double promoAmount = request.getAmount() * 0.10;
            if (promoAmount > 50.0) {
                promoAmount = 50.0;
            }

            logger.info("Adding {}$ for 'SALTSIDE' promo code", promoAmount);
            Double balance = walletRepository.getBalance(request.getWalletId());
            TransactionRequest tr = new TransactionRequest();
            tr.setAmount(promoAmount);
            tr.setUserId(request.getUserId());
            tr.setWalletId(request.getWalletId());
            walletRepository.saveTransaction(tr, balance + promoAmount);
        }
    }
}
