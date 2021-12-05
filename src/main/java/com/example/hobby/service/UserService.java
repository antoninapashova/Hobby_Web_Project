package com.example.hobby.service;

import com.example.hobby.model.dto.UserDTO;
import com.example.hobby.model.entity.User;
import com.example.hobby.model.service.UserRegisterServiceModel;
import com.example.hobby.model.service.UserServiceModel;
import java.util.List;

public interface UserService {
    void registerUser(UserRegisterServiceModel map);

    User getByUsername(String username);

    void initAdmin();

    UserServiceModel findById(Long id);

    List<UserDTO> getAllUsers();

    void deleteUser(Long id);
}
