package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.BuyProduct;
import com.example.demo.Entity.SpecificBuyProductData;

public interface BuyProductRepo extends JpaRepository<BuyProduct, Integer>
{
	
	@Query(value = "SELECT u.first_name as firstName,\r\n"
			+ "bp.product_name as productName,\r\n"
			+ "bp.quantity as quantity,\r\n"
			+ "bp.price as price,\r\n"
			+ "u.contact_number as contactNumber \r\n"
			+ "FROM \r\n"
			+ "user u join user_buy_products ubp on u.id=ubp.user_id\r\n"
			+ "join buy_product bp on ubp.buy_products_id=bp.id",nativeQuery = true)
	List<SpecificBuyProductData> getAllBuyProductData();
}
