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

public class User implements Serializable {

    private static final long serialVersionUID = 995106075558576453L;
    private String id;
    private String name;

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
              name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
              .append("id", id)
              .append("name", name)
              .toString();
    }
}
