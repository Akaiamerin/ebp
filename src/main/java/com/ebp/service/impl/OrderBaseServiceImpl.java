package com.ebp.service.impl;
import com.ebp.mapper.OrderBaseMapper;
import com.ebp.entity.OrderBase;
import com.ebp.service.OrderBaseService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class OrderBaseServiceImpl implements OrderBaseService {
    @Resource
    private OrderBaseMapper orderBaseMapper;
    @Override
    public int insertOrderBase(OrderBase orderBase) {
        return orderBaseMapper.insertOrderBase(orderBase);
    }
    @Override
    public int deleteOrderBaseById(Integer id) {
        return orderBaseMapper.deleteOrderBaseById(id);
    }
    @Override
    public int updateOrderBaseById(OrderBase orderBase) {
        return orderBaseMapper.updateOrderBaseById(orderBase);
    }
    @Override
    public OrderBase selectOrderBaseById(Integer id) {
        return orderBaseMapper.selectOrderBaseById(id);
    }
    @Override
    public List<OrderBase> selectOrderBaseByUserId(Integer userId) {
        return orderBaseMapper.selectOrderBaseByUserId(userId);
    }
    @Override
    public List<OrderBase> selectAllOrderBase() {
        return orderBaseMapper.selectAllOrderBase();
    }
}