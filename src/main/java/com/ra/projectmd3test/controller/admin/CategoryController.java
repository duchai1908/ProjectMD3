package com.ra.projectmd3test.controller.admin;

import com.google.common.net.MediaType;
import com.ra.projectmd3test.model.entity.Category;
import com.ra.projectmd3test.service.design.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("category",new Category());
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
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model model, @RequestParam("adminId") Integer adminId){
        if(bindingResult.hasErrors()){
            model.addAttribute("category",category);
            model.addAttribute("viewName","list-category");
            model.addAttribute("portfolio","Category");
            model.addAttribute("action","List");
            return "admin/dashboard";
        }else {
            Category newCategory = categoryService.findById(adminId);
            newCategory.setName(category.getName());
            categoryService.save(newCategory);
            return "redirect:/admin/category/list";
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer id){
        categoryService.deleteById(id);
        return ResponseEntity.ok("Category deleted successfully");
//        if (isDeleted) {
//            return ResponseEntity.ok("Category deleted successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
//        }
    }
}
