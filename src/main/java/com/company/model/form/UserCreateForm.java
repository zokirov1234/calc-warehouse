package com.company.model.form;

import com.company.enums.Roles;
import com.company.enums.SalaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserCreateForm {

    private String firstName;
    private String lastName;
    private String middleName;
    private String username;
    private String password;
    private SalaryType salaryType;
    private boolean isWorking;
    private Timestamp dateOfBirth;
    private boolean isMale;
    private int educationTypeId;
    private String phoneNumber;
    private int attachId;
}
