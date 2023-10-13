package com.chen.book.service;

import com.chen.book.pojo.OrderBean;
import com.chen.book.pojo.User;

import java.util.List;

public interface OrderService {
    void addOrderBean(OrderBean orderBean);
    List<OrderBean> getOrderList(User user);
}
