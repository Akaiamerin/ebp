package com.ebp.service;
import com.ebp.entity.OrderDetail;
import java.util.List;
public interface OrderDetailService {
    int insertOrderDetail(OrderDetail orderDetail);
    int deleteOrderDetailById(Integer id);
    int deleteOrderDetailByOrderBaseId(Integer orderBaseId);
    int updateOrderDetailById(OrderDetail orderDetail);
    OrderDetail selectOrderDetailById(Integer id);
    List<OrderDetail> selectOrderDetailByOrderBaseId(Integer orderBaseId);
    List<OrderDetail> selectAllOrderDetail();
}