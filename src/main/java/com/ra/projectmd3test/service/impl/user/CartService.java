package com.ra.projectmd3test.service.impl.user;

import com.ra.projectmd3test.model.entity.CartItem;
import com.ra.projectmd3test.model.entity.Image;
import com.ra.projectmd3test.repository.design.user.ICartRepository;
import com.ra.projectmd3test.service.design.user.ICartService;
import com.ra.projectmd3test.service.impl.admin.ImageService;
import com.ra.projectmd3test.service.impl.admin.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Override
    public List<CartItem> getCartItemsByUserId(Integer userId) {
        return cartRepository.findByUserId(userId);
    }

    //service add to cart when you have product in cart and no
    @Override
    public void addProductToCart(Integer userId, Integer productId, Integer quantity) {
        CartItem existingCartItem = cartRepository.findByUserIdAndProductId(userId, productId);
        if (existingCartItem != null) {
            // If the item is already in the cart, just update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            cartRepository.save(existingCartItem);
        } else {
            // If the item is not in the cart, create a new CartItem
            CartItem newCartItem = new CartItem();
            newCartItem.setUser(userService.findById(userId));
            newCartItem.setProduct(productDetailService.findById(productId));
            newCartItem.setQuantity(quantity);
            cartRepository.save(newCartItem);
        }
    }

    @Override
    public void removeCartItem(Integer cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    //take all image with productDetailId
    @Override
    public List<Image> getAllImagesByProductDetailId(Integer productDetailId) {
        return imageService.findAll().stream()
                .filter(image -> image.getProductDetail().getId().equals(productDetailId))
                .collect(Collectors.toList());
    }

    //find first image in every product detail
    @Override
    public Map<Integer, Image> getSingleImageForEachProduct(List<CartItem> cartItems) {
        return cartItems.stream()
                .collect(Collectors.toMap(
                        cartItem -> cartItem.getProduct().getId(),
                        cartItem -> imageService.findAll().stream()
                                .filter(image -> image.getProductDetail().getId().equals(cartItem.getProduct().getId()))
                                .findFirst()
                                .orElse(null)
                ));
    }

    //change quantity product detail in cart
    public void updateCartItemQuantity(Integer userId,Integer productId, Integer quantity) {
        CartItem cartItem = cartRepository.findByUserIdAndProductId(userId,productId);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            cartRepository.save(cartItem);
        }
    }

    //delete cart with cart id
    public void deleteCartItemById(Integer cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    public void deleteAllByUserId(Integer userId) {
        cartRepository.deleteAllByUserId(userId);
    }

    //Calculate total price
    public double calculateTotalPrice(List<CartItem> cartItems) {
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}
