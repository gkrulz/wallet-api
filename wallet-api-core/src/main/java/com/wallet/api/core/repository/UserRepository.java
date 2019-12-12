package com.wallet.api.core.repository;

import com.wallet.api.beans.User;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/12/19
 */
public interface UserRepository {

    User saveUser(User user);
}
