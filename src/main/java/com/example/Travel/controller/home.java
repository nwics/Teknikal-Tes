package com.example.Travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class home {
    @GetMapping("/index")
    public String index() {
        return "index.html";
    }
}
