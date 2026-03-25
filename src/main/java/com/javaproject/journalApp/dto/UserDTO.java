package com.javaproject.journalApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NonNull
    private String userName;
    private String email;
    private Boolean sentimentAnalysis;
    @NonNull
    private String password;
}
