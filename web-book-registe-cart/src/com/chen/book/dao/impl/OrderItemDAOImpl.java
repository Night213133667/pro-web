package com.chen.book.dao.impl;

import com.chen.book.dao.OrderItemDAO;
import com.chen.book.pojo.OrderItem;
import com.chen.myssm.basedao.BaseDAO;

public class OrderItemDAOImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) {
        super.executeUpdate("insert into t_order_item values(0,?,?,?)",orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId()) ;
    }
}
