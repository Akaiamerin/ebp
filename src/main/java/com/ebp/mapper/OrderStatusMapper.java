package com.ebp.mapper;
import com.ebp.entity.OrderStatus;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface OrderStatusMapper {
    @Insert("INSERT INTO order_status (id, status) VALUES (NULL, #{status})")
    int insertOrderStatus(OrderStatus orderStatus);
    @Delete("DELETE FROM order_status WHERE id = #{id}")
    int deleteOrderStatusById(Integer id);
    @Update("UPDATE order_status SET status = #{status} WHERE id = #{id}")
    int updateOrderStatusById(OrderStatus orderStatus);
    @Select("SELECT * FROM order_status WHERE id = #{id}")
    OrderStatus selectOrderStatusById(Integer id);
    @Select("SELECT * FROM order_status")
    List<OrderStatus> selectAllOrderStatus();
}