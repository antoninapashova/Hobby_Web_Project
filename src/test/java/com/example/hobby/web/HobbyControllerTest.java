package com.example.hobby.web;

import com.example.hobby.model.entity.*;
import com.example.hobby.model.entity.enums.HobbyCategoryEnum;
import com.example.hobby.model.entity.enums.UserRoleEnum;
import com.example.hobby.repository.HobbyRepository;
import com.example.hobby.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WithMockUser("toni")
@SpringBootTest
@AutoConfigureMockMvc
class HobbyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private HobbyRepository testRepository;
    @Mock
    private UserRepository mockedUserRepository;

    @Mock
    private MultipartFile multipartFile;

    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;

    private UserRole userRole;
    private PictureEntity pictureEntity;

    @Mock
    private CategoryHobby categoryHobby;

    private HobbyCategoryEnum categoryEnum;

    private Hobby testHobby;

    @AfterEach
    public void deleteFromRepo() {
        testRepository.deleteAll();
    }

    @BeforeEach
    public void createHobby() {
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
        testHobby = new Hobby();
        testHobby.setTitle("Hobby1");
        testHobby.setCategory(categoryHobby);
        testHobby.setPhoto(pictureEntity);
        testHobby.setDescription("Hobby1 description");
        testRepository.save(testHobby);
    }

    @Test
    public void hobbyPage() throws Exception {
        mockMvc.
                perform(get("/hobbies/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-hobby"));
    }

}
