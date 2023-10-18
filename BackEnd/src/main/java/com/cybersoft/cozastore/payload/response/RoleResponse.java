package com.cybersoft.cozastore.payload.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoleResponse {
    private String name;
    List<UserResponse> userList;
}
