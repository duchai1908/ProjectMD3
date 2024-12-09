package com.ra.projectmd3test.controller.user;

import com.ra.projectmd3test.model.dto.user.UserLogin;
import com.ra.projectmd3test.model.entity.CartItem;
import com.ra.projectmd3test.model.entity.Image;
import com.ra.projectmd3test.service.impl.admin.*;
import com.ra.projectmd3test.service.impl.user.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class CartUserController {
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
    ///shopping-cart/{userid}
    @GetMapping("/shopping-cart/{id}")
    public String shoppingCart(@PathVariable("id") Integer userId, Model model) {
        List<CartItem> cartItemList = cartService.getCartItemsByUserId(userId);

        // Get a map of ProductDetail ID to a single Image
        Map<Integer, Image> productImagesMap = cartService.getSingleImageForEachProduct(cartItemList);
        UserLogin userLogin = new UserLogin();

        model.addAttribute("userId",userId);
        model.addAttribute("cartItems", cartItemList);
        model.addAttribute("productImagesMap", productImagesMap);

//        for ( cart : cartItemLis)
        return "user/shopping-cart";
    }

    @PostMapping("/user/shopping-cart/{id}")
    public String shoppingCart(@PathVariable("id") Integer productId, @RequestParam("quantity") Integer quantity, Model model) {
        // Assuming you have the user ID from the session or security context
        Integer userId = getCurrentUserId();

        // Add the product to the cart
        cartService.addProductToCart(userId, productId, quantity);

        // Redirect to the shopping cart view or another page
        model.addAttribute("banners",bannerService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        return "user/index"; // Adjust the redirect as needed
    }

    //ajex
    @PostMapping("/updateQuantity/{id}")
    public ResponseEntity<String> updateQuantity(@PathVariable("id")Integer userId, @RequestParam("productId") Integer productId, @RequestParam("quantity") Integer quantity) {
        cartService.updateCartItemQuantity(userId,productId, quantity);
        return ResponseEntity.ok("Quantity updated successfully");
    }

    //delete one
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("id") Integer cartItemId) {
        cartService.deleteCartItemById(cartItemId);
        return ResponseEntity.ok("CartItem deleted successfully");
    }

    //delete all
    @DeleteMapping("/shopping-cart/delete-all/{userId}")
    public ResponseEntity<Void> deleteAllCartItems(@PathVariable Integer userId) {
        cartService.deleteAllByUserId(userId);
        return ResponseEntity.ok().build();
    }

    //hard fix
    private Integer getCurrentUserId() {
        // Replace with your actual logic to retrieve the current user ID
        // For example, you might retrieve it from the security context
        return 3; // Placeholder user ID
    }


}
