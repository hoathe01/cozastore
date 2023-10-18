package com.cybersoft.cozastore.payload.request;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoleRes {
    private String name;
    List<UserRes> userList;
}
