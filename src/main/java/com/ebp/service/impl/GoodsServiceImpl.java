package com.ebp.service.impl;
import com.ebp.mapper.GoodsMapper;
import com.ebp.entity.Goods;
import com.ebp.service.GoodsService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Override
    public int insertGoods(Goods goods) {
        return goodsMapper.insertGoods(goods);
    }
    @Override
    public int deleteGoodsById(Integer id) {
        return goodsMapper.deleteGoodsById(id);
    }
    @Override
    public int updateGoodsById(Goods goods) {
        return goodsMapper.updateGoodsById(goods);
    }
    @Override
    public Goods selectGoodsById(Integer id) {
        return goodsMapper.selectGoodsById(id);
    }
    @Override
    public List<Goods> selectAllGoods() {
        return goodsMapper.selectAllGoods();
    }
}