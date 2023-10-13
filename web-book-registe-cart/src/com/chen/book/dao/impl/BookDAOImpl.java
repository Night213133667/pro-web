package com.chen.book.dao.impl;

import com.chen.book.dao.BookDAO;
import com.chen.book.pojo.Book;
import com.chen.myssm.basedao.BaseDAO;

import java.util.List;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
        return executeQuery("select * from t_book where bookStatus=0");
    }

    @Override
    public Book getBook(Integer id) {
        return load("select * from t_book where id = ? " , id);
    }
}
