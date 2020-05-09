package com.gagful.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {

    @NotBlank
    @Size(min = 3, max = 15)
    private String oldPassword;

    @NotBlank
    @Size(min = 3, max = 15)
    private String newPassword;

}
