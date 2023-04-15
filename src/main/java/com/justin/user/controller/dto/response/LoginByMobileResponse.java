package com.justin.user.controller.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LoginByMobileResponse implements Serializable {
    private String userId;
    private String accessToken;
}
