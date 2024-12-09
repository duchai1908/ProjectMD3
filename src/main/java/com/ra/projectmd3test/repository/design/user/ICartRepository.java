package com.ra.projectmd3test.repository.design.user;

import com.ra.projectmd3test.model.entity.CartItem;

import java.util.List;

//add cart repository
public interface ICartRepository {
    List<CartItem> findByUserId(Integer userId);
    CartItem findByUserIdAndProductId(Integer userId, Integer productId);
    CartItem save(CartItem cartItem);
    void deleteById(Integer id);
    public void deleteAllByUserId(Integer userId);
}
