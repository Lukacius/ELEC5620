package com.elec5620.portal.controller;

import com.elec5620.portal.service.PortalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portal")
public class PortalController {
    private final PortalService portalService;

    public PortalController(PortalService portalService) {
        this.portalService = portalService;
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
