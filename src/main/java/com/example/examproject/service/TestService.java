package com.example.examproject.service;

import com.example.examproject.entity.Subject;
import com.example.examproject.entity.Test;
import com.example.examproject.payload.TestDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.repository.SubjectRepository;
import com.example.examproject.repository.TestRepository;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;
    @Autowired
    SubjectRepository subjectRepository;

    public ApiResponse addTest(TestDTO testDTO) {
        Test addTest=new Test();
        if (!testRepository.existByTestTitle(testDTO.getTestTitle())){
            Optional<Subject> byId = subjectRepository.findById(testDTO.getSubjectId());
            addTest.setClasses(testDTO.getClasses());
            addTest.setDuration(testDTO.getDuration());
            addTest.setTestStatus(testDTO.getTestStatus());
            addTest.setSubjectName(byId.get());
            addTest.setTotalQuestion(testDTO.getTotalQuestion());
            addTest.setMarksPerRightAnswear(testDTO.getMarksPerRightAnswear());
            addTest.setMarksPerWrongAnswear(testDTO.getMarksPerWrongAnswear());
            testRepository.save(addTest);
            return new ApiResponse("Saved",true);
        }
        return new ApiResponse("test allready defined",false);
    }

    public Test getOne(Integer id) {
        Optional<Test> byId = testRepository.findById(id);
        return byId.orElseGet(Test::new);
    }

    public List<Test> getAll() {
        return testRepository.findAll();
    }

    public ApiResponse editTest(TestDTO testDTO , Integer id) {
        Optional<Test> testOptional = testRepository.findById(id);
        Optional<Subject> subjectOptional=subjectRepository.findById(testDTO.getSubjectId());
           if (testOptional.isPresent()){
               Test testEdit=testOptional.get();
               testEdit.setTestTitle(testDTO.getTestTitle());
               testEdit.setTestStatus(testDTO.getTestStatus());
               testEdit.setDuration(testDTO.getDuration());
               testEdit.setClasses(testDTO.getClasses());
               testEdit.setSubjectName(subjectOptional.get());
               testEdit.setTotalQuestion(testDTO.getTotalQuestion());
               testEdit.setMarksPerWrongAnswear(testDTO.getMarksPerWrongAnswear());
               testEdit.setMarksPerRightAnswear(testDTO.getMarksPerRightAnswear());

               testRepository.save(testEdit);
               return new ApiResponse("Changed",true);
           }
           return new ApiResponse("not found",false);
    }

    public ApiResponse deleteTest(Integer id) {
        Optional<Test> byId = testRepository.findById(id);
        if (byId.isPresent()){
            Test test=byId.get();
            test.setActive(false);
            testRepository.save(test);
          return new ApiResponse("deledted",true);
        }
        return new ApiResponse("not foubd",false);
    }
}
