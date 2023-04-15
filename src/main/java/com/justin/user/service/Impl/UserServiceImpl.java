package com.justin.user.service.Impl;

import com.justin.user.controller.dto.request.GetSmsCodeRequest;
import com.justin.user.controller.dto.request.LoginByMobileRequest;
import com.justin.user.controller.dto.request.LoginExitRequest;
import com.justin.user.controller.dto.response.LoginByMobileResponse;
import com.justin.user.dao.UserInfoDao;
import com.justin.user.dao.UserSmsCodeDao;
import com.justin.user.entity.*;
import com.justin.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserSmsCodeDao userSmsCodeDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean getSmsCode(GetSmsCodeRequest getSmsCodeReqVo) {
        //隨機生成6位簡訊驗證碼
        String smsCode = String.valueOf((int) (Math.random() * 100000 +1));
        //真實場景是需要呼叫簡訊平台介面
        //儲存驗證碼資訊至驗證碼資料表

        UserSmsCode userSmsCode = UserSmsCode.builder().mobileNo(getSmsCodeReqVo.getMobileNo()).smsCode(smsCode).
                sendTime(new Timestamp(new Date().getTime())).createTime(new Timestamp(new Date().getTime()))
                        .build();
        userSmsCodeDao.insert(userSmsCode);
        return true;
    }

    @Override
    public LoginByMobileResponse loginByMobile(LoginByMobileRequest loginByMobileReqVo) throws BizException {
        //驗證碼檢核
        UserSmsCode userSmsCode = userSmsCodeDao.selectByMobileNo(loginByMobileReqVo.getMobileNo());
        if(userSmsCode == null){
            throw new BizException(-1, "驗證碼輸入錯誤");
        }else if (!userSmsCode.getSmsCode().equals(loginByMobileReqVo.getSmsCode())){
            throw new BizException(-1, "驗證碼輸入錯誤");
        }

        //用戶註冊檢核
        UserInfo userInfo = userInfoDao.selectByMobileNo(loginByMobileReqVo.getMobileNo());
        if(userInfo == null){
            //隨機生成使用者 ID
            String userId = String.valueOf((int)(Math.random()* 100000 +1));
              userInfo = UserInfo.builder().userId(userId).mobileNo(loginByMobileReqVo.getMobileNo()).isLogin("1")
                      .loginTime(new Timestamp(new Date().getTime())).createTime(new Timestamp(new Date().getTime()))
                      .build();
              //完成註冊流程
              userInfoDao.insert(userInfo);
        }else {
           userInfo.setIsLogin("1");
           userInfo.setLoginTime(new Timestamp(new Date().getTime()));
           userInfoDao.updateById(userInfo);
        }

        //生成使用者階段資訊
        String accessToken = UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
        redisTemplate.opsForValue().set("accessToken", userInfo, 30, TimeUnit.DAYS);
        LoginByMobileResponse loginByMobileResVo = LoginByMobileResponse.builder().userId(userInfo.getUserId())
                .accessToken(accessToken).build();
        return loginByMobileResVo;
    }

    @Override
    public boolean loginExit(LoginExitRequest loginExitReqVo) {
        try {
            redisTemplate.delete(loginExitReqVo.getAccessToken());
            return true;
        }catch (Exception e){
            log.error(e.toString()+"_"+ e);
            return false;
        }
    }
}
