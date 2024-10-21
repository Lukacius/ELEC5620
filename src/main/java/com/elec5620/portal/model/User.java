package com.elec5620.portal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @JoinColumn(nullable = false)
    String userName;

    @JoinColumn(nullable = false)
    String emailAddress;

    @JoinColumn(nullable = false)
    String userType;

    @JoinColumn(nullable = false)
    String userDescription;

    String academicLevel;

    public User(String userName, String emailAddress, String userType, String userDescription, String academicLevel) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.userType = userType;
        this.userDescription = userDescription;
        this.academicLevel = academicLevel;
    }
}
