package com.cybersoft.cozastore.payload.request;

import com.cybersoft.cozastore.entity.BlogEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CommentRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String content;
    private int idBlog;
}
