package com.ebp.service;
import com.ebp.entity.OrderStatus;
import java.util.List;
public interface OrderStatusService {
    int insertOrderStatus(OrderStatus orderStatus);
    int deleteOrderStatusById(Integer id);
    int updateOrderStatusById(OrderStatus orderStatus);
    OrderStatus selectOrderStatusById(Integer id);
    List<OrderStatus> selectAllOrderStatus();
}