package com.ra.projectmd3test.controller.admin;

import com.ra.projectmd3test.model.dto.ProductRequest;
import com.ra.projectmd3test.model.entity.Product;
import com.ra.projectmd3test.service.design.ICategoryService;
import com.ra.projectmd3test.service.design.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        Integer pageSize = Math.max(size,1);
        int currentPage  = Math.max(page,0);
        Long totalProducts = productService.getTotalProduct();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("viewName","list-product");
        model.addAttribute("portfolio","Product");
        model.addAttribute("action","List");
        model.addAttribute("listProduct",productService.findAll(page,pageSize));
        return "admin/dashboard";
    }
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("viewName","add-product");
        model.addAttribute("portfolio","Product");
        model.addAttribute("action","Add");
        model.addAttribute("listCate",categoryService.findAll());
        model.addAttribute("productRequest",new ProductRequest());
        return "admin/dashboard";
    }
    @PostMapping("/add")
    public String doAdd(@Valid @ModelAttribute("productRequest") ProductRequest productRequest, BindingResult bindingResult, Model model, @RequestParam("mainImage" )MultipartFile mainImage) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("viewName","add-product");
            model.addAttribute("portfolio","Product");
            model.addAttribute("action","Add");
            model.addAttribute("listCate",categoryService.findAll());
            model.addAttribute("productRequest",productRequest);
            if(mainImage.isEmpty()) {
                model.addAttribute("mainImageError","Please select a main image");
            }
            return "admin/dashboard";
        }else{
            productService.save(productRequest);
            return "redirect:/admin/product/list";
        }
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("viewName","edit-product");
        model.addAttribute("portfolio","Product");
        model.addAttribute("action","Edit");
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        model.addAttribute("imgMain",product.getMainImage());
        model.addAttribute("cateId",product.getCategory().getId());
        model.addAttribute("productId",product.getId());
        model.addAttribute("listCate",categoryService.findAll());
        return "admin/dashboard";
    }
    @PostMapping("/edit/{id}")
    public String doEdit(@Valid @ModelAttribute("product") ProductRequest productRequest, BindingResult bindingResult, Model model,@PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("viewName","edit-product");
            model.addAttribute("portfolio","Product");
            model.addAttribute("action","Edit");
            model.addAttribute("productId",id);
            Product product = productService.findById(id);
            model.addAttribute("imgMain",product.getMainImage());
            model.addAttribute("cateId",product.getCategory().getId());
            model.addAttribute("product",productRequest);
            model.addAttribute("listCate",categoryService.findAll());
            return "admin/dashboard";
        }else{
            Product product = productService.findById(id);
            productService.saveProduct(productRequest, product.getId(),product.getMainImage());
            return "redirect:/admin/product/list";
        }
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        if(productService.findById(id) != null) {
            productService.deleteById(id);
        }
        return "redirect:/admin/product/list";
    }
}
