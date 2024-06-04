package com.ebp.mapper;
import com.ebp.entity.OrderBase;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface OrderBaseMapper {
    @Insert("INSERT INTO order_base (id, user_id, order_time, order_status_id) VALUES (NULL, #{userId}, #{orderTime}, #{orderStatusId})")
    int insertOrderBase(OrderBase orderBase);
    @Delete("DELETE FROM order_base WHERE id = #{id}")
    int deleteOrderBaseById(Integer id);
    @Update("UPDATE order_base SET user_id = #{userId}, order_time = #{orderTime}, order_status_id = #{orderStatusId} WHERE id = #{id}")
    int updateOrderBaseById(OrderBase orderBase);
    @Select("SELECT * FROM order_base WHERE id = #{id}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "order_time", property = "orderTime"),
            @Result(column = "order_status_id", property = "orderStatusId")
    })
    OrderBase selectOrderBaseById(Integer id);
    @Select("SELECT * FROM order_base WHERE user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "order_time", property = "orderTime"),
            @Result(column = "order_status_id", property = "orderStatusId")
    })
    List<OrderBase> selectOrderBaseByUserId(Integer userId);
    @Select("SELECT * FROM order_base")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "order_time", property = "orderTime"),
            @Result(column = "order_status_id", property = "orderStatusId")
    })
    List<OrderBase> selectAllOrderBase();
}