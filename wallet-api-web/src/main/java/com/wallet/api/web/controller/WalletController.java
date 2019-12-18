package com.wallet.api.web.controller;

import com.wallet.api.beans.Transaction;
import com.wallet.api.beans.TransactionRequest;
import com.wallet.api.beans.Wallet;
import com.wallet.api.core.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
@RestController
@RequestMapping("/v1/wallet")
public class WalletController {

    private static final Logger logger = LoggerFactory.getLogger(WalletController.class);

    @Autowired
    private WalletService walletService;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Wallet createUserWallet(@RequestBody Wallet wallet) {
        logger.info("Creating wallet for user {}", wallet.getUser().getId());

        return walletService.createWallet(wallet);
    }

    @PostMapping(value = "credit")
    @ResponseStatus(HttpStatus.OK)
    public Transaction credit(@RequestBody TransactionRequest request) {
        logger.info("[Wallet] credit [{}] for wallet ID [{}] by user ID [{}]", request.getAmount(), request.getWalletId(), request.getUserId());

        return walletService.credit(request);
    }

    @PostMapping(value = "debit")
    @ResponseStatus(HttpStatus.OK)
    public Transaction debit(@RequestBody TransactionRequest request) {
        logger.info("[Wallet] debit [{}] from wallet ID [{}] by user ID [{}]", request.getAmount(), request.getWalletId(), request.getUserId());

        return walletService.debit(request);
    }

    @GetMapping(value = "/{walletId}")
    @ResponseStatus(HttpStatus.OK)
    public Wallet getBalance(@PathVariable String walletId) {
        logger.info("[Wallet] retrieving balance for wallet ID [{}]", walletId);

        return walletService.checkBalance(walletId);
    }

    @GetMapping(value = "/{walletId}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getWalletHistory(@PathVariable String walletId) {
        logger.info("[Wallet] Request received to retrieve wallet history for wallet ID [{}]", walletId);

        return walletService.getWalletHistory(walletId);
    }
}
