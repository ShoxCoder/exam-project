package com.example.examproject.repository;

import com.example.examproject.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface TestRepository extends JpaRepository<Test, Integer> {
    boolean existByTestTitle(String testTitle);
}
