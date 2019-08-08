package com.rookieliu.demo.mapper;

import com.rookieliu.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id,name,login,token,gmt_create,gmt_modified) " +
            "value(#{accountId},#{name},#{login},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
