package com.example.examproject.controller;

import com.example.examproject.entity.TestQuestion;
import com.example.examproject.payload.TestQuestionDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.service.TestQuestionService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class TestQuestionController {
@Autowired
TestQuestionService testQuestionService;
@PreAuthorize(value = "hasAuthority('Add_Question')")
@PostMapping
    public HttpEntity<?> add(@RequestBody TestQuestionDTO testQuestionDTO){
    ApiResponse apiResponse = testQuestionService.addQuestion(testQuestionDTO);
    return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
}

@GetMapping("{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
    TestQuestion testQuestion = testQuestionService.oneQuestion(id);
    return ResponseEntity.ok(testQuestion);
}
@PreAuthorize("hasAuthority('Read_Question')")
@GetMapping
    public HttpEntity<?> getAll(){
    List<TestQuestion> testQuestions = testQuestionService.allQuestion();
    return ResponseEntity.ok(testQuestions);
}
@PreAuthorize("hasAuthority('Edit_Question')")
@PutMapping
    public HttpEntity<?> edit(@RequestBody TestQuestionDTO testQuestionDTO,@PathVariable Integer id){
    ApiResponse apiResponse = testQuestionService.editQuestion(testQuestionDTO, id);
    return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
}
@PreAuthorize("hasAuthority('Delete_Question')")
@DeleteMapping("{id}")
    public HttpEntity<?>delete(@PathVariable Integer id){
    ApiResponse apiResponse = testQuestionService.deleteQuestion(id);
return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
}

}
