package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StyleController {
    @GetMapping("/style")
    public String showStyle() {
        return "components";
    }

    @GetMapping("/example")
    public String showExample() {
        return "example";
    }
}
