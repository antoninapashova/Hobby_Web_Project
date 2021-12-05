package com.example.hobby.model.entity;

import com.example.hobby.model.entity.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{
    private UserRoleEnum role;

    public UserRole() {
    }

    @Enumerated(EnumType.STRING)
    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

}
