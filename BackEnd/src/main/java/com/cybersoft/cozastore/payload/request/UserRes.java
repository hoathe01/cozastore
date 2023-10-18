package com.cybersoft.cozastore.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRes {
    private String username;
    private String password;
    private String email;
    private RoleRes role;
}
