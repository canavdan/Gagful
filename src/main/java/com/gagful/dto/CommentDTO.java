package com.gagful.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gagful.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"id", "createdDate", "lastModifiedDate"})
public class CommentDTO extends BaseEntity {

    @NotBlank(message = "Username is mandatory")
    private String userUsername;

    @Lob
    private byte[] userProfilePic;

    @NotBlank
    @Size(min = 3, max = 15)
    private String content;

    private String postId;

}
