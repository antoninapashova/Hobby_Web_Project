package com.example.hobby.init;

import com.example.hobby.service.CategoryHobbyService;
import com.example.hobby.service.UserRoleService;
import com.example.hobby.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDatabase implements CommandLineRunner {


    private final CategoryHobbyService categoryHobbyService;
    private final UserRoleService userRoleService;
    private final UserService userService;

    public InitDatabase(CategoryHobbyService categoryHobbyService, UserRoleService userRoleService, UserService userService) {
        this.categoryHobbyService = categoryHobbyService;
        this.userRoleService = userRoleService;

        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        categoryHobbyService.initCategories();
       userRoleService.initUserRoles();
       userService.initAdmin();
    }
}
