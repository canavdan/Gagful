package com.gagful.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gagful.base.BaseEntity;
import com.gagful.constant.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "POSTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "TITLE")
    private String title;


    @Column(name = "IMAGE")
    @Lob
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "IS_SENSITIVE")
    private boolean isSensitive=false;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Comment> comments;


    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Vote> votes= new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<VoteUser> voteUsers= new ArrayList<>();


}
