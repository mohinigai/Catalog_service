package com.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.model.Product;
@Repository
public interface Productrepository extends JpaRepository<Product, Long> {

	public List<Product> findByOrderId(Long orderId );
}
