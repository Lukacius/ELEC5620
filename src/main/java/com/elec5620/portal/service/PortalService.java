package com.elec5620.portal.service;

import com.elec5620.portal.repository.AssessmentRepository;
import com.elec5620.portal.repository.QuestionRepository;
import com.elec5620.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalService {
    private final UserRepository userRepository;
    private final AssessmentRepository assessmentRepository;
    private final QuestionRepository questionRepository;
    private final AIService aiService;

    @Autowired
    public PortalService(UserRepository userRepository,
                         AssessmentRepository assessmentRepository,
                         QuestionRepository questionRepository,
                         AIService aiService) {
        this.userRepository = userRepository;
        this.assessmentRepository = assessmentRepository;
        this.questionRepository = questionRepository;
        this.aiService = aiService;
    }

    public String GenerateSyllabus(String input) {
        return aiService.getAIResponse(input);
    }

    public String DetectingPlagiarism(String input) {
        return aiService.getAIResponse(input);
    }

    public String AssessAssignment(String input) {
        return aiService.getAIResponse(input);
    }

    public String MockExam(String input) {
        return aiService.getAIResponse(input);
    }

    public String UpdatingLearningResource(String input) {
        return aiService.getAIResponse(input);
    }

    public String OptimizeAlgorithm(String input) {
        return aiService.getAIResponse(input);
    }
}
