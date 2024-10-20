package com.elec5620.portal.dto;

import com.elec5620.portal.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO {
    private long id;
    String emailAddress;
    String userType;
    String academicLevel;

    public UserDTO(User userEntity) {
        this.id = userEntity.getId();
        this.emailAddress = userEntity.getEmailAddress();
        this.userType = userEntity.getUserType();
        this.academicLevel = userEntity.getAcademicLevel();
    }
}
