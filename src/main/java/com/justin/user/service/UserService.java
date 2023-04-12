package com.justin.user.service;

import com.justin.user.entity.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

public interface UserService {
    //獲取簡訊驗證碼
    boolean getSmsCode(GetSmsCodeReqVo getSmsCodeReqVo);
    //簡訊登入
    LoginByMobileResVo loginByMobile(LoginByMobileReqVo loginByMobileReqVo) throws BizException;
    //登入登出
    boolean loginExit(LoginExitReqVo loginExitReqVo);

}
