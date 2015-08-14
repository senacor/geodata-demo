package com.senacor.geodata.model;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * @author dschmitz
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name ="user_email_uniq", columnNames = "email"))
public class User {
    @Id
    private String id;

    @NotNull
    private String email;

    @SuppressWarnings("unused") // yeah JPA!
    User() {
    }

    public User(@Nonnull String id, @Nonnull String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setId(@Nonnull String id) {
        this.id = id;
    }

    public void setEmail(@Nonnull String newEmail) {
        email = newEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
