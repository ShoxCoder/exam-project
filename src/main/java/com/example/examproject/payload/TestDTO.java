package com.example.examproject.payload;

import com.example.examproject.entity.Class;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDTO {
    private String testTitle;
    private Set<Class> classes;
    private Integer subjectId;
    private String duration;
    private Integer totalQuestion;
    private double marksPerRightAnswear;
    private double marksPerWrongAnswear;
    private String testStatus;
}
