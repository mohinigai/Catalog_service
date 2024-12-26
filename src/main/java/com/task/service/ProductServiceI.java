package com.task.service;

import java.util.List;

import com.task.model.Product;

public interface ProductServiceI {
	
	public Product CreateProduct(Product product);
	
	public List<Product> getAllProduct();
	
	public Product updateProduct(Product product, Long pid);
	
	public Product getSingleProduct(Long pid);
	
	public void deleteProduct(Long pid);
	
	public List<Product> getAllProductByOrderId(Long orderId);

}
