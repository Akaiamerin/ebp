package com.ebp.service;
import com.ebp.entity.Goods;
import java.util.List;
public interface GoodsService {
    int insertGoods(Goods goods);
    int deleteGoodsById(Integer id);
    int updateGoodsById(Goods goods);
    Goods selectGoodsById(Integer id);
    List<Goods> selectAllGoods();
}