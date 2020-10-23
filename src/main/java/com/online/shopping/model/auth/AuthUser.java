package com.online.shopping.model.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@Document(collection = "auth_user")
public class AuthUser {

    @Id
    private String id;

    private String name;

    @Indexed
    private String email;

    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String userId;

    private Integer otp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date otpDate;
}