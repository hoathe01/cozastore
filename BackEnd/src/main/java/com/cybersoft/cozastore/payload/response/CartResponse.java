package com.cybersoft.cozastore.payload.response;

import com.cybersoft.cozastore.entity.UserEntity;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CartResponse {

    private UserResponse userResponse;
    private Date createDate;
}
