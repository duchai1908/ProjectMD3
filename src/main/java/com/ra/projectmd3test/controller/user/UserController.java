package com.ra.projectmd3test.controller.user;

import com.ra.projectmd3test.model.entity.*;
import com.ra.projectmd3test.service.impl.admin.*;
import com.ra.projectmd3test.service.impl.user.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/store")
public class UserController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SizeServiceImpl sizeService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CartService cartService;


    @GetMapping(value = {"/","/index"})
    public String index(Model model) {
        model.addAttribute("banners",bannerService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("productDetails",productDetailService.findAll(0,4));
        // Get the map of productDetailId to a single image from the service
        Map<Integer, Image> productImageMap = productDetailService.getOneProductImageMap();

        List<ProductDetail> productDetailList = productDetailService.findProductDetailsByCategoryId(2);
        model.addAttribute("productImageMap", productImageMap);
        return "user/index";
    }




    @GetMapping("/checkout")
    public String checkout() {
        return "user/checkout";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/register")
    public String register() {
        return "user/register";
    }



}
