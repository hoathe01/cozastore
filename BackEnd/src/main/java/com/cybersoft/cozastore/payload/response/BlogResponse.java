package com.cybersoft.cozastore.payload.response;

import com.cybersoft.cozastore.entity.UserEntity;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BlogResponse {

    private int id;
    private String title;
    private String image;
    private String content;
    private UserResponse user;
    private Date createDate;

    private List<CommentResponse> listComment;
    private List<TagResponse> listTag;
}
