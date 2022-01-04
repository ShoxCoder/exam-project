package com.example.examproject.repository;

import com.example.examproject.entity.UserAnswear;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface UsersAnswearRepository extends JpaRepository<UserAnswear, Integer> {
    boolean existsByQuestionId(Integer questionId);
}
