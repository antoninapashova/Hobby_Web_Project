package com.example.hobby.service.impl;

import com.example.hobby.model.entity.User;
import com.example.hobby.model.entity.UserRole;
import com.example.hobby.model.entity.enums.UserRoleEnum;
import com.example.hobby.repository.UserRepository;
import com.example.hobby.repository.UserRoleRepository;
import com.example.hobby.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private User testUser;
    private UserRole userRole;
    @Mock
    UserRepository mockedUserRepository;
    @Mock
    UserRoleRepository mockedUserRoleRepository;
    @Mock
    HobbyUserServiceImpl mockedHobbyUserService;
    @Mock
    PasswordEncoder mockedPasswordEncoder;
    @Mock
    ModelMapper mockedModelMapper;

    UserService serviceToTest;

    @BeforeEach
    public void init() {
        serviceToTest = new UserServiceImpl(mockedUserRepository, mockedUserRoleRepository,mockedHobbyUserService,
                mockedPasswordEncoder, mockedModelMapper);
        testUser = new User();
        userRole = new UserRole();
        userRole.setRole(UserRoleEnum.USER);
        testUser.setUsername("toni");
        testUser.setFirstName("Toni");
        testUser.setLastName("Toni");
        testUser.setEmail("toni@abv.bg");
        testUser.setPassword("12345");
        testUser.setRoles(Set.of(userRole));
        this.mockedUserRepository.save(testUser);
    }

    @Test
    void testUserNotFound() {
        Assertions.assertNull(serviceToTest.getByUsername("invalid username"));
    }

    @AfterEach
    public void deleteUser(){
        mockedUserRepository.deleteAll();
    }

    @Test
    public void userService_GetUserByUsername() {
        Mockito.when(this.mockedUserRepository
                .findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));
        User actual = serviceToTest.getByUsername("toni");

        Assert.assertEquals(actual.getUsername(),
                testUser.getUsername());
    }



}
