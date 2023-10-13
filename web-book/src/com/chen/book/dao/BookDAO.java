package com.chen.book.dao;


import com.chen.book.pojo.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBookList();
    Book getBook(Integer id);
}
