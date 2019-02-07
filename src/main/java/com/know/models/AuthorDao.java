package com.know.models;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.List;

public class AuthorDao extends AbstractDAO<AuthorModel> {


    @Inject
    public AuthorDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<AuthorModel> findAll(long offset, long limit) {
        return list(namedQuery("com.know.models.AuthorModel.findAll")
                .setFirstResult((int) offset)
                .setMaxResults((int) limit)
        );
    }

    public long countAll() {
        return (long) namedQuery("com.know.models.AuthorModel.countAll").uniqueResult();
    }

    public AuthorModel findById(long id) {
        return get(id);
    }

    public long create(AuthorModel author) {
        return persist(author).getId();
    }

    public List<AuthorModel> findByName(String name, long offset, long limit) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name.toLowerCase()).append("%");
        return list(
                namedQuery("com.know.models.AuthorModel.findByName")
                        .setParameter("name", builder.toString())
                        .setFirstResult((int) offset)
                        .setMaxResults((int) limit)
        );
    }

    public long countByName(String name) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name.toLowerCase()).append("%");
        return (long) namedQuery("com.know.models.AuthorModel.countByName")
                        .setParameter("name", builder.toString()
        ).uniqueResult();
    }
}
