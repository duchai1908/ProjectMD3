package com.ra.projectmd3test.controller.user;

import com.ra.projectmd3test.model.entity.*;
import com.ra.projectmd3test.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
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


    @GetMapping(value = {"/","/index"})
    public String index(Model model) {
        model.addAttribute("banners",bannerService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        return "user/index";
    }

    @GetMapping("/list-products")
    public String listProduct(Model model) {
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("productDetails",productDetailService.findAll(0,6));
        return "user/general/list-products";
    }

    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable("id") Integer id ,Model model) {
        List<Category> categories = categoryService.findAll();
        List<Product> products = productService.findAll(0,6);
        List<Image> images = imageService.findAll();
        Product productFind = productService.findById(id);
        //find list product detail
        List<ProductDetail> productDetails = productDetailService.getProductDetailByProductId(productFind.getId());
        //find image which has same productDetail id
        List<String> imageList = new ArrayList<>();


        System.out.println(imageList);
//        for (Image image : images) {
//            if (image.getProductDetail().getId().equals(productDetails.get(0).getId())) {
//
//            }
//        }

        List<Color> colors = colorService.findAll();
        List<Size> sizes = sizeService.findAll();

        model.addAttribute("productDetails",productDetails);
        model.addAttribute("images",images);
        model.addAttribute("categories",categories);
        model.addAttribute("products",products);
        model.addAttribute("product",productFind);
        model.addAttribute("colors",colors);
        model.addAttribute("sizes",sizes);
        return "user/general/product-detail";
    }

    @GetMapping("/shopping-cart")
    public String shoppingCart() {
        return "user/shopping-cart";
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
