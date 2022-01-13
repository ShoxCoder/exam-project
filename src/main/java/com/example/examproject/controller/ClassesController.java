package com.example.examproject.controller;

import com.example.examproject.entity.Class;


import com.example.examproject.payload.ClassDTO;
import com.example.examproject.payload.response.ApiResponse;

import com.example.examproject.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/classes")
public class ClassesController {
    @Autowired
    ClassService classService;

    @PostMapping()
    public HttpEntity<ApiResponse> add(@RequestBody Class classes) {

        ApiResponse apiResponse = classService.addClass(classes);
return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
    }


    @GetMapping("/{id}")
   public HttpEntity<?> getOne(@PathVariable Integer id){
        Class one =classService.getOne(id);
        return ResponseEntity.ok(one);
    }
    @GetMapping
    public HttpEntity<?> getAll() {
        List<Class> all = classService.getAll();
        return ResponseEntity.ok(all);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@RequestBody Class sinflar, @PathVariable Integer id) {
        ApiResponse apiResponse = classService.edit(sinflar, id);
        return ResponseEntity.status(apiResponse.isSucess() ? 201 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id) {

        ApiResponse apiResponse = classService.delete(id);
        return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
    }


}
