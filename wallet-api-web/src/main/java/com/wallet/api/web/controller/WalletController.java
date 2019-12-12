package com.wallet.api.web.controller;

import com.wallet.api.beans.Wallet;
import com.wallet.api.core.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
