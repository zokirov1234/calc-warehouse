package com.company.model.form;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserListForm {

    private String firstName;
    private String lastName;
    private String username;
    private int page;
    private int size;
}
