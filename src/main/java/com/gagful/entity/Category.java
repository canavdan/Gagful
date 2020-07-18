package com.gagful.entity;

import com.gagful.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORIES", uniqueConstraints = {
        @UniqueConstraint(name = "categoryName_unique", columnNames = {"name"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @Column(name = "ICON")
    @Lob
    private byte[] icon;


}
