package com.ebp.service;
import com.ebp.entity.User;
import java.util.List;
public interface UserService {
    int insertUser(User user);
    int deleteUserById(Integer id);
    int updateUserById(User user);
    User selectUserById(Integer id);
    User selectUserByUsername(String username);
    List<User> selectAllUser();
}