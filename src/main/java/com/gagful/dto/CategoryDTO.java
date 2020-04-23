package com.gagful.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gagful.base.BaseEntity;
import com.gagful.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO extends BaseEntity {

    @NotBlank
    @Size(min = 3, max = 15)
    private String name;

    @JsonIgnore
    private List<PostDTO> posts;

    @Lob
    private byte[] icon;
}