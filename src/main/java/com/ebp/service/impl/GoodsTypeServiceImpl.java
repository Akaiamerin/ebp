package com.ebp.service.impl;
import com.ebp.mapper.GoodsTypeMapper;
import com.ebp.entity.GoodsType;
import com.ebp.service.GoodsTypeService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Resource
    private GoodsTypeMapper goodsTypeMapper;
    @Override
    public int insertGoodsType(GoodsType goodsType) {
        return goodsTypeMapper.insertGoodsType(goodsType);
    }
    @Override
    public int deleteGoodsTypeById(Integer id) {
        return goodsTypeMapper.deleteGoodsTypeById(id);
    }
    @Override
    public int updateGoodsTypeById(GoodsType goodsType) {
        return goodsTypeMapper.updateGoodsTypeById(goodsType);
    }
    @Override
    public GoodsType selectGoodsTypeById(Integer id) {
        return goodsTypeMapper.selectGoodsTypeById(id);
    }
    @Override
    public List<GoodsType> selectAllGoodsType() {
        return goodsTypeMapper.selectAllGoodsType();
    }
}