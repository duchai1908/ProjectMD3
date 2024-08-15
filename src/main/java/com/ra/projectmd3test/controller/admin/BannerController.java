package com.ra.projectmd3test.controller.admin;

import com.ra.projectmd3test.model.dto.admin.BannerRequest;
import com.ra.projectmd3test.model.entity.Banner;
import com.ra.projectmd3test.service.impl.admin.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("viewName","list-banner");
        model.addAttribute("portfolio","Banner");
        model.addAttribute("action","List");
        model.addAttribute("banner",new Banner());
        model.addAttribute("listBanner",bannerService.findAll());
        return "admin/dashboard";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("viewName","add-banner");
        model.addAttribute("portfolio","Banner");
        model.addAttribute("action","Add");
        model.addAttribute("bannerRequest",new BannerRequest());
        return "admin/dashboard";
    }
    @PostMapping(value = "/add")
    public String doAdd(@Valid @ModelAttribute("bannerRequest") BannerRequest banner, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("banner",banner);
            model.addAttribute("viewName","add-banner");
            model.addAttribute("portfolio","Banner");
            model.addAttribute("action","Add");
            return "admin/dashboard";
        }else{
            banner.setStatus(true);
            bannerService.save(banner);
            return "redirect:/admin/banner/list";
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer id){
        bannerService.deleteById(id);
        return ResponseEntity.ok("Banner deleted successfully");
    }
}
