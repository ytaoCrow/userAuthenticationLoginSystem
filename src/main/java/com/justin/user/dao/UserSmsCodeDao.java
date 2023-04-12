package com.justin.user.dao;

import com.justin.user.entity.UserSmsCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserSmsCodeDao {
    int insert(UserSmsCode userSmsCode);
    UserSmsCode selectByMobileNo(String mobileNo);
}
