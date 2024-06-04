package com.ebp.mapper;
import com.ebp.entity.OrderDetail;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface OrderDetailMapper {
    @Insert("INSERT INTO order_detail (id, order_base_id, goods_id, goods_num) VALUES (NULL, #{orderBaseId}, #{goodsId}, #{goodsNum})")
    int insertOrderDetail(OrderDetail orderDetail);
    @Delete("DELETE FROM order_detail WHERE id = #{id}")
    int deleteOrderDetailById(Integer id);
    @Delete("DELETE FROM order_detail WHERE order_base_id = #{orderBaseId}")
    int deleteOrderDetailByOrderBaseId(Integer orderBaseId);
    @Update("UPDATE order_detail SET order_base_id = #{orderBaseId}, goods_id = #{goodsId}, goods_num = #{goodsNum} WHERE id = #{id}")
    int updateOrderDetailById(OrderDetail orderDetail);
    @Select("SELECT * FROM order_detail WHERE id = #{id}")
    @Results({
            @Result(column = "order_base_id", property = "orderBaseId"),
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "goods_num", property = "goodsNum")
    })
    OrderDetail selectOrderDetailById(Integer id);
    @Select("SELECT * FROM order_detail WHERE order_base_id = #{orderBaseId}")
    @Results({
            @Result(column = "order_base_id", property = "orderBaseId"),
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "goods_num", property = "goodsNum")
    })
    List<OrderDetail> selectOrderDetailByOrderBaseId(Integer orderBaseId);
    @Select("SELECT * FROM order_detail")
    @Results({
            @Result(column = "order_base_id", property = "orderBaseId"),
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "goods_num", property = "goodsNum")
    })
    List<OrderDetail> selectAllOrderDetail();
}