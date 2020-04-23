package com.gagful.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gagful.base.BaseEntity;
import com.gagful.constant.VoteType;
import com.gagful.entity.*;
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
public class VoteUserDTO extends BaseEntity {

    private String userId;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private VoteType type;

}
