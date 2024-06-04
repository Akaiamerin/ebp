package com.ebp.entity;
import java.math.BigDecimal;
public class Goods {
    private Integer id;
    private Integer goodsTypeId;
    private String title;
    private BigDecimal originalPrice;
    private BigDecimal currentPrice;
    private Integer store;
    private String picture;
    public Goods() {

    }
    public Goods(Integer id, Integer goodsTypeId, String title, BigDecimal originalPrice, BigDecimal currentPrice, Integer store, String picture) {
        this.id = id;
        this.goodsTypeId = goodsTypeId;
        this.title = title;
        this.originalPrice = originalPrice;
        this.currentPrice = currentPrice;
        this.store = store;
        this.picture = picture;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }
    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }
    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
    public Integer getStore() {
        return store;
    }
    public void setStore(Integer store) {
        this.store = store;
    }
    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
}