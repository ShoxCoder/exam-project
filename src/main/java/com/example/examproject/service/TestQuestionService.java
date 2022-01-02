package com.example.examproject.service;

import com.example.examproject.entity.Test;
import com.example.examproject.entity.TestQuestion;
import com.example.examproject.payload.TestQuestionDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.repository.QuestionRepository;
import com.example.examproject.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestQuestionService {
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    TestRepository testRepository;

    public ApiResponse addQuestion(TestQuestionDTO testQuestionDTO) {
        if (!questionRepository.existByQuestionTitle(testQuestionDTO.getQuestion())) {
            Optional<Test> optionalTest = testRepository.findById(testQuestionDTO.getTestId());
            TestQuestion testQuestion = new TestQuestion();
            testQuestion.setQuestion(testQuestionDTO.getQuestion());
            testQuestion.setTest(optionalTest.get());
            testQuestion.setAnswearOption(testQuestion.getAnswearOption());
            questionRepository.save(testQuestion);
        } else if (questionRepository.existByQuestionTitle(testQuestionDTO.getQuestion())) ;
        return new ApiResponse("Saved", true);
    }

    public TestQuestion oneQuestion(Integer id) {
        Optional<TestQuestion> byId = questionRepository.findById(id);
        return byId.orElseGet(TestQuestion::new);
    }

    public List<TestQuestion> allQuestion() {
        return questionRepository.findAll();
    }

    public ApiResponse editQuestion(TestQuestionDTO testQuestionDTO, Integer id) {
        Optional<TestQuestion> byId = questionRepository.findById(id);
        Optional<Test> testOptional = testRepository.findById(testQuestionDTO.getTestId());
        if (byId.isPresent()) {
            TestQuestion testQuestion = byId.get();
            testQuestion.setQuestion(testQuestion.getQuestion());
            testQuestion.setTest(testOptional.get());
            testQuestion.setAnswearOption(testQuestionDTO.getAnswearOption());
            questionRepository.save(testQuestion);
            return new ApiResponse("Edited", true);
        }
        return new ApiResponse("no found", false);
    }

    public ApiResponse deleteQuestion(Integer id) {

        Optional<TestQuestion> byId = questionRepository.findById(id);
        if (byId.isPresent()) {
            TestQuestion testQuestion = byId.get();
            testQuestion.setActive(false);
            questionRepository.save(testQuestion);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found", false);

    }
}
