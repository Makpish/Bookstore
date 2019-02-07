package com.know.models;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.List;

public class BookDao extends AbstractDAO<BookModel> {

    @Inject
    public BookDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public long countAll() {
        return (long) namedQuery("com.know.models.BookModel.countAll").uniqueResult();
    }

    public List<BookModel> findAll(long offset, long limit) {
        return list(namedQuery("com.know.models.BookModel.findAll")
                .setFirstResult((int) offset)
                .setMaxResults((int) limit));
    }

    public BookModel findById(long id) {
        return get(id);
    }

    public long create(BookModel book) {
        return persist(book).getId();
    }

    public long countByName(String name) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name.toLowerCase()).append("%");
        return (long)
                namedQuery("com.know.models.BookModel.countByName")
                        .setParameter("name", builder.toString()).uniqueResult();
    }

    public List<BookModel> findByName(String name, long offset, long limit) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name.toLowerCase()).append("%");
        return list(
                namedQuery("com.know.models.BookModel.findByName")
                        .setParameter("name", builder.toString())
                        .setFirstResult((int) offset)
                        .setMaxResults((int) limit)
        );
    }

    public long countByAuthor(AuthorModel author) {
        return (long)
                namedQuery("com.know.models.BookModel.countByAuthor")
                        .setParameter("author", author).uniqueResult();
    }

    public List<BookModel> findByAuthor(AuthorModel author, long offset, long limit) {
        return list(
                namedQuery("com.know.models.BookModel.findByAuthor")
                        .setParameter("author", author)
                        .setFirstResult((int) offset)
                        .setMaxResults((int) limit)
        );
    }
}
