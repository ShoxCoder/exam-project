package com.example.examproject.controller;

import com.example.examproject.entity.User;
import com.example.examproject.payload.UserDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize(value = "hasAuthority('Add_User')")
    @PostMapping

    public HttpEntity<?> save(@RequestBody UserDTO userDTO){
        ApiResponse apiResponse = userService.saveUser(userDTO);

return  ResponseEntity.status(apiResponse.isSucess()?201:409).body(apiResponse);
    }
    @PreAuthorize(value = "hasAuthority('Read_User')")
    @GetMapping("/all")
    public HttpEntity<?> getAll(){
        List<User> all = userService.getALL();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id){
        User user = userService.getOneById(id);
        return ResponseEntity.status(user!=null ? 201: 409).body(user);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_USER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@PathVariable UUID id, @RequestBody User user){
        User edited = userService.editUser(id, user);
        return ResponseEntity.status(edited!=null? 201: 409).body(edited);
    }

    @PreAuthorize(value = "hasAuthority(' DELETE_USER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable UUID id){
        ApiResponse apiResponse = userService.delete(id);
        return ResponseEntity.status(apiResponse.isSucess() ? 201: 409).body(apiResponse);
    }


}