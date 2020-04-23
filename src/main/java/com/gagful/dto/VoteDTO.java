package com.gagful.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gagful.base.BaseEntity;
import com.gagful.constant.VoteType;
import com.gagful.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private Post post;

    //@Min(value = 0,message = "Size can not be lower than 0")
    private Long count;

    @Enumerated(EnumType.STRING)
    private VoteType type;
}

