package com.elec5620.portal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Question {
    @Id
    @GeneratedValue
    private long id;

    @JoinColumn(nullable = false)
    String type;

    @JoinColumn(nullable = false)
    int amount;

    @JoinColumn(nullable = false)
    String difficultyLevel;

    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    public Question(String type, int amount, String difficultyLevel, Assessment assessment) {
        this.type = type;
        this.amount = amount;
        this.difficultyLevel = difficultyLevel;
        this.assessment = assessment;
    }
}
