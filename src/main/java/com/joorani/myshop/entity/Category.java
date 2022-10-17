package com.joorani.myshop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private int depth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children;

    @Builder
    public Category(Long id, String name, Category parent, int depth) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.children = new ArrayList<>();
        this.depth = depth;
    }

    public void addChildrenCategory(Category children) {

        this.children.add(children);
    }

}
