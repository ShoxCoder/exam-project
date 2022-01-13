package com.example.examproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    private Integer testTitle;
    private UUID users;
    private double usersStatus;
}
