package com.cybersoft.cozastore.dto;

import com.cybersoft.cozastore.entity.RoleEntity;
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
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private RoleDTO role;
}
