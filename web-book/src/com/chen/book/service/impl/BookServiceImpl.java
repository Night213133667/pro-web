package com.chen.book.service.impl;

import com.chen.book.dao.BookDAO;
import com.chen.book.pojo.Book;
import com.chen.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDAO bookDAO;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBook(Integer id) {
        return bookDAO.getBook(id);
    }
}
