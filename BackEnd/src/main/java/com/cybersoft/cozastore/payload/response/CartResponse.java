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

    private int id;
    private int quanity;
    private Date createDate;
}
