package com.example.examproject.controller;

import com.example.examproject.entity.UserStatus;
import com.example.examproject.payload.StatusDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Autowired
    StatusService statusService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody StatusDTO statusDTO){
        ApiResponse apiResponse = statusService.addStatus(statusDTO);
        return ResponseEntity.status(apiResponse.isSucess()?201:405).body(apiResponse);
    }
    @GetMapping("{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        UserStatus userStatus = statusService.oneStatus(id);
        return ResponseEntity.ok(userStatus);
    }
    @GetMapping

    public HttpEntity<?> getAll(){
        List<UserStatus> userStatuses = statusService.allStatus();
        return ResponseEntity.ok(userStatuses);
    }
    @PutMapping
    public HttpEntity<?>edit(@RequestBody StatusDTO statusDTO, @PathVariable Integer id){
        ApiResponse apiResponse = statusService.editAnswear(statusDTO, id);
        return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = statusService.deleteStatus(id);
        return ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);

    }
}
