package com.faikturan.service;

import java.util.List;

import com.faikturan.model.Product;

public interface ProductService {
	Product findById(int id);
	List<Product> getProductList();
	boolean updateProduct(Product product);
	boolean deleteProduct(int id);
	boolean addProduct(Product product);
}
