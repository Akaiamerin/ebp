package com.ebp.mapper;
import com.ebp.entity.Cart;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface CartMapper {
    @Insert("INSERT INTO cart (id, user_id, goods_id, goods_num) VALUES (NULL, #{userId}, #{goodsId}, #{goodsNum})")
    int insertCart(Cart cart);
    @Delete("DELETE FROM cart WHERE id = #{id}")
    int deleteCartById(Integer id);
    @Update("UPDATE cart SET user_id = #{userId}, goods_id = #{goodsId}, goods_num = #{goodsNum} WHERE id = #{id}")
    int updateCartById(Cart cart);
    @Select("SELECT * FROM cart WHERE id = #{id}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "goods_num", property = "goodsNum")
    })
    Cart selectCartById(Integer id);
    @Select("SELECT * FROM cart WHERE user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "goods_num", property = "goodsNum")
    })
    List<Cart> selectCartByUserId(Integer userId);
    @Select("SELECT * FROM cart")
    @Results({
            @Result(column = "user_id", property = "userId"),
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "goods_num", property = "goodsNum")
    })
    List<Cart> selectAllCart();
}