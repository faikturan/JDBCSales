package com.faikturan.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.faikturan.common.ConnectionDB;
import com.faikturan.model.OrderDetail;
import com.faikturan.model.Orders;
import com.faikturan.model.Product;
import com.faikturan.service.OrderDetailService;
import com.faikturan.service.ProductService;

public class OrderDetailServiceImpl implements OrderDetailService {
	private static final Logger log = LogManager.getLogger(OrderDetailServiceImpl.class);

	Connection cn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	@Override
	public List<OrderDetail> findDetailbyOrderId(int id) {
		log.info("#findDetailbyOrderId");
		
		String sql = "SELECT od.id,"
				+ "od.orderId, "
				+ "od.productId, "
				+ "od.unitPrice, "
				+ "od.quantity, "
				+ "od.subTotal, "
				+ "FROM db_sales.orderDetail od"
				+ "WHERE od.orderId = ?;";
		try {
			cn = new ConnectionDB().getConnection();
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			OrderDetail orderDetail = null;
			
			ProductService productService = new ProductServiceImpl();
			
			while (rs.next()) {
				orderDetail = new OrderDetail();
				orderDetail.setId(rs.getInt("id"));
				orderDetail.setOrderId(rs.getInt("orderId"));
				orderDetail.setProductId(rs.getInt("productId"));
				orderDetail.setUnitPrice(rs.getDouble("unitPrice"));
				orderDetail.setQuantity(rs.getInt("quantity"));
				orderDetail.setSubTotal(rs.getDouble("subTotal"));
				orderDetail.setProduct(productService.findProductById(rs.getInt("productId")));
				orderDetailList.add(orderDetail);
			}
			
			int count = orderDetailList.size();
			log.info("Records selected: " +count);
			return orderDetailList;
			
		} catch (Exception exception) {
			log.error(exception);
		}finally {
			try {
				if(cn != null)
					cn.close();
				if(pstm != null)
					pstm.close();
				if(rs != null)
					rs.close();
			} catch (Exception exception2) {
				log.error(exception2);
			}
		}
		return null;
	}

	@Override
	public boolean addDetailsInOrder(Orders order, List<OrderDetail> orderDetailList) {
		log.info("addDetailsInOrder");
		
		boolean flag = false;
		try {
			int counter = 0;
			ProductService productService = new ProductServiceImpl();
			for (OrderDetail orderDetail : orderDetailList) {
				Product product = productService.findProductById(orderDetail.getProductId());
				if (product.getStock() < orderDetail.getQuantity()) {
					log.warn("No stock for product: ["+product.getId()+"]"
							+ "["+product.getProductName()+"]"
							+ ".Current stock is: " +
							product.getStock()+". Quantity required is: " +
							orderDetail.getQuantity()); 
							counter++;
							//If more than 1 product doesn't present stock
				}
			}
			if (counter > 0) {//if counter is 0 then all products have stock, 
				//More than 0 means some product does not have stock then bye bye
				flag = false;
				return flag;
				
			}
		} catch (Exception exception) {
			log.error("Insufficient Stock");
		}
		
		//Begin transaction
		
		
	}

}
