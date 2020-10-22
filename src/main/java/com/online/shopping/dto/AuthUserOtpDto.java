package com.online.shopping.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthUserOtpDto {

    private String email;

    private String otp;
}
