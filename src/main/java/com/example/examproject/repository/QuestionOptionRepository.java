package com.example.examproject.repository;

import com.example.examproject.entity.TestQuestionOptions;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface QuestionOptionRepository extends JpaRepository<TestQuestionOptions, Integer> {

}
