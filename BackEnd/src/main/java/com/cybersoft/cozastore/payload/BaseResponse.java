package com.cybersoft.cozastore.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BaseResponse {
    private int StatusCode;
    private String message;
    private Object data;
}
