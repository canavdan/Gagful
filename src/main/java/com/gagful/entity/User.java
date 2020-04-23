package com.gagful.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gagful.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements Serializable  {
    private static final long serialVersionUID = 1L;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CELLPHONE")
    private String cellPhone;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name="AGE")
    private Integer age;

    @Column(name = "IS_ACTIVE")
    private boolean active=true;

    @Column(name = "SHOW_SENSITIVE")
    private boolean showSensitive=true;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Post> posts= new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Comment> comments= new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<VoteUser> voteUsers= new ArrayList<>();
}
