package com.elec5620.portal.service;

import com.elec5620.portal.controller.PortalController;
import com.elec5620.portal.repository.AssessmentRepository;
import com.elec5620.portal.repository.QuestionRepository;
import com.elec5620.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PortalService {
    private final UserRepository userRepository;
    private final AssessmentRepository assessmentRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public PortalService(UserRepository userRepository,
                         AssessmentRepository assessmentRepository,
                         QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.assessmentRepository = assessmentRepository;
        this.questionRepository = questionRepository;
    }

    public String GenerateSyllabus(String input) {
        return input;
    }

    public String DetectingPlagiarism(String input) {
        return input;
    }

    public String AssessAssignment(String input) {
        return input;
    }

    public String MockExam(String input) {
        return input;
    }

    public String UpdatingLearningResource(String input) {
        return input;
    }

    public String OptimizeAlgorithm(String input) {
        return input;
    }
}
