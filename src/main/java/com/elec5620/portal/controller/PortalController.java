package com.elec5620.portal.controller;

import com.elec5620.portal.model.User;
import com.elec5620.portal.repository.UserRepository;
import com.elec5620.portal.service.PortalService;
import com.elec5620.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/portal")
public class PortalController {
    private final PortalService portalService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    public PortalController(PortalService portalService) {
        this.portalService = portalService;
    }

    @PostMapping("/LanguageTutor/{email}")
    public String LanguageTutor(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);
        request.userInput = user.getUserPrompt() + ": " + request.userInput;
        return portalService.LanguageTutor(request.userInput);
    }

    @PostMapping("/FurtherReadings/{email}")
    public String FurtherReadings(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);
        request.userInput = user.getUserPrompt() + ": " + request.userInput;
        return portalService.LanguageTutor(request.userInput);
    }

    @PostMapping("/AfterClassExercise/{email}")
    public String AfterClassExercise(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);
        request.userInput = user.getUserPrompt() + ": " + request.userInput;
        return portalService.LanguageTutor(request.userInput);
    }

    @PostMapping("/AssessAssignmentStudent/{email}")
    public String AssessAssignmentStudent(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);
        request.userInput = user.getUserPrompt() + ": " + request.userInput;
        return portalService.LanguageTutor(request.userInput);
    }

    @PostMapping("/LearningFeedback/{email}")
    public String LearningFeedback(@PathVariable String email, @RequestBody UserRequest request) {
        User user = userRepository.findByEmail(email).orElse(null);
        request.userInput = user.getUserPrompt() + ": " + request.userInput;
        return portalService.LanguageTutor(request.userInput);
    }


    @PostMapping("/GenerateSyllabus")
    public String GenerateSyllabus(@RequestBody UserRequest request) {
        return portalService.GenerateSyllabus(request.userInput);
    }

    @PostMapping("/DetectingPlagiarism")
    public String DetectingPlagiarism(@RequestBody UserRequest request) {
        return portalService.DetectingPlagiarism(request.userInput);
    }

    @PostMapping("/AssessAssignment")
    public String AssessAssignment(@RequestBody UserRequest request) {
        return portalService.AssessAssignment(request.userInput);
    }

    @PostMapping("/MockExam")
    public String MockExam(@RequestBody UserRequest request) {
        return portalService.MockExam(request.userInput);
    }

    @PostMapping("/UpdatingLearningResource")
    public String UpdatingLearningResource(@RequestBody UserRequest request) {
        return portalService.UpdatingLearningResource(request.userInput);
    }

    @PostMapping("/OptimizeAlgorithm")
    public String OptimizeAlgorithm(@RequestBody UserRequest request) {
        return portalService.OptimizeAlgorithm(request.userInput);
    }

    public static class UserRequest {
        public String userInput;
    }
}
