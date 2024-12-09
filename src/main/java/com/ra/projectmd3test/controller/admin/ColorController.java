package com.ra.projectmd3test.controller.admin;

import com.ra.projectmd3test.model.entity.Color;
import com.ra.projectmd3test.service.impl.admin.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/color")
public class ColorController {
    @Autowired
    private ColorService colorService;
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("viewName","list-color");
        model.addAttribute("portfolio","Color");
        model.addAttribute("action","List");
        model.addAttribute("listColor",colorService.findAll());
        model.addAttribute("color",new Color());
        return "admin/dashboard";
    }
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("viewName","add-color");
        model.addAttribute("portfolio","Color");
        model.addAttribute("action","Add");
        model.addAttribute("color",new Color());
        return "admin/dashboard";
    }
    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("color") Color color, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("viewName","add-color");
            model.addAttribute("portfolio","Color");
            model.addAttribute("action","Add");
            model.addAttribute("color",color);
            return "admin/dashboard";
        }else{
            colorService.save(color);
            return "redirect:/admin/color/list";
        }
    }
    @PostMapping("/update")
    public String doUpdate(@Valid @ModelAttribute("color") Color color, BindingResult bindingResult, Model model, @RequestParam("colorId") Integer id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("viewName","list-color");
            model.addAttribute("portfolio","Color");
            model.addAttribute("action","List");
            model.addAttribute("color",color);
            return "admin/dashboard";
        }else{
            Color newColor = colorService.findById(id);
            if(newColor != null){
                newColor.setColor(color.getColor());
                colorService.save(newColor);
                return "redirect:/admin/color/list";
            }else{
                return "admin/dashboard";
            }
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer id){
        colorService.deleteById(id);
        return ResponseEntity.ok("Color deleted successfully");
//        if (isDeleted) {
//            return ResponseEntity.ok("Category deleted successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
//        }
    }
}
