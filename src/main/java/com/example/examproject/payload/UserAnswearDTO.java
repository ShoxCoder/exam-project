package com.example.examproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswearDTO {
private UUID userId;
private Integer testId;
private Integer questionId;
private String givenAnswear;
}
