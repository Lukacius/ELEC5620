package com.elec5620.portal.dto;

import com.elec5620.portal.model.Assessment;
import com.elec5620.portal.model.Question;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AssessmentDTO {
    private long id;
    String assessmentType;

    Set<QuestionDTO> questions = new HashSet<>();

    public AssessmentDTO(Assessment assessmentEntity) {
        this.id = assessmentEntity.getId();
        this.assessmentType = assessmentEntity.getAssessmentType();
        for (Question question : assessmentEntity.getQuestions()) {
            this.questions.add(new QuestionDTO(question));
        }
    }
}
