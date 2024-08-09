package com.ra.projectmd3test.controller.admin;

import com.ra.projectmd3test.model.entity.Size;
import com.ra.projectmd3test.service.design.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/size")
public class SizeController {
    @Autowired
    private ISizeService sizeService;
    @GetMapping("/list")
    public String listSize(Model model) {
        model.addAttribute("viewName","list-size");
        model.addAttribute("portfolio","Size");
        model.addAttribute("action","List");
        model.addAttribute("listSize",sizeService.findAll());
        model.addAttribute("size",new Size());
        return "admin/dashboard";
    }
    @GetMapping("/add")
    public String addSize(Model model) {
        model.addAttribute("viewName","add-size");
        model.addAttribute("portfolio","Size");
        model.addAttribute("action","Add");
        model.addAttribute("size",new Size());
        return "admin/dashboard";
    }
    @PostMapping("/add")
    public String doAddSize(@Valid @ModelAttribute("size") Size size, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("viewName","add-size");
            model.addAttribute("portfolio","Size");
            model.addAttribute("action","Add");
            model.addAttribute("size",size);
            return "admin/dashboard";
        }else{
            sizeService.save(size);
            return "redirect:/admin/size/list";
        }
    }
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("size") Size size, BindingResult bindingResult, Model model,@RequestParam("sizeId") Integer id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("viewName","list-size");
            model.addAttribute("portfolio","Size");
            model.addAttribute("action","List");
            model.addAttribute("size",size);
            return "admin/dashboard";
        }else{
            Size newSize = sizeService.findById(id);
            if(newSize !=null){
                newSize.setName(size.getName());
                sizeService.save(newSize);
                return "redirect:/admin/size/list";
            }else{
                return "admin/dashboard";
            }
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer id){
        sizeService.deleteById(id);
        return ResponseEntity.ok("Size deleted successfully");
//        if (isDeleted) {
//            return ResponseEntity.ok("Category deleted successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
//        }
    }
}
