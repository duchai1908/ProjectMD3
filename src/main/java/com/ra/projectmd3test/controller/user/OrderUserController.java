package com.ra.projectmd3test.controller.user;

import com.ra.projectmd3test.model.dto.user.UserLogin;
import com.ra.projectmd3test.model.entity.CartItem;
import com.ra.projectmd3test.model.entity.Image;
import com.ra.projectmd3test.service.impl.admin.*;
import com.ra.projectmd3test.service.impl.user.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class OrderUserController {
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

    @GetMapping("/checkout/{id}")
    public String order(@PathVariable("id") Integer userId, Model model) {
        List<CartItem> cartItemList = cartService.getCartItemsByUserId(userId);

        // Calculate the total price
        double totalPrice = cartService.calculateTotalPrice(cartItemList);

        // Get a map of ProductDetail ID to a single Image
        Map<Integer, Image> productImagesMap = cartService.getSingleImageForEachProduct(cartItemList);
        UserLogin userLogin = new UserLogin();

        model.addAttribute("userId",userId);
        model.addAttribute("cartItems", cartItemList);
        model.addAttribute("productImagesMap", productImagesMap);
        model.addAttribute("totalPrice", totalPrice);
        return "user/checkout";
    }
}
