package com.cybersoft.cozastore.payload.request;

import com.cybersoft.cozastore.payload.response.RoleResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRequest {
    private int id;
    private String username;
    private String password;
    private String email;
    private int role;
}
