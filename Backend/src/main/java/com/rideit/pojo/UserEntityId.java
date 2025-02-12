package com.rideit.pojo;

import java.io.Serializable;
import java.util.Objects;

import com.rideit.enumclass.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class UserEntityId implements Serializable{
	@Column(length = 100)
	private String email;
	@Enumerated(EnumType.STRING)
    private Role role;

    public UserEntityId() {}

    public UserEntityId(String email, Role role) {
        this.email = email;
        this.role = role;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntityId that = (UserEntityId) o;
        return Objects.equals(email, that.email) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, role);
    }
}
