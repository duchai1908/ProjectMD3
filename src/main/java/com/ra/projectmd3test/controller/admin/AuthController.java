package com.ra.projectmd3test.controller.admin;

import com.ra.projectmd3test.model.dto.admin.AdminRegister;
import com.ra.projectmd3test.service.design.admin.IAdminAccountService;
import com.ra.projectmd3test.service.impl.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/adminaccount")
public class AuthController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private IAdminAccountService adminAccountService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("viewName","add-adminregister");
        model.addAttribute("portfolio","Admin Account");
        model.addAttribute("action","Add");
        model.addAttribute("adminRole",roleService.getAllRole());
        model.addAttribute("adminRegister",new AdminRegister());
        return "admin/dashboard";
    }
    @PostMapping("/register")
    public String registerSubmit(@Valid  @ModelAttribute("adminRegister") AdminRegister adminRegister, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("viewName","add-adminregister");
            model.addAttribute("portfolio","Admin Account");
            model.addAttribute("action","Add");
            model.addAttribute("adminRole",roleService.getAllRole());
            model.addAttribute("adminRegister",adminRegister);
            return "admin/dashboard";
        }else {
            adminAccountService.save(adminRegister);
            return "redirect:/admin/account/list";
        }
    }
    @GetMapping("/account/list")
    public String accountList(Model model) {
        model.addAttribute("viewName","account-list");
        model.addAttribute("portfolio","Admin Account");
        model.addAttribute("action","List");
        model.addAttribute("listAdmin",adminAccountService.findAll());
        return "admin/dashboard";
    }
    @GetMapping("/403")
    public String _403() {
        return "admin/403";
    }
}
