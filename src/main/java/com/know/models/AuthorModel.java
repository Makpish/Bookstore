package com.know.models;

import javax.persistence.*;

@Entity
@Table(name = "author")
@NamedQueries({
        @NamedQuery(name = "com.know.models.AuthorModel.countAll",
                query = "select count(a) from AuthorModel a"),
        @NamedQuery(name = "com.know.models.AuthorModel.findAll",
                query = "select a from AuthorModel a"),
        @NamedQuery(name = "com.know.models.AuthorModel.findByName",
                query = "select a from AuthorModel a "
                        + "where lower(a.name) like :name"),
        @NamedQuery(name = "com.know.models.AuthorModel.countByName",
                query = "select count(a) from AuthorModel a "
                        + "where lower(a.name) like :name ")
})
public class AuthorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    public AuthorModel() {
    }

    public AuthorModel(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
