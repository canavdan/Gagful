package com.gagful.dto;

import com.gagful.base.BaseEntity;
import com.gagful.constant.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteUserDTO extends BaseEntity {

    private String userUsername;

    @Enumerated(EnumType.STRING)
    private VoteType type;

}
