package com.rookieliu.demo.mapper;

import com.rookieliu.demo.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,bio,login,token,gmt_create,gmt_modified,avatar_url) " +
            "value(#{accountId},#{name},#{bio},#{login},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);


    @Results(id = "userResult",value = {
        @Result(property = "accountId",column = "account_Id"),
        @Result(property = "gmtCreate",column = "gmt_create"),
        @Result(property = "gmtModified",column = "gmt_modified"),
        @Result(property = "avatarUrl",column = "avatar_url")
    })
    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);
}
