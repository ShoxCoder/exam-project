package com.example.examproject.service;

import com.example.examproject.entity.Test;
import com.example.examproject.entity.TestQuestion;
import com.example.examproject.entity.User;
import com.example.examproject.entity.UserAnswear;
import com.example.examproject.payload.UserAnswearDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.repository.QuestionRepository;
import com.example.examproject.repository.TestRepository;
import com.example.examproject.repository.UserRepository;
import com.example.examproject.repository.UsersAnswearRepository;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAnswearService {
    @Autowired
    UsersAnswearRepository usersAnswearRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QuestionRepository questionRepository;

    public ApiResponse addAnswear(UserAnswearDTO userAnswearDTO) {
        if(!usersAnswearRepository.existsByQuestionId(userAnswearDTO.getQuestionId())){
            Optional<Test> testOptional = testRepository.findById(userAnswearDTO.getTestId());
            Optional<User> userOptional=userRepository.findById(userAnswearDTO.getUserId());
            Optional<TestQuestion> questionOptional=questionRepository.findById(userAnswearDTO.getQuestionId());
            UserAnswear userAnswear=new UserAnswear();
            userAnswear.setTestTitle(testOptional.get());
            userAnswear.setUserName(userOptional.get());
            userAnswear.setTestQuestion(questionOptional.get());
            userAnswear.setGivenAnswear(userAnswearDTO.getGivenAnswear());

            usersAnswearRepository.save(userAnswear);
            return new ApiResponse("Saved",true);
        }
        return new ApiResponse("not found",false);
    }

    public UserAnswear oneAnswear(Integer id) {
        Optional<UserAnswear> answearOptional = usersAnswearRepository.findById(id);
        return answearOptional.orElseGet(UserAnswear::new);
    }

    public List<UserAnswear> allAnswear() {
        return usersAnswearRepository.findAll();
    }

    public ApiResponse editAnswear(UserAnswearDTO userAnswearDTO, Integer id) {
        Optional<UserAnswear> answearOptional=usersAnswearRepository.findById(id);
        Optional<Test> testOptional = testRepository.findById(userAnswearDTO.getTestId());
        Optional<User> userOptional=userRepository.findById(userAnswearDTO.getUserId());
        Optional<TestQuestion> questionOptional=questionRepository.findById(userAnswearDTO.getQuestionId());
        if (answearOptional.isPresent()){
            UserAnswear userAnswear =answearOptional.get();
            userAnswear.setTestTitle(testOptional.get());
            userAnswear.setUserName(userOptional.get());
            userAnswear.setTestQuestion(questionOptional.get());
            userAnswear.setGivenAnswear(userAnswearDTO.getGivenAnswear());

            usersAnswearRepository.save(userAnswear);
            return new ApiResponse("CHnaged",true);
        }
        return new ApiResponse("not found",false);
    }

    public ApiResponse deleteAnswear(Integer id) {
       Optional<UserAnswear> answearOptional= usersAnswearRepository.findById(id);
       if (answearOptional.isPresent()){
           UserAnswear userAnswear=answearOptional.get();
           userAnswear.setActive(false);
           usersAnswearRepository.save(userAnswear);

           return new ApiResponse("Deleted",true);
       }
       return new ApiResponse("not found",false);
    }
}
