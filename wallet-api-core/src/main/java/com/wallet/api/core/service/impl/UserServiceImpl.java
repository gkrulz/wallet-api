package com.wallet.api.core.service.impl;

import com.wallet.api.beans.User;
import com.wallet.api.core.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User createUser(User user) {
        return user;
    }
}
