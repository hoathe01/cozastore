package com.cybersoft.cozastore.payload.response;

import com.cybersoft.cozastore.entity.BlogEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CommentResponse {
    private String name;
    private String email;
    private String content;
    private Date createDate;
}
