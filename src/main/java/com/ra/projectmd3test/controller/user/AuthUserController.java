package com.ra.projectmd3test.controller.user;

import com.ra.projectmd3test.model.dto.user.UserLogin;
import com.ra.projectmd3test.model.dto.user.UserRegister;
import com.ra.projectmd3test.service.impl.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

// Đăng ký, đăng nhâp User
@Controller
@RequestMapping("/")
public class AuthUserController {
    @Autowired
    private UserService userService;
    // Đăng ký user
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegister", new UserRegister());
        return "user/register";
    }
    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute("userRegister") UserRegister userRegister, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRegister", userRegister);
            return "user/register";
        }
        userService.save(userRegister);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userLogin", new UserLogin());
        return "user/login";
    }
    @PostMapping("/login")
    public String doLogin(@ModelAttribute("userLogin") UserLogin userLogin, Model model) {
        if(userService.login(userLogin)){
            return "redirect:/store/";
        }else{
            model.addAttribute("error", "Invalid username or password!");
            model.addAttribute("userLogin",userLogin);
            return "user/login";
        }
    }

}
