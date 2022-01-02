package com.example.examproject.controller;

import com.example.examproject.entity.Subject;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody Subject subject){
        ApiResponse apiResponse = subjectService.addSubject(subject);
return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
    }
    @GetMapping("{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Subject one = subjectService.getOne(id);
        return ResponseEntity.ok(one);
    }
    @GetMapping
    public HttpEntity<?> getAll(){
        List<Subject> all = subjectService.getAll();
        return ResponseEntity.ok(all);
    }
    @PutMapping
    public HttpEntity<?> edit(@RequestBody Subject subject ,@PathVariable Integer id){
        ApiResponse edit = subjectService.edit(subject, id);
        return ResponseEntity.status(edit.isSucess()?201:409).body(edit);
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse delete = subjectService.delete(id);
        return ResponseEntity.ok(delete);
    }
}
