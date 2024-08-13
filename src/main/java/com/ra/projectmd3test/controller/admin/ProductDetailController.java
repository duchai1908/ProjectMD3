package com.ra.projectmd3test.controller.admin;

import com.ra.projectmd3test.model.dto.ProductDetailRequest;
import com.ra.projectmd3test.service.design.IColorService;
import com.ra.projectmd3test.service.design.IProductDetailService;
import com.ra.projectmd3test.service.design.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/productdetail")
public class ProductDetailController {
    @Autowired
    private ISizeService sizeService;
    @Autowired
    private IColorService colorService;
    @Autowired
    private IProductDetailService productDetailService;
    @GetMapping("/list/{id}")
    public String list(Model model, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size, @PathVariable("id") Integer id) {
        model.addAttribute("sizeService",sizeService.findAll());
        model.addAttribute("colorService",colorService.findAll());
        model.addAttribute("viewName","list-productdetail");
        model.addAttribute("portfolio","ProductDetail");
        model.addAttribute("action","List");
        model.addAttribute("productId",id);
        model.addAttribute("productDetailRequest", new ProductDetailRequest());
        return "admin/dashboard";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("productDetailRequest") ProductDetailRequest productDetailRequest, BindingResult bindingResult,Model model,@RequestParam("image") List<MultipartFile> images) {
                    if(bindingResult.hasErrors()) {
                        model.addAttribute("sizeService",sizeService.findAll());
                        model.addAttribute("colorService",colorService.findAll());
                        model.addAttribute("viewName","list-productdetail");
                        model.addAttribute("portfolio","ProductDetail");
                        model.addAttribute("action","List");
                        model.addAttribute("productId",productDetailRequest.getProduct());
                        model.addAttribute("productDetailRequest", new ProductDetailRequest());
                        return "admin/dashboard";
                    }else{
                        productDetailService.saveProductDetail(productDetailRequest,images);
                        return "redirect:/admin/productdetail/list/"+productDetailRequest.getProduct();
                    }
    }
}
