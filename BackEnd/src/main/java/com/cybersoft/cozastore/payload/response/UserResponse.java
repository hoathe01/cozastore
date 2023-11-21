package com.cybersoft.cozastore.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserResponse {
    private int id;
    private String username;
    private String password;
    private String email;
    private DateResponse createDate;
    private RoleResponse role;
}
