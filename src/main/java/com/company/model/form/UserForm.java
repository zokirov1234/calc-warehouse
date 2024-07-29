package com.company.model.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserForm {

    private String firstName;

    private String lastName;
    @NotEmpty(message = "Must not be Empty and NULL")
    private String username;
    @NotEmpty(message = "Must not be Empty and NULL")
    private String password;
    @NotEmpty(message = "Must not be Empty and NULL")
    private String role;

}
