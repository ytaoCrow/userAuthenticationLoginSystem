package com.justin.user.dao;

import com.justin.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
public interface UserDao {
    String TABLE_NAME = " user ";
    String ALL_FIELDS = "username,password";
    @Insert("INSERT INTO" + TABLE_NAME + "(" + ALL_FIELDS + ") VALUES (#{username}, #{password})")
    int addUser(User user);
}
