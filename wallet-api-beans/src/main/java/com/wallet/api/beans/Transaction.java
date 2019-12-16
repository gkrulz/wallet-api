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
public class Transaction implements Serializable {

    private static final long serialVersionUID = -3893034055778819208L;
    private String id;
    private Double credit;
    private Double debit;
    private Double balance;
    private Long dateTs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getDateTs() {
        return dateTs;
    }

    public void setDateTs(Long dateTs) {
        this.dateTs = dateTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id.equals(that.id) &&
              credit.equals(that.credit) &&
              debit.equals(that.debit) &&
              balance.equals(that.balance) &&
              dateTs.equals(that.dateTs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, credit, debit, balance, dateTs);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
              .append("id", id)
              .append("credit", credit)
              .append("debit", debit)
              .append("balance", balance)
              .append("dateTs", dateTs)
              .toString();
    }
}
