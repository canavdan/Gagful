package com.gagful.entity;

import com.gagful.base.BaseEntity;
import com.gagful.constant.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "VOTES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    //@Min(value = 0,message = "Size can not be lower than 0")
    private Long count;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private VoteType type;
}
