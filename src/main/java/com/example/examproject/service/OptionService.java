package com.example.examproject.service;

import com.example.examproject.entity.TestQuestion;
import com.example.examproject.entity.TestQuestionOptions;
import com.example.examproject.payload.OptionDTO;
import com.example.examproject.payload.response.ApiResponse;
import com.example.examproject.repository.QuestionOptionRepository;
import com.example.examproject.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class OptionService {
    @Autowired
    QuestionOptionRepository optionRepository;
    @Autowired
    QuestionRepository questionRepository;

    public ApiResponse addOption(OptionDTO optionDTO) {
        TestQuestionOptions options=new TestQuestionOptions();
        Optional<TestQuestion> byId = questionRepository.findById(optionDTO.getQuestionId());

        options.setOptionTitle(optionDTO.getOptionTitle());
        options.setQuestion(byId.get());
        optionRepository.save(options);
        return new ApiResponse("Saved",true);
    }

    public TestQuestionOptions oneOption(Integer id) {
        Optional<TestQuestionOptions> byId = optionRepository.findById(id);
        return byId.orElseGet(TestQuestionOptions::new);
    }

    public List<TestQuestionOptions> allOptions() {
        return optionRepository.findAll();
    }

    public ApiResponse editOption(OptionDTO optionDTO, Integer id) {
        Optional<TestQuestionOptions> byId = optionRepository.findById(id);
        Optional<TestQuestion> questionOptional = questionRepository.findById(optionDTO.getQuestionId());
        if(byId.isPresent()){
            TestQuestionOptions options=byId.get();
            options.setQuestion(questionOptional.get());
            options.setOptionTitle(optionDTO.getOptionTitle());
            optionRepository.save(options);
            return new ApiResponse("Changed",true);
        }
        return new ApiResponse("not found",false);
    }

    public ApiResponse deleteOption(Integer id) {
        Optional<TestQuestionOptions> byId = optionRepository.findById(id);
        if(byId.isPresent()){
            TestQuestionOptions options=byId.get();
            options.setActive(false);
            optionRepository.save(options);
            return new ApiResponse("Deleted",true);
        }
        return new ApiResponse("not found",false);
    }
}
