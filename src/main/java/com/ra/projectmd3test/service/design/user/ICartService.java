package com.ra.projectmd3test.service.design.user;

import com.ra.projectmd3test.model.entity.CartItem;
import com.ra.projectmd3test.model.entity.Image;

import java.util.List;
import java.util.Map;

public interface ICartService {
    List<CartItem> getCartItemsByUserId(Integer userId);
    void addProductToCart(Integer userId, Integer productId, Integer quantity);
    void removeCartItem(Integer cartItemId);
    List<Image> getAllImagesByProductDetailId(Integer productDetailId);
    Map<Integer, Image> getSingleImageForEachProduct(List<CartItem> cartItems);
}
