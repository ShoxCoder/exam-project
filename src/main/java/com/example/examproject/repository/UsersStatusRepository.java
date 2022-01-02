package com.example.examproject.repository;

import com.example.examproject.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.UUID;

public interface UsersStatusRepository extends JpaRepository<UserStatus, UUID> {

}
