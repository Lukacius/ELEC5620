package com.elec5620.portal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    public Question(String type, int amount, String difficultyLevel) {
        this.type = type;
        this.amount = amount;
        this.difficultyLevel = difficultyLevel;
    }
}
