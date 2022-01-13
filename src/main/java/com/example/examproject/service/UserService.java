package com.example.examproject.service;

import com.example.examproject.entity.Role;
import com.example.examproject.entity.User;
import com.example.examproject.payload.UserDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.repository.RoleRepository;
import com.example.examproject.repository.UserRepository;
import com.fasterxml.jackson.core.PrettyPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse saveUser(UserDTO userDTO) {
        User user = new User();
        if (!userRepository.existByPhoneNumber(userDTO.getPhoneNumber())) {

            Optional<Role> optionalRole = roleRepository.findById(userDTO.getRoleId());
            user.setUsername(userDTO.getUsername());
            user.setRole(optionalRole.get());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setFullName(user.getFullName());
            user.setPassword(userDTO.getPasssword());

            userRepository.save(user);

            return new ApiResponse("Saved", true);
        }
        return new ApiResponse("This user allready defined", false);
    }


    public List<User> getALL() {
        List<User> all = userRepository.findAll();
        return all;
    }

    public User getOneById(UUID id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.orElse(null);
    }

    public User editUser(UUID id, User user) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User editUser = new User();
            editUser.setFullName(user.getFullName());
            editUser.setUsername(user.getUsername());
            editUser.setPassword(user.getPassword());

            return userRepository.save(editUser);
        }
        return null;
    }

    public ApiResponse delete(UUID id) {
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            User user =byId.get();
            user.setActive(false);
        userRepository.save(user);
        return new ApiResponse("Deleted",true);
        }
        return new ApiResponse("not deleted",false);
    }
}
