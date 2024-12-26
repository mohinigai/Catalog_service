package com.task.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.task.dto.Inventory;
import com.task.exception.ResourceNotFoundException;
import com.task.model.Product;
import com.task.repository.Productrepository;

@Service
public class ProductServiceImpl implements ProductServiceI {
	@Autowired
	private Productrepository productrepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Product CreateProduct(Product product) {
		Product createProduct = productrepository.save(product);
		return createProduct;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> list = productrepository.findAll().stream().map(a -> a).collect(Collectors.toList());
		return list;
	}

	public Product updateProduct(Product product, Long pid) {
		Product prodctById = productrepository.findById(pid)
				.orElseThrow(() -> new ResourceNotFoundException("product Id not found" + pid));
		prodctById.setName(product.getName());
		prodctById.setDescription(product.getDescription());
		prodctById.setPrice(prodctById.getPrice());
		prodctById.setIsactive(product.getIsactive());
		Product updatedproduct = productrepository.save(prodctById);
		return updatedproduct;
	}

	@Override
	public Product getSingleProduct(Long pid) {
		Product productById = productrepository.findById(pid)
				.orElseThrow(() -> new RuntimeException("product Id not found" + pid));

		String url = "http://localhost:9094/api/inventory/product/" + productById.getPid();
		Inventory[] inv = restTemplate.getForObject(url, Inventory[].class);
		List<Inventory> asList = Arrays.asList(inv);
		productById.setInventory(asList);

		return productById;
	}

	@Override
	public void deleteProduct(Long pid) {
		Product byId = productrepository.findById(pid)
				.orElseThrow(() -> new RuntimeException("product Id not found" + pid));
		productrepository.delete(byId);
	}

	@Override
	public List<Product> getAllProductByOrderId(Long orderId) {
		List<Product> byOrderId = productrepository.findByOrderId(orderId);
		return byOrderId;
	}

}
