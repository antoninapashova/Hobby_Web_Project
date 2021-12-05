package com.example.hobby.web;

import com.example.hobby.model.binding.UserRegisterBindingModel;
import com.example.hobby.model.entity.User;
import com.example.hobby.model.service.UserRegisterServiceModel;
import com.example.hobby.model.view.UserViewModel;
import com.example.hobby.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class RegisterController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public RegisterController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterBindingModel userRegisterBindingModel, RedirectAttributes redirectAttributes,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        UserRegisterServiceModel serviceModel =
                modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

        userService.registerUser(serviceModel);

        return "redirect:login";
    }


    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel userModel() {
        return new UserRegisterBindingModel();
    }
}
