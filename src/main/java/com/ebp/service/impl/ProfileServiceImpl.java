package com.ebp.service.impl;
import com.ebp.mapper.ProfileMapper;
import com.ebp.entity.Profile;
import com.ebp.service.ProfileService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;
@Service
public class ProfileServiceImpl implements ProfileService {
    @Resource
    private ProfileMapper profileMapper;
    @Override
    public int insertProfile(Profile profile) {
        return profileMapper.insertProfile(profile);
    }
    @Override
    public int deleteProfileById(Integer id) {
        return profileMapper.deleteProfileById(id);
    }
    @Override
    public int updateProfileById(Profile profile) {
        return profileMapper.updateProfileById(profile);
    }
    @Override
    public Profile selectProfileById(Integer id) {
        return profileMapper.selectProfileById(id);
    }
    @Override
    public Profile selectProfileByUserId(Integer userId) {
        return profileMapper.selectProfileByUserId(userId);
    }
    @Override
    public List<Profile> selectAllProfile() {
        return profileMapper.selectAllProfile();
    }
}