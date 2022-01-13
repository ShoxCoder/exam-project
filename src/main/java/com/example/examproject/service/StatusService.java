package com.example.examproject.service;

import com.example.examproject.entity.*;
import com.example.examproject.payload.StatusDTO;
import com.example.examproject.payload.UserAnswearDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.repository.TestRepository;
import com.example.examproject.repository.UserRepository;
import com.example.examproject.repository.UsersStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    @Autowired
    UsersStatusRepository statusRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TestRepository testRepository;

    public ApiResponse addStatus(StatusDTO statusDTO) {
        if(!statusRepository.existsByUserId(statusDTO.getUsers())){
            Optional<User> userOptional=userRepository.findById(statusDTO.getUsers());
            Optional<Test> testOptional=testRepository.findById(statusDTO.getTestTitle());
            UserStatus userStatus=new UserStatus();
            userStatus.setTestTitle(testOptional.get());
            userStatus.setUsers(userOptional.get());
            userStatus.setUserStatus(statusDTO.getUsersStatus());

            statusRepository.save(userStatus);
            return new ApiResponse("Saved",true);
        }
        return new ApiResponse("this Status allready defined",false);
    }
    public UserStatus oneStatus(Integer id) {
        Optional<UserStatus> statusOptional = statusRepository.findById(id);
        return statusOptional.orElseGet(UserStatus::new);
    }

    public List<UserStatus> allStatus() {
        return statusRepository.findAll();
    }

    public ApiResponse editAnswear(StatusDTO statusDTO, Integer id) {
        Optional<UserStatus> statusOptional=statusRepository.findById(id);
        Optional<Test> testOptional = testRepository.findById(statusDTO.getTestTitle());
        Optional<User> userOptional=userRepository.findById(statusDTO.getUsers());
        if (statusOptional.isPresent()){
            UserStatus userStatus=statusOptional.get();
            userStatus.setTestTitle(testOptional.get());
            userStatus.setUsers(userOptional.get());
            userStatus.setUserStatus(statusDTO.getUsersStatus());

            statusRepository.save(userStatus);
            return new ApiResponse("CHnaged",true);
        }
        return new ApiResponse("not found",false);
    }

    public ApiResponse deleteStatus(Integer id) {
        Optional<UserStatus> statusOptional= statusRepository.findById(id);
        if (statusOptional.isPresent()){
            UserStatus userStatus=statusOptional.get();
            userStatus.setActive(false);
            statusRepository.save(userStatus);

            return new ApiResponse("Deleted",true);
        }
        return new ApiResponse("not found",false);
    }
}
