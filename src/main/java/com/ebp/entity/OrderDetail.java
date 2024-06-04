package com.ebp.entity;
public class OrderDetail {
    private Integer id;
    private Integer orderBaseId;
    private Integer goodsId;
    private Integer goodsNum;
    public OrderDetail() {

    }
    public OrderDetail(Integer id, Integer orderBaseId, Integer goodsId, Integer goodsNum) {
        this.id = id;
        this.orderBaseId = orderBaseId;
        this.goodsId = goodsId;
        this.goodsNum = goodsNum;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getOrderBaseId() {
        return orderBaseId;
    }
    public void setOrderBaseId(Integer orderBaseId) {
        this.orderBaseId = orderBaseId;
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