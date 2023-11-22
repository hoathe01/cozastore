package com.cybersoft.cozastore.payload.response;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderReponse {
    private int idOder;
    private int idStatus;
    private Date createDate;
}

