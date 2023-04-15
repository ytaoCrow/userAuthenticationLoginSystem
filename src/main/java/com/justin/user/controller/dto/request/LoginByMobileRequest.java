package com.justin.user.controller.dto.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LoginByMobileRequest implements Serializable{
    private String reqId;
    private String mobileNo;
    private String smsCode;
}
