package com.ebp.mapper;
import com.ebp.entity.Goods;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface GoodsMapper {
    @Insert("INSERT INTO goods (id, goods_type_id, title, original_price, current_price, store, picture) VALUES (NULL, #{goodsTypeId}, #{title}, #{originalPrice}, #{currentPrice}, #{store}, #{picture})")
    int insertGoods(Goods goods);
    @Delete("DELETE FROM goods WHERE id = #{id}")
    int deleteGoodsById(Integer id);
    @Update("UPDATE goods SET goods_type_id = #{goodsTypeId}, title = #{title}, original_price = #{originalPrice}, current_price = #{currentPrice}, store = #{store}, picture = #{picture} WHERE id = #{id}")
    int updateGoodsById(Goods goods);
    @Select("SELECT * FROM goods WHERE id = #{id}")
    @Results({
            @Result(column = "goods_type_id", property = "goodsTypeId"),
            @Result(column = "original_price", property = "originalPrice"),
            @Result(column = "current_price", property = "currentPrice")
    })
    Goods selectGoodsById(Integer id);
    @Select("SELECT * FROM goods")
    @Results({
            @Result(column = "goods_type_id", property = "goodsTypeId"),
            @Result(column = "original_price", property = "originalPrice"),
            @Result(column = "current_price", property = "currentPrice")
    })
    List<Goods> selectAllGoods();
}