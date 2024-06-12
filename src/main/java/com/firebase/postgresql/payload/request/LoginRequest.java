package com.firebase.postgresql.payload.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {
    @NotNull(message = "username may be not null")
    @NotEmpty(message = "username may not be empty")
    private String username;
    @NotNull(message = "password may be not null")
    @NotEmpty(message = "password may not be empty")
    private String password;
}
