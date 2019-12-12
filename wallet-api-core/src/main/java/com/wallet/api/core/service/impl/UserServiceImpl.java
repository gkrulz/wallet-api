package com.wallet.api.core.service.impl;

import com.wallet.api.beans.User;
import com.wallet.api.core.repository.UserRepository;
import com.wallet.api.core.repository.impl.UserRepositoryImpl;
import com.wallet.api.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        logger.info("[User] Creating user {}", user.getId());

        return userRepository.saveUser(user);
    }
}
