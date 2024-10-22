package com.elec5620.portal.dto;

import com.elec5620.portal.model.Question;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QuestionDTO {
    private long id;
    String type;
    int amount;
    String difficultyLevel;
    AssessmentDTO assessment;

    public QuestionDTO(Question questionEntity) {
        this.id = questionEntity.getId();
        this.type = questionEntity.getType();
        this.amount = questionEntity.getAmount();
        this.difficultyLevel = questionEntity.getDifficultyLevel();
        this.assessment = new AssessmentDTO(questionEntity.getAssessment());
    }
}
