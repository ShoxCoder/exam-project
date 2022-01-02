package com.example.examproject.service;

import com.example.examproject.entity.Subject;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
@Autowired
    SubjectRepository subjectRepository;

    public ApiResponse addSubject(Subject subject) {
        boolean byName=subjectRepository.exisBySubjectName(subject.getName());
        if (byName)
            return new ApiResponse("Subject allready defined",false);
        subjectRepository.save(subject);
    return new ApiResponse("Saved",true);
    }

    public Subject getOne(Integer id) {
        Optional<Subject> byId = subjectRepository.findById(id);
        return byId.orElseGet(Subject::new);
    }

    public List<Subject> getAll() {
        List<Subject> all = subjectRepository.findAll();
        return all;
    }

    public ApiResponse edit(Subject subject, Integer id) {
        Optional<Subject> byId = subjectRepository.findById(id);
        if (byId.isPresent()){
            Subject subject1=byId.get();
            subject1.setName(subject.getName());
        subjectRepository.save(subject1);
        return new ApiResponse("Changed",true);
        }
        return new ApiResponse("Not Found",false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Subject> byId = subjectRepository.findById(id);
        if (byId.isPresent()){
            Subject subject=byId.get();
            subject.setActive(false);
            subjectRepository.save(subject);
        return new ApiResponse("Deleted",true);
        }
        return new ApiResponse("not found",false);

    }
}
