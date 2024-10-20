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

    public ResponseEntity<DTO> GenerateSyllabus(String input) {
    }

    public ResponseEntity<DTO> DetectingPlagiarism(String input) {
    }

    public ResponseEntity<DTO> AssessAssignment(String input) {
    }

    public ResponseEntity<DTO> MockExam(String input) {
    }

    public ResponseEntity<DTO> UpdatingLearningResource(String input) {
    }

    public ResponseEntity<DTO> OptimizeAlgorithm(String input) {
    }
}
