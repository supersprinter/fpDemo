package com.finnplay.demo.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class PasswordDTO {

    private String oldPassword;
    private String newPassword;
    private String passwordConfirm;
}
