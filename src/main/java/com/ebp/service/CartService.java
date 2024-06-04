package com.ebp.service;
import com.ebp.entity.Cart;
import java.util.List;
public interface CartService {
    int insertCart(Cart cart);
    int deleteCartById(Integer id);
    int updateCartById(Cart cart);
    Cart selectCartById(Integer id);
    List<Cart> selectCartByUserId(Integer userId);
    List<Cart> selectAllCart();
}