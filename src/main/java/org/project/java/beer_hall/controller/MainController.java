package org.project.java.beer_hall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    
    public String homePage() {
        return "main/home";
    }
}
