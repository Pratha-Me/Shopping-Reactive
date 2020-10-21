package com.online.shopping.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthUserLoginDto {

    private String username;

    private String password;

    private String email;

    private String phone;
}
