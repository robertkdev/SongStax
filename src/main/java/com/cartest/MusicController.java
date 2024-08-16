package com.cartest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MusicController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Music Player!");
        return "home";
    }
}
