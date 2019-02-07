package com.know.services;

import com.know.models.*;

import javax.inject.Inject;
import java.util.List;

public class BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;

    @Inject
    public BookService(BookDao bookDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    public long countAllBooks() {
        return bookDao.countAll();
    }

    public List<BookModel> getAllBooks(long offset, long limit) {
        return bookDao.findAll(offset, limit);
    }

    public BookModel getBook(long bookId) {
        return bookDao.findById(bookId);
    }

    public long createBook(String bookName, long authorId) throws Exception {
        AuthorModel author = authorDao.findById(authorId);
        if (author == null) {
            throw new Exception("Author Not Found");
        }
        return bookDao.create(new BookModel(bookName, author));
    }
}
