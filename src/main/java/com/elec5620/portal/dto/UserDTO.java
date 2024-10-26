package com.elec5620.portal.dto;

import com.elec5620.portal.model.DifficultyLevel;
import com.elec5620.portal.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDTO {
    private Long id;
    private String email;
    private String name;
    private String password;
    private String userType;
    private String userPrompt;
    private DifficultyLevel difficultyLevel;


}
