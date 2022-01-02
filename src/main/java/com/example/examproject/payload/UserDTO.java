package com.example.examproject.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String fullName;
    private String username;
    private String passsword;
    private Integer roleId;

}
