package com.faikturan.service;

import java.util.List;

import com.faikturan.model.OrderDetail;
import com.faikturan.model.Orders;

public interface OrderDetailService {
	List<OrderDetail> findDetailbyOrderId(int id);
	boolean addDetailsInOrder(Orders order, List<OrderDetail> orderDetailList);

}
