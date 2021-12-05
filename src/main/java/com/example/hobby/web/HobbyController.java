package com.example.hobby.web;

import com.example.hobby.model.binding.HobbyAddBindingModel;
import com.example.hobby.model.service.HobbyAddServiceModel;
import com.example.hobby.service.HobbyService;
import com.example.hobby.service.impl.HobbyUserImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/hobbies")
public class HobbyController {

    private final HobbyService hobbyService;

    public HobbyController(HobbyService hobbyService) {
        this.hobbyService = hobbyService;
    }

    @GetMapping("/add")
    public String getAddHobbyPage(Model model) {

        if (!model.containsAttribute("hobbyAddBindingModel")) {
            model.
                    addAttribute("hobbyAddBindingModel", new HobbyAddBindingModel());
        }
        return "add-hobby";
    }

    @PostMapping("/add")
    public String addHobby(@Valid HobbyAddBindingModel hobbyAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal HobbyUserImpl user) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("hobbyAddBindingModel", hobbyAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.hobbyAddBindingModel", bindingResult);

            return "redirect:/add-hobby";
        }
        HobbyAddServiceModel hobby = hobbyService.addHobby(hobbyAddBindingModel, user.getUserIdentifier());

        return "redirect:/home";

    }

    @ModelAttribute
    public HobbyAddBindingModel hobbyAddBindingModel() {
        return new HobbyAddBindingModel();
    }
}
