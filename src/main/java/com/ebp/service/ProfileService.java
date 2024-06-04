package com.ebp.service;
import com.ebp.entity.Profile;
import java.util.List;
public interface ProfileService {
    int insertProfile(Profile profile);
    int deleteProfileById(Integer id);
    int updateProfileById(Profile profile);
    Profile selectProfileById(Integer id);
    Profile selectProfileByUserId(Integer userId);
    List<Profile> selectAllProfile();
}