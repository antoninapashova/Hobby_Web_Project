package com.example.hobby.web;

import com.example.hobby.model.dto.HobbyDTO;
import com.example.hobby.service.CloudinaryService;
import com.example.hobby.service.HobbyService;
import com.example.hobby.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class CategoryController {

    private final HobbyService hobbyService;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;

    public CategoryController(HobbyService hobbyService, CloudinaryService cloudinaryService, UserService userService) {
        this.hobbyService = hobbyService;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
    }

    @GetMapping("/music")
    public ResponseEntity<List<HobbyDTO>> getHobbyByMusicCategory() {

        List<HobbyDTO> hobbies=hobbyService.getAllHobbiesByMusicCategory();

        if (hobbies.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        } else {
            return ResponseEntity.ok(hobbies);
        }
    }

    @GetMapping("/photography")
    public ResponseEntity<List<HobbyDTO>> getHobbyByPhotographyCategory() {

        List<HobbyDTO> hobbies=hobbyService.getAllHobbiesByPhotographyCategory();

        if (hobbies.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        } else {
            return ResponseEntity.ok(hobbies);
        }
    }

    @GetMapping("/origami")
    public ResponseEntity<List<HobbyDTO>> getHobbyByOrigamiCategory() {

        List<HobbyDTO> hobbies=hobbyService.getAllHobbiesByOrigamiCategory();

        if (hobbies.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        } else {
            return ResponseEntity.ok(hobbies);
        }
    }

    @GetMapping("/pets")
    public ResponseEntity<List<HobbyDTO>> getHobbyByPetsCategory() {

        List<HobbyDTO> hobbies=hobbyService.getAllHobbiesByPetsCategory();

        if (hobbies.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        } else {
            return ResponseEntity.ok(hobbies);
        }
    }
}
