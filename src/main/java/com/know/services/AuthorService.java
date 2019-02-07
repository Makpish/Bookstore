package com.know.services;

import com.know.models.*;

import javax.inject.Inject;
import java.util.List;

public class AuthorService {

    private final AuthorDao authorDao;
    private final BookDao bookDao;

    @Inject
    public AuthorService(AuthorDao authorDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }

    public List<AuthorModel> getAllAuthors(long offset, long limit) {
        return authorDao.findAll(offset, limit);
    }

    public long countAllAuthors() {
        return authorDao.countAll();
    }

    public AuthorModel getAuthor(long id) {
        return authorDao.findById(id);
    }

    public long countAuthorBooks(long id) {
        AuthorModel author = authorDao.findById(id);
        return bookDao.countByAuthor(author);
    }

    public List<BookModel> getAuthorBooks(long id, long offset, long limit) {
        AuthorModel author = authorDao.findById(id);
        return bookDao.findByAuthor(author, offset, limit);
    }

    public long createNewAuthor(String name) {
        return authorDao.create(new AuthorModel(name));
    }
}
