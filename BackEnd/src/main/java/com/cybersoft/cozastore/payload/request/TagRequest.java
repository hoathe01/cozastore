package com.cybersoft.cozastore.payload.request;

import com.cybersoft.cozastore.payload.response.BlogResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TagRequest {
    private int id;
    private String name;
}
