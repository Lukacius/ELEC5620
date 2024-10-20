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
    public ResponseEntity<DTO> GenerateSyllabus(@RequestBody UserRequest request) {
        portalService.GenerateSyllabus(request.userInput);
    }

    @PostMapping("/DetectingPlagiarism")
    public ResponseEntity<DTO> DetectingPlagiarism(@RequestBody UserRequest request) {
        portalService.DetectingPlagiarism(request.userInput);
    }

    @PostMapping("/AssessAssignment")
    public ResponseEntity<DTO> AssessAssignment(@RequestBody UserRequest request) {
        portalService.AssessAssignment(request.userInput);
    }

    @PostMapping("/MockExam")
    public ResponseEntity<DTO> MockExam(@RequestBody UserRequest request) {
        portalService.MockExam(request.userInput);
    }

    @PostMapping("/UpdatingLearningResource")
    public ResponseEntity<DTO> UpdatingLearningResource(@RequestBody UserRequest request) {
        portalService.UpdatingLearningResource(request.userInput);
    }

    @PostMapping("/OptimizeAlgorithm")
    public ResponseEntity<DTO> OptimizeAlgorithm(@RequestBody UserRequest request) {
        portalService.OptimizeAlgorithm(request.userInput);
    }

    public static class UserRequest {
        public String userInput;
    }
}
