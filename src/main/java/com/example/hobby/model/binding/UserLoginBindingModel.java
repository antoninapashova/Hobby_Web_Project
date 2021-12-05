package com.example.hobby.model.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {
    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    @Size(min=3, max=20, message = "Username must be between 3 and 20 characters")
    @NotEmpty(message = "Username must not be empty")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Size(min=3, max=20,  message = "Username must be between 3 and 20 characters")
    @NotEmpty(message = "Password cannot be empty")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
