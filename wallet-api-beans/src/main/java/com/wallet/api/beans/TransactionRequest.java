package com.wallet.api.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Padmaka Wijayagoonawardena
 * Date: 12/13/19
 */

@JsonInclude(
      value = JsonInclude.Include.NON_NULL
)
@JsonIgnoreProperties(
      ignoreUnknown = true
)
public class TransactionRequest implements Serializable {

    private static final long serialVersionUID = 6414899528722809307L;
    private String id;
    private String userId;
    private String walletId;
    private Double amount;
    private PromoType promoType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PromoType getPromoType() {
        return promoType;
    }

    public void setPromoType(PromoType promoType) {
        this.promoType = promoType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionRequest that = (TransactionRequest) o;
        return id.equals(that.id) &&
              userId.equals(that.userId) &&
              walletId.equals(that.walletId) &&
              amount.equals(that.amount) &&
              promoType == that.promoType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, walletId, amount, promoType);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
              .append("id", id)
              .append("userId", userId)
              .append("walletId", walletId)
              .append("amount", amount)
              .append("promoType", promoType)
              .toString();
    }
}
