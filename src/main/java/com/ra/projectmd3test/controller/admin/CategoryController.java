package com.ra.projectmd3test.controller.admin;

import com.google.common.net.MediaType;
import com.ra.projectmd3test.model.entity.Category;
import com.ra.projectmd3test.service.design.ICategoryService;
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
@RequestMapping("/admin/category")
public class CategoryController{
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("viewName","list-category");
        model.addAttribute("portfolio","Category");
        model.addAttribute("action","List");
        model.addAttribute("listCate",categoryService.findAll());
        return "admin/dashboard";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("viewName","add-category");
        model.addAttribute("portfolio","Category");
        model.addAttribute("action","Add");
        model.addAttribute("category",new Category());
        return "admin/dashboard";
    }
    @PostMapping(value = "/add")
    public String doAdd(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("category",category);
            model.addAttribute("viewName","add-category");
            model.addAttribute("portfolio","Category");
            model.addAttribute("action","Add");
            return "admin/dashboard";
        }else{
            categoryService.save(category);
            return "redirect:/admin/category/list";
        }
    }
}
