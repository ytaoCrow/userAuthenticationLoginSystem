package com.justin.user.controller;

import com.justin.user.controller.dto.request.GetSmsCodeRequest;
import com.justin.user.controller.dto.request.LoginByMobileRequest;
import com.justin.user.controller.dto.request.LoginExitRequest;
import com.justin.user.controller.dto.response.LoginByMobileResponse;
import com.justin.user.entity.*;
import com.justin.user.entity.enums.ResultCode;
import com.justin.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userServiceImpl;

    @RequestMapping(value = "getSmsCode", method = RequestMethod.POST)
    public Boolean getSmsCode(@RequestParam("reqId") String reqId,
                              @RequestParam("mobileNo") String mobileNo){
        GetSmsCodeRequest getSmsCodeReqVo = GetSmsCodeRequest.builder().
                reqId(reqId).mobileNo(mobileNo).build();
        boolean result = userServiceImpl.getSmsCode(getSmsCodeReqVo);
        return result;
    }
    @RequestMapping(value = "loginByMobile", method = RequestMethod.POST)
    public ApiResponse loginByMobile(@RequestParam("reqId") String reqId,
                                     @RequestParam("mobileNo")String mobileNo,
                                     @RequestParam("smsCode")String smsCode) throws BizException {
        LoginByMobileRequest loginByMobileReqVo = LoginByMobileRequest.builder().reqId(reqId).mobileNo(mobileNo)
                .smsCode(smsCode).build();
        LoginByMobileResponse loginByMobileResVo = userServiceImpl.loginByMobile(loginByMobileReqVo);
        return ApiResponse.success(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), loginByMobileResVo);
    }
    @RequestMapping(value = "loginExit", method = RequestMethod.POST)
    public Boolean loginExit(@RequestParam("userId") String userId,
                             @RequestParam("accessToken") String accessToken){

        LoginExitRequest loginExitReqVo = LoginExitRequest.builder().
                userId(userId).accessToken(accessToken).build();
        boolean result = userServiceImpl.loginExit(loginExitReqVo);
        return result;
    }
}
