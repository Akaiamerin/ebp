package com.ebp.service;
import com.ebp.entity.OrderBase;
import java.util.List;
public interface OrderBaseService {
    int insertOrderBase(OrderBase orderBase);
    int deleteOrderBaseById(Integer id);
    int updateOrderBaseById(OrderBase orderBase);
    OrderBase selectOrderBaseById(Integer id);
    List<OrderBase> selectOrderBaseByUserId(Integer userId);
    List<OrderBase> selectAllOrderBase();
}