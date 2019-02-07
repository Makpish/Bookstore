package com.know;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.know.injection.BookstoreModule;
import com.know.models.*;
import com.know.resources.AuthorResource;
import com.know.resources.BookResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BookstoreApplication extends Application<BookstoreConfiguration> {

    public static void main(final String[] args) throws Exception {
        new BookstoreApplication().run(args);
    }

    @Override
    public String getName() {
        return "Bookstore";
    }

    private final HibernateBundle<BookstoreConfiguration> hibernateBundle
            = new HibernateBundle<BookstoreConfiguration>(AuthorModel.class, BookModel.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(
                BookstoreConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<BookstoreConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final BookstoreConfiguration configuration,
                    final Environment environment) {
        BookstoreModule bookstoreModule = new BookstoreModule(hibernateBundle.getSessionFactory());
        Injector injector = Guice.createInjector(bookstoreModule);
        environment.jersey().register(injector.getInstance(AuthorResource.class));
        environment.jersey().register(injector.getInstance(BookResource.class));
    }

}
