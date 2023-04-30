package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>
{

	@Query(value = "SELECT * FROM product\r\n"
			+ "where quantity>0;",nativeQuery = true)
	List<Product> getAvailableProd();
}
