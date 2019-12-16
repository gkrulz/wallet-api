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
public class PromoCode implements Serializable {

    private static final long serialVersionUID = 2723144368495792535L;
    private String id;
    private PromoType promoType;
    private int percentage;
    private Double amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PromoType getPromoType() {
        return promoType;
    }

    public void setPromoType(PromoType promoType) {
        this.promoType = promoType;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoCode promoCode = (PromoCode) o;
        return percentage == promoCode.percentage &&
              id.equals(promoCode.id) &&
              promoType == promoCode.promoType &&
              amount.equals(promoCode.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, promoType, percentage, amount);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
              .append("id", id)
              .append("promoType", promoType)
              .append("percentage", percentage)
              .append("amount", amount)
              .toString();
    }
}
