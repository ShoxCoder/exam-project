package com.example.examproject.controller;

import com.example.examproject.entity.UserAnswear;
import com.example.examproject.payload.UserAnswearDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.service.UserAnswearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/myanswear")

public class UserAnswearController {
@Autowired
UserAnswearService userAnswearService;

@PostMapping
    public HttpEntity<?> add(@RequestBody UserAnswearDTO userAnswearDTO){
    ApiResponse apiResponse = userAnswearService.addAnswear(userAnswearDTO);
    return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
}
@GetMapping("{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
    UserAnswear userAnswear = userAnswearService.oneAnswear(id);
    return ResponseEntity.ok(userAnswear);
}
@GetMapping

    public HttpEntity<?> getAll(){
    List<UserAnswear> userAnswears = userAnswearService.allAnswear();
    return ResponseEntity.ok(userAnswears);
}
@PutMapping
    public HttpEntity<?>edit(@RequestBody UserAnswearDTO userAnswearDTO, @PathVariable Integer id){
    ApiResponse apiResponse = userAnswearService.editAnswear(userAnswearDTO, id);
    return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
}
@DeleteMapping("{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
    ApiResponse apiResponse = userAnswearService.deleteAnswear(id);
    return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
}
}
