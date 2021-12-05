package com.example.filestorageapp.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "files", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class File {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public File setName(String name) {
        this.name = name;
        return this;
    }

    public File setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }
}
