package com.know.injection;

import com.google.inject.AbstractModule;
import org.hibernate.SessionFactory;

public class BookstoreModule extends AbstractModule {

    private SessionFactory sessionFactory;

    public BookstoreModule(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected void configure() {
        bind(SessionFactory.class).toInstance(sessionFactory);
    }
}
