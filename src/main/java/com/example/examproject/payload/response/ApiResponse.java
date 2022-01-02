package com.example.examproject.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
private String message;
private boolean sucess;
private Object object;

    public ApiResponse(String message, boolean sucess) {
        this.message = message;
        this.sucess = sucess;
    }
}
