package com.gagful.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gagful.base.BaseEntity;
import com.gagful.constant.VoteType;
import com.gagful.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private Post post;

    private Long count;

    @Enumerated(EnumType.STRING)
    private VoteType type;
}

