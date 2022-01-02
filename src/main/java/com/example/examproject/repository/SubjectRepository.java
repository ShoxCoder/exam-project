package com.example.examproject.repository;

import com.example.examproject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface   SubjectRepository extends JpaRepository<Subject, Integer> {

    boolean exisBySubjectName(String name);
}
