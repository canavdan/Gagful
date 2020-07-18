package com.gagful.entity;


import com.gagful.base.BaseEntity;
import com.gagful.constant.VoteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "VOTEUSER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoteUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private VoteType type;

}
