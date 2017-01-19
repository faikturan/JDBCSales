package com.faikturan.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.faikturan.common.ConnectionDB;
import com.faikturan.model.Customer;
import com.faikturan.model.Product;
import com.faikturan.service.ProductService;

public class ProductServiceImpl implements ProductService {
	private static final Logger log = LogManager.getLogger(ProductServiceImpl.class);

	Connection cn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	@Override
	public Product findProductById(int id) {
		log.info("#findProductById");
		
		String sql = "SELECT p.id, " 
				+ "p.productName, " 
				+ "p.unitPrice, " 
				+ "p.stock, " 
				+ "p.createdDate," 
				+ "p.updatedDate, " 
				+ "p.deletedDate, " 
				+ "p.deleted "
				+ "FROM db_sales.product p" + "WHERE p.id = ?;";
		try {
			cn = new ConnectionDB().getConnection();
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			Product product = null;
			if (rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("productName"));
				product.setUnitPrice(rs.getDouble("unitPrice"));
				product.setStock(rs.getInt("stock"));
				product.setCreatedDate(rs.getString("createdDate"));
				product.setUpdatedDate(rs.getString("updatedDate"));
				product.setDeletedDate(rs.getString("deletedDate"));
				product.setDeleted(rs.getString("deleted"));
			}

			log.info(product);
			return product;
		} catch (Exception exception) {
			log.error(exception);
		} finally {
			try {
				if (cn != null)
					cn.close();
				if (pstm != null)
					pstm.close();
				if (rs != null)
					rs.close();
			} catch (Exception exception2) {
				log.error(exception2);
			}
		}
		return null;
	}

	@Override
	public List<Product> getProductList() {
		log.info("#getProductList");
		
		String sql = "SELECT p.id, " 
				+ "p.productName, " 
				+ "p.unitPrice, " 
				+ "p.stock, " 
				+ "p.createdDate," 
				+ "p.updatedDate, " 
				+ "p.deletedDate, " 
				+ "p.deleted "
				+ "FROM db_sales.product p;";
		try {
			cn = new ConnectionDB().getConnection();
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			List<Product> productList = new ArrayList<Product>();
			Product product = null;
			while (rs.next()) {
				product = new Product();
				product.setId(rs.getInt("id"));
				product.setProductName(rs.getString("productName"));
				product.setUnitPrice(rs.getDouble("unitPrice"));
				product.setStock(rs.getInt("stock"));
				product.setCreatedDate(rs.getString("createdDate"));
				product.setUpdatedDate(rs.getString("updatedDate"));
				product.setDeletedDate(rs.getString("deletedDate"));
				product.setDeleted(rs.getString("deleted"));
				productList.add(product);
			}

			int count = productList.size();
			log.info("Records selected: "+count ) ;
			return productList;
		} catch (Exception exception) {
			log.error(exception);
		} finally {
			try {
				if (cn != null)
					cn.close();
				if (pstm != null)
					pstm.close();
				if (rs != null)
					rs.close();
			} catch (Exception exception2) {
				log.error(exception2);
			}
		}
		return null;
	}

	@Override
	public boolean updateProduct(Product product) {
		log.info("#updateProduct");
		
		boolean flag = false;
		
		String sql = "UPDATE db_sales.product p, " 
				+ "SET p.productName = ?, " 
				+ "p.unitPrice = ?, " 
				+ "p.stock = ?, " 
				+ "p.createdDate = ?," 
				+ "p.updatedDate = NOW(), " 
				+ "p.deletedDate = ?, " 
				+ "p.deleted = ?"
				+ "WHERE p.id = ?;";
		try {
			cn = new ConnectionDB().getConnection();
			pstm = cn.prepareStatement(sql);
			
				pstm.setString(1, product.getProductName());
				pstm.setDouble(2, product.getUnitPrice());
				pstm.setInt(3, product.getStock());
				pstm.setString(4, product.getCreatedDate());
				pstm.setString(5, product.getDeletedDate());
				pstm.setString(6, product.getDeleted());
			}

			log.info(product);
			return product;
		} catch (Exception exception) {
			log.error(exception);
		} finally {
			try {
				if (cn != null)
					cn.close();
				if (pstm != null)
					pstm.close();
				if (rs != null)
					rs.close();
			} catch (Exception exception2) {
				log.error(exception2);
			}
		}
		return null;
	}

	@Override
	public boolean deleteProduct(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
