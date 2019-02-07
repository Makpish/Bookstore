package com.know.models;

import javax.persistence.*;

@Entity
@Table(name = "book")
@NamedQueries({
        @NamedQuery(name = "com.know.models.BookModel.countAll",
                query = "select count(a) from BookModel a"),
        @NamedQuery(name = "com.know.models.BookModel.findAll",
                query = "select a from BookModel a"),
        @NamedQuery(name = "com.know.models.BookModel.countByName",
                query = "select count(a) from BookModel a "
                        + "where lower(a.name) like :name "),
        @NamedQuery(name = "com.know.models.BookModel.findByName",
                query = "select a from BookModel a "
                        + "where lower(a.name) like :name "),
        @NamedQuery(name = "com.know.models.BookModel.countByAuthor",
                query = "select count(a) from BookModel a "
                        + "where a.author like :author "),
        @NamedQuery(name = "com.know.models.BookModel.findByAuthor",
                query = "select a from BookModel a "
                        + "where a.author like :author ")
})
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private AuthorModel author;

    public BookModel() {
    }

    public BookModel(String name, AuthorModel author) {
        this.name = name;
        this.author = author;
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

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }
}
