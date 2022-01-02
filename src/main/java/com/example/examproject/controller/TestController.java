package com.example.examproject.controller;

import com.example.examproject.entity.Test;
import com.example.examproject.payload.TestDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    TestService testService;

   @PreAuthorize(value = "hasAuthority('Add_Test')")
   @PostMapping
   public HttpEntity<?> add(@RequestBody TestDTO testDTO){
       ApiResponse apiResponse = testService.addTest(testDTO);
       return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
   }
   @PreAuthorize(value = "hasAuthority('Read_Test')")
   @GetMapping("{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
       Test one = testService.getOne(id);
       return ResponseEntity.ok(one);
   }
    @PreAuthorize(value = "hasAuthority('Read_Test')")

    @GetMapping
    public HttpEntity<?> getAll(){
       List<Test> all = testService.getAll();
       return ResponseEntity.ok(all);
   }
   @PreAuthorize(value = "hasAuthority('Edit_Test')")
   @PutMapping
    public HttpEntity<?> edit(@RequestBody TestDTO testDTO , @PathVariable Integer id){
       ApiResponse apiResponse = testService.editTest(testDTO, id);
       return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
   }
   @PreAuthorize(value = "hasAuthority('Delete_Test')")
   @DeleteMapping("{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
       ApiResponse apiResponse = testService.deleteTest(id);
       return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
   }

}
