package com.ebp.mapper;
import com.ebp.entity.Profile;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface ProfileMapper {
    @Insert("INSERT INTO profile (id, user_id, email) VALUES (NULL, #{userId}, #{email})")
    int insertProfile(Profile profile);
    @Delete("DELETE FROM profile WHERE id = #{id}")
    int deleteProfileById(Integer id);
    @Update("UPDATE profile SET user_id = #{userId}, email = #{email} WHERE id = #{id}")
    int updateProfileById(Profile profile);
    @Select("SELECT * FROM profile WHERE id = #{id}")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    Profile selectProfileById(Integer id);
    @Select("SELECT * FROM profile WHERE user_id = #{userId}")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    Profile selectProfileByUserId(Integer userId);
    @Select("SELECT * FROM profile")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    List<Profile> selectAllProfile();
}