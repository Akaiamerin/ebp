package com.ebp.mapper;
import com.ebp.entity.GoodsType;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface GoodsTypeMapper {
    @Insert("INSERT INTO goods_type (id, title) VALUES (NULL, #{title})")
    int insertGoodsType(GoodsType goodsType);
    @Delete("DELETE FROM goods_type WHERE id = #{id}")
    int deleteGoodsTypeById(Integer id);
    @Update("UPDATE goods_type SET title = #{title} WHERE id = #{id}")
    int updateGoodsTypeById(GoodsType goodsType);
    @Select("SELECT * FROM goods_type WHERE id = #{id}")
    GoodsType selectGoodsTypeById(Integer id);
    @Select("SELECT * FROM goods_type")
    List<GoodsType> selectAllGoodsType();
}