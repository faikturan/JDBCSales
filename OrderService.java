package com.faikturan.service;

import com.faikturan.model.Orders;

public interface OrderService {
	int createOrder(Orders order);
	Orders findOrderById(int id);
	boolean updateOrderWithOrderDetail(Orders order);
}
