package com.firebase.postgresql.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String createdAt;
    private boolean enabled;
    private List<RoleResponse> roles;
}
