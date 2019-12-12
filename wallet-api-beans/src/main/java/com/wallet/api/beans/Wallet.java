package com.wallet.api.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/6/19
 */

@JsonInclude(
      value = JsonInclude.Include.NON_NULL
)
@JsonIgnoreProperties(
      ignoreUnknown = true
)

public class Wallet implements Serializable {

    private static final long serialVersionUID = -5838222932933505471L;
    private String id;
    private String balance;
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return id.equals(wallet.id) &&
              balance.equals(wallet.balance) &&
              user.equals(wallet.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, user);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
              .append("id", id)
              .append("balance", balance)
              .append("user", user)
              .toString();
    }
}
