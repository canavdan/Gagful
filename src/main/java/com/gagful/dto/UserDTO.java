package com.gagful.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gagful.base.BaseEntity;
import com.gagful.entity.Comment;
import com.gagful.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO extends BaseEntity  {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    private String cellPhone;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @Min(value = 15, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private Integer age;

    private boolean active=true;

    private boolean showSensitive=true;

    @JsonIgnore
    private List<PostDTO> posts;
    @JsonIgnore
    private List<CommentDTO> comments;
}
