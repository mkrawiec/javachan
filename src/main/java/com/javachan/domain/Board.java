package com.javachan.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board extends AbstractEntity {

    private String name, slug, description;

    @OneToMany(mappedBy = "board")
    private List<Thread> threads;

    public Board(String name, String slug, String  description) {
        this.name = name;
        this.slug = slug;
        this.description = description;
    }

    public Board() {
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public List<Thread> getThreads() {
        return threads;
    }

}
