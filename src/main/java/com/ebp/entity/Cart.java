package com.ebp.entity;
public class Cart {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private Integer goodsNum;
    public Cart() {

    }
    public Cart(Integer id, Integer userId, Integer goodsId, Integer goodsNum) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.goodsNum = goodsNum;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    public Integer getGoodsNum() {
        return goodsNum;
    }
    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }
}