package com.ra.projectmd3test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/")
    public String index() {
        return "admin/dashboard";
    }
}
