package com.justin.user.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LoginExitReqVo implements Serializable {
    private String userId;
    private String accessToken;
}
