package com.example.hobby.web;

import com.example.hobby.model.dto.HobbyDTO;
import com.example.hobby.model.dto.UserDTO;
import com.example.hobby.model.entity.CategoryHobby;
import com.example.hobby.model.entity.User;
import com.example.hobby.service.CloudinaryService;
import com.example.hobby.service.HobbyService;
import com.example.hobby.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/statistic")
public class StatsController {

    private final HobbyService hobbyService;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;

    public StatsController(HobbyService hobbyService, CloudinaryService cloudinaryService, UserService userService) {
        this.hobbyService = hobbyService;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<HobbyDTO>> getAllHobbies(){
        List<HobbyDTO> allHobbies = hobbyService.getAllHobbies();
        return ResponseEntity.ok(allHobbies);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<HobbyDTO>> getHobbyByUsername(@PathVariable ("username") String username) {

       List<HobbyDTO> hobbies=hobbyService.getAllHobiesByUsername(username);

        if (hobbies.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        } else {
            return ResponseEntity.ok(hobbies);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HobbyDTO> deleteHobby(@PathVariable("id") Long id) {
        hobbyService.deleteHobby(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO>> getUsersByUsername() {

        List<UserDTO> users=userService.getAllUsers();

        if (users.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
