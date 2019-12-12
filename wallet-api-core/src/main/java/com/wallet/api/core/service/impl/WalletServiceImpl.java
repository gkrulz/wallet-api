package com.wallet.api.core.service.impl;

import com.wallet.api.core.service.WalletService;
import org.springframework.stereotype.Service;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
@Service
public class WalletServiceImpl  implements WalletService {

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
}
