package com.example.examproject.payload;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;



@Data
public class LoginDTO {
@NonNull
    private String userName;
@NotNull
    private String password;
}
