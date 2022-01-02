package com.example.examproject.service;

import com.example.examproject.entity.Class;
import com.example.examproject.entity.User;
import com.example.examproject.payload.ClassDTO;
import com.example.examproject.payload.UserDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
@Autowired
    ClassRepository classRepository;

    public ApiResponse addClass(Class classes) {
        boolean byName=classRepository.existByClassName(classes.getClassName());

        if (byName)
     return new ApiResponse(" not Saved",false);
        classRepository.save(classes);

        return new ApiResponse("Saved",true);
    }


    public Class getOne(Integer id) {
        Optional<Class> byId = classRepository.findById(id);
            return byId.orElseGet(Class:: new );
    }

    public List<Class> getAll() {
        return classRepository.findAll();
    }

    public ApiResponse edit(Class sinflar, Integer id) {
        Optional<Class> optionalClass = classRepository.findById(id);
        if (optionalClass.isPresent()){
            Class newClass=optionalClass.get();
            newClass.setClassName(sinflar.getClassName());
            classRepository.save(newClass);
            return new ApiResponse("Saved",true);
        }
        return new ApiResponse("Allready defined",false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Class> byId = classRepository.findById(id);
    if (byId.isPresent()){
        Class deleteClass=byId.get();
        deleteClass.setActive(false);

        classRepository.save(deleteClass);
        return new ApiResponse("deleted",true);
    }
    return new ApiResponse("not found",false);
    }
}
