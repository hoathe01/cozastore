package com.cybersoft.cozastore.payload.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ColorResponse {
    private int id;
    private String name;
}
