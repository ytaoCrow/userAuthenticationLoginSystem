package com.justin.user.service;

import com.justin.user.controller.dto.request.GetSmsCodeRequest;
import com.justin.user.controller.dto.request.LoginByMobileRequest;
import com.justin.user.controller.dto.request.LoginExitRequest;
import com.justin.user.controller.dto.response.LoginByMobileResponse;
import com.justin.user.entity.*;

public interface UserService {
    //獲取簡訊驗證碼
    boolean getSmsCode(GetSmsCodeRequest getSmsCodeReqVo);
    //簡訊登入
    LoginByMobileResponse loginByMobile(LoginByMobileRequest loginByMobileReqVo) throws BizException;
    //登入登出
    boolean loginExit(LoginExitRequest loginExitReqVo);

}
