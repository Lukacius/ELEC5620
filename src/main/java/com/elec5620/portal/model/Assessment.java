package com.elec5620.portal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Assessment {
    @Id
    @GeneratedValue
    private long id;

    @JoinColumn(nullable = false)
    String assessmentType;

    @OneToMany(mappedBy = "assessment")
    Collection<Question> questions;

    public Assessment(String assessmentType) {
        this.assessmentType = assessmentType;
        this.questions = new ArrayList<>();
    }
}
