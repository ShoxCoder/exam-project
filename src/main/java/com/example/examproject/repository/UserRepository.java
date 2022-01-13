package com.example.examproject.repository;

import com.example.examproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.util.Optional;
import java.util.UUID;
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User>  findByPhoneNumber(String phone);
    boolean existByPhoneNumber(String phoneNumber);

}
