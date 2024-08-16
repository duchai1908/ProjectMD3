package com.ra.projectmd3test.controller.user;

import com.ra.projectmd3test.model.entity.Color;
import com.ra.projectmd3test.model.entity.Image;
import com.ra.projectmd3test.model.entity.ProductDetail;
import com.ra.projectmd3test.model.entity.Size;
import com.ra.projectmd3test.service.impl.admin.*;
import com.ra.projectmd3test.service.impl.user.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/store/product")
public class ProductUserController {
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

    @GetMapping("/list-products")
    public String listProduct(Model model,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer size) {
        Integer pageSize = Math.max(size,1);
        int currentPage  = Math.max(page,0);

        Long totalProductTotals = productDetailService.getTotalProductDetail();
        int totalPages = (int) Math.ceil((double) totalProductTotals / pageSize);



        // Get the map of productDetailId to a single image from the service
        Map<Integer, Image> productImageMap = productDetailService.getOneProductImageMap();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", currentPage);

        model.addAttribute("colors",colorService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("productDetails",productDetailService.findWithPhanTrang(page,pageSize));
        model.addAttribute("productImageMap", productImageMap);

        return "user/general/list-products";
    }

    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable("id") Integer id , Model model) {
        //find product detail
        ProductDetail productDetail = productDetailService.findById(id);

        List<Color> colors = colorService.findAll();
        List<Size> sizes = sizeService.findAll();

        // Get the map of productDetailId to a list of images from the service
        Map<Integer, List<Image>> productImagesMap = productDetailService.getAllProductImageMap();

        model.addAttribute("productImagesMap", productImagesMap);
        model.addAttribute("productDetail",productDetail);
        model.addAttribute("colors",colors);
        model.addAttribute("sizes",sizes);
        return "user/general/product-detail";
    }

}
