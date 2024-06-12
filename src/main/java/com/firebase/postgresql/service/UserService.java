package com.firebase.postgresql.service;

import com.firebase.postgresql.payload.request.LoginRequest;
import com.firebase.postgresql.payload.request.RegisterRequest;
import com.firebase.postgresql.payload.response.UserResponse;

public interface UserService {
    String login(LoginRequest loginRequest);

    String register(RegisterRequest registerRequest);

    String refresh(String username);

    UserResponse userInfo(String username);

}
