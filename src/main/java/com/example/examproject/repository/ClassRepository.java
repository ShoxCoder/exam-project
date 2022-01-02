package com.example.examproject.repository;


import com.example.examproject.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassRepository extends JpaRepository<Class, Integer> {
    boolean existByClassName(String className);


}
