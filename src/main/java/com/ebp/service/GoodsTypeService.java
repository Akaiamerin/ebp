package com.ebp.service;
import com.ebp.entity.GoodsType;
import java.util.List;
public interface GoodsTypeService {
    int insertGoodsType(GoodsType goodsType);
    int deleteGoodsTypeById(Integer id);
    int updateGoodsTypeById(GoodsType goodsType);
    GoodsType selectGoodsTypeById(Integer id);
    List<GoodsType> selectAllGoodsType();
}