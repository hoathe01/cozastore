package com.cybersoft.cozastore.payload.request;

import com.cybersoft.cozastore.payload.response.CommentResponse;
import com.cybersoft.cozastore.payload.response.TagResponse;
import com.cybersoft.cozastore.payload.response.UserResponse;
import lombok.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BlogRequest {
    private int id;
    private String title;
//    private MultipartFile image;
    private String content;
    private int user;
    private List<TagRequest> listTag;
}
