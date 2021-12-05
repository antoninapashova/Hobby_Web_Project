package com.example.hobby.web;

import com.example.hobby.model.entity.User;
import com.example.hobby.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc.
                perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    private static final String TEST_USER_USERNAME = "toniii";
    private static final String TEST_USER_EMAIL = "tonii@abv.bg";

    @Test
    void testRegisterUser() throws Exception {
        mockMvc.perform(post("/users/register").
                param("username",TEST_USER_USERNAME).
                param("firstName","Antonina").
                param("lastName","Pashova").
                param("email", "tonii@abv.bg").
                param("password","12345").
                param("confirmPassword","12345").
                with(csrf()).
                contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2, userRepository.count());

        Optional<User> newlyCreatedUserOpt = userRepository.findByUsername(TEST_USER_USERNAME);

        Assertions.assertTrue(newlyCreatedUserOpt.isPresent());

        User newlyCreatedUser = newlyCreatedUserOpt.get();

        Assertions.assertEquals(TEST_USER_EMAIL, newlyCreatedUser.getEmail());

    }
}
