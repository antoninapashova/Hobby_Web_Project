package com.example.hobby.service.impl;

import com.example.hobby.model.entity.CategoryHobby;
import com.example.hobby.model.entity.UserRole;
import com.example.hobby.model.entity.enums.HobbyCategoryEnum;
import com.example.hobby.model.entity.enums.UserRoleEnum;
import com.example.hobby.repository.UserRepository;
import com.example.hobby.repository.UserRoleRepository;
import com.example.hobby.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRepository mockedUserRepository, UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initUserRoles() {
        if (userRoleRepository.count() != 0) {
            return;
        }
        Arrays.stream(UserRoleEnum.values())
                .forEach(userRoleEnum-> {
                    UserRole userRole = new UserRole();
                   userRole.setRole(userRoleEnum);
                    userRoleRepository.save(userRole);
                });

    }
}
