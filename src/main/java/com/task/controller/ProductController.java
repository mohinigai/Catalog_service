package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Product;
import com.task.service.ProductServiceI;
import com.task.util.ApiResponse;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductServiceI productServiceI;

	@GetMapping("/products")
	ResponseEntity<List<Product>> getAllProducts() {
		List<Product> list = productServiceI.getAllProduct();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}

	@PostMapping("/products")
	ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product createProduct = productServiceI.CreateProduct(product);
		return new ResponseEntity<Product>(createProduct, HttpStatus.CREATED);
	}

	@PutMapping("/products/{id}")
	ResponseEntity<Product> updateproduct(@RequestBody Product product, @PathVariable("id") Long pid) {
		Product updateProduct = productServiceI.updateProduct(product, pid);
		return new ResponseEntity<Product>(updateProduct, HttpStatus.CREATED);
	}

	@DeleteMapping("/products/{id}")
	ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Long pid) {
		productServiceI.deleteProduct(pid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Product deleted succefull ", true), HttpStatus.CREATED);
	}

	@GetMapping("/products/{id}")
	ResponseEntity<Product> getSinleProduct(@PathVariable("id") Long pid) {
		Product singleProduct = productServiceI.getSingleProduct(pid);
		return new ResponseEntity<Product>(singleProduct, HttpStatus.OK);
	}

	@GetMapping("/products/order/{id}")
	ResponseEntity<List<Product>> getAllProductsByOrderId(@PathVariable("id") Long OrderId) {
		List<Product> allProductByOrderId = productServiceI.getAllProductByOrderId(OrderId);
		return new ResponseEntity<List<Product>>(allProductByOrderId, HttpStatus.OK);
	}

}
