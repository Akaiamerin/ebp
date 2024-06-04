package com.ebp.entity;
import java.time.LocalDateTime;
public class OrderBase {
    private Integer id;
    private Integer userId;
    private LocalDateTime orderTime;
    private Integer orderStatusId;
    public OrderBase() {

    }
    public OrderBase(Integer id, Integer userId, LocalDateTime orderTime, Integer orderStatusId) {
        this.id = id;
        this.userId = userId;
        this.orderTime = orderTime;
        this.orderStatusId = orderStatusId;
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
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
    public Integer getOrderStatusId() {
        return orderStatusId;
    }
    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }
}