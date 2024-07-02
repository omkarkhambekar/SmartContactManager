package com.omkar_smartcontactmanager.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    @RequestMapping("/home")
    public String home() {
            System.out.println("In home page handler");
            return "home";
    }
    //about route
    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println(" About Page loading");
        return "about";
    }
    //services
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services Page loading");
        return "services";
    }
    //contact page
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }
    @GetMapping("/register")
    public String register() {
        return new String("register");
    }

}
