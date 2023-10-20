package com.cybersoft.cozastore.payload.response;

import com.cybersoft.cozastore.entity.BlogTagEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TagResponse {
    private int id;
    private String name;
    private List<BlogResponse> listBlog;
}
