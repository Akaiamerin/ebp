package com.ebp.service.impl;
import com.ebp.mapper.OrderStatusMapper;
import com.ebp.entity.OrderStatus;
import com.ebp.service.OrderStatusService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class OrderStatusServiceImpl implements OrderStatusService {
    @Resource
    private OrderStatusMapper orderStatusMapper;
    @Override
    public int insertOrderStatus(OrderStatus orderStatus) {
        return orderStatusMapper.insertOrderStatus(orderStatus);
    }
    @Override
    public int deleteOrderStatusById(Integer id) {
        return orderStatusMapper.deleteOrderStatusById(id);
    }
    @Override
    public int updateOrderStatusById(OrderStatus orderStatus) {
        return orderStatusMapper.updateOrderStatusById(orderStatus);
    }
    @Override
    public OrderStatus selectOrderStatusById(Integer id) {
        return orderStatusMapper.selectOrderStatusById(id);
    }
    @Override
    public List<OrderStatus> selectAllOrderStatus() {
        return orderStatusMapper.selectAllOrderStatus();
    }
}