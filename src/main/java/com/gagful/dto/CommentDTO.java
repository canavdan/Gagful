package com.gagful.dto;

import com.gagful.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO extends BaseEntity {


    @NotBlank(message = "Username is mandatory")
    private String userUsername;

    @NotBlank
    @Size(min = 3, max = 15)
    private String content;

    private String postId;

}
