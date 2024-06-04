package com.ebp.service.impl;
import com.ebp.mapper.OrderDetailMapper;
import com.ebp.entity.OrderDetail;
import com.ebp.service.OrderDetailService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Override
    public int insertOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.insertOrderDetail(orderDetail);
    }
    @Override
    public int deleteOrderDetailById(Integer id) {
        return orderDetailMapper.deleteOrderDetailById(id);
    }
    @Override
    public int deleteOrderDetailByOrderBaseId(Integer orderBaseId) {
        return orderDetailMapper.deleteOrderDetailByOrderBaseId(orderBaseId);
    }
    @Override
    public int updateOrderDetailById(OrderDetail orderDetail) {
        return orderDetailMapper.updateOrderDetailById(orderDetail);
    }
    @Override
    public OrderDetail selectOrderDetailById(Integer id) {
        return orderDetailMapper.selectOrderDetailById(id);
    }
    @Override
    public List<OrderDetail> selectOrderDetailByOrderBaseId(Integer orderBaseId) {
        return orderDetailMapper.selectOrderDetailByOrderBaseId(orderBaseId);
    }
    @Override
    public List<OrderDetail> selectAllOrderDetail() {
        return orderDetailMapper.selectAllOrderDetail();
    }
}