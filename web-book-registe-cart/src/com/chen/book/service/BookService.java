package com.chen.book.service;

import com.chen.book.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookList();
    Book getBook(Integer id);
}
