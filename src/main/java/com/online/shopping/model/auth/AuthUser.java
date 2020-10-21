package com.online.shopping.model.auth;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Accessors(chain = true)
@Document
public class AuthUser {

    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    private String name;

    @Indexed
    private String email;

    private String phone;

    private String password;

//    @Enumerated(EnumType.STRING)
    private Role role;

//    @Enumerated(EnumType.STRING)
    private Status status;

    private String userId;

    private Integer otp;

//    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate;

//    @Temporal(TemporalType.TIMESTAMP)
    private Date otpDate;
}