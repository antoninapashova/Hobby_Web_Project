package com.example.hobby.web;

import com.example.hobby.model.view.HobbyViewModel;
import com.example.hobby.model.view.UserViewModel;
import com.example.hobby.service.HobbyService;
import com.example.hobby.service.UserService;
import com.example.hobby.service.impl.HobbyUserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class PController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final HobbyService hobbyService;

    public PController(UserService userService, ModelMapper modelMapper, HobbyService hobbyService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.hobbyService = hobbyService;
    }

    @GetMapping("/profile")
    private String profile(Model model, @AuthenticationPrincipal HobbyUserImpl user){
        model
                .addAttribute("user",modelMapper.map(userService.getByUsername(user.getUsername()), UserViewModel.class));
        return "profile";
    }

    @GetMapping("/userinfo")
    public String showUserHobbies(Model model, @AuthenticationPrincipal HobbyUserImpl user){
        List<HobbyViewModel> allHobbies = hobbyService.getAllHobbiesOfUser(user.getUserIdentifier()).stream()
                .map(hobbyServiceModel->modelMapper.map(hobbyServiceModel, HobbyViewModel.class)).collect(Collectors.toList());

        model.addAttribute("hobbies", allHobbies);
        return "hobbies";
    }



}
