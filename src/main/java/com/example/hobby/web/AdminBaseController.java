package com.example.hobby.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminBaseController {

    @GetMapping("/statistic")
    public String getStatisticPage(){
        return "statistic";
    }
}
