package com.ra.projectmd3test.controller.admin;

import com.ra.projectmd3test.model.dto.admin.AdminLogin;
import com.ra.projectmd3test.service.design.admin.IAdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/loginadmin")
public class LoginController {
    @Autowired
    private IAdminAccountService adminAccountService;
    @Autowired
    private HttpSession session;
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("adminlogin",new AdminLogin());
        return "admin/login";
    }
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("adminlogin") AdminLogin adminLogin, Model model) {
        if(adminAccountService.login(adminLogin)){
            return "redirect:/admin/product/list";
        }else{
            model.addAttribute("error", "Invalid username or password!");
            model.addAttribute("adminlogin",adminLogin);
            return "admin/login";
        }
    }
    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("adminLogin");
        return "redirect:/loginadmin/login";
    }
}
