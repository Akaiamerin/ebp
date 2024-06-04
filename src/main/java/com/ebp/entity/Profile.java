package com.ebp.entity;
public class Profile {
    private Integer id;
    private Integer userId;
    private String email;
    public Profile() {

    }
    public Profile(Integer id, Integer userId, String email) {
        this.id = id;
        this.userId = userId;
        this.email = email;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}