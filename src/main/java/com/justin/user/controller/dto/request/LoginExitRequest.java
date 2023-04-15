package com.justin.user.controller.dto.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LoginExitRequest implements Serializable {
    private String userId;
    private String accessToken;
}
