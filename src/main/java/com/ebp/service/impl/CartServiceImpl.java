package com.ebp.service.impl;
import com.ebp.mapper.CartMapper;
import com.ebp.entity.Cart;
import com.ebp.service.CartService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartMapper cartMapper;
    @Override
    public int insertCart(Cart cart) {
        return cartMapper.insertCart(cart);
    }
    @Override
    public int deleteCartById(Integer id) {
        return cartMapper.deleteCartById(id);
    }
    @Override
    public int updateCartById(Cart cart) {
        return cartMapper.updateCartById(cart);
    }
    @Override
    public Cart selectCartById(Integer id) {
        return cartMapper.selectCartById(id);
    }
    @Override
    public List<Cart> selectCartByUserId(Integer userId) {
        return cartMapper.selectCartByUserId(userId);
    }
    @Override
    public List<Cart> selectAllCart() {
        return cartMapper.selectAllCart();
    }
}