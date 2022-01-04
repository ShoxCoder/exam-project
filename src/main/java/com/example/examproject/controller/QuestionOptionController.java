package com.example.examproject.controller;

import com.example.examproject.entity.TestQuestionOptions;
import com.example.examproject.payload.OptionDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/options")
public class QuestionOptionController{
    @Autowired
    OptionService optionService;
 @PostMapping
    public HttpEntity<?> add(@RequestBody OptionDTO optionDTO){
        ApiResponse apiResponse = optionService.addOption(optionDTO);
        return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
    }
    @GetMapping("{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        TestQuestionOptions options = optionService.oneOption(id);
        return ResponseEntity.ok(options);
    }
    @GetMapping
    public HttpEntity<?> getAll(){
        List<TestQuestionOptions> testQuestionOptions = optionService.allOptions();
 return ResponseEntity.ok(testQuestionOptions);
 }
    @PutMapping
    public HttpEntity<?> edit(@RequestBody OptionDTO optionDTO,@PathVariable Integer id){
        ApiResponse apiResponse = optionService.editOption(optionDTO, id);
return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = optionService.deleteOption(id);
        return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
    }

}
