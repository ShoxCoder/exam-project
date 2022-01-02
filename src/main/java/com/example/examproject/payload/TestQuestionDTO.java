package com.example.examproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestQuestionDTO {
private Integer testId;
private  String question;
private String answearOption;
}
