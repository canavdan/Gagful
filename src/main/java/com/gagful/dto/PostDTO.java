package com.gagful.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gagful.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO extends BaseEntity {

    @NotBlank
    @Size(min = 3, max = 15)
    private String title;

    @Lob
    private byte[] image;

    @NotBlank(message = "Username is mandatory")
    private String userUsername;

    @NotBlank(message = "Category is mandatory")
    private String categoryName;

    private boolean isSensitive=false;

    @JsonIgnore
    private List<CommentDTO> comments;

    @JsonIgnoreProperties({"id", "createdDate", "lastModifiedDate" })
    private List<VoteDTO> votes;

    @JsonIgnoreProperties({"id", "createdDate", "lastModifiedDate" })
    private List<VoteUserDTO> voteUsers;

}
