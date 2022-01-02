package com.example.examproject.repository;

import com.example.examproject.entity.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface QuestionRepository extends JpaRepository<TestQuestion, Integer> {
    boolean existByQuestionTitle(String question);
}
