package com.cybersoft.cozastore.dto;

import com.cybersoft.cozastore.entity.UserEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoleDTO {
    private String name;
    List<UserDTO> userList;
}
