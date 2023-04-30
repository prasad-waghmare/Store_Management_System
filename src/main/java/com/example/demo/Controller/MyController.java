package com.example.demo.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.BuyProduct;
import com.example.demo.Entity.Product;
import com.example.demo.Entity.Shop;
import com.example.demo.Entity.SpecificBuyProductData;
import com.example.demo.Entity.User;
import com.example.demo.Repository.BuyProductRepo;
import com.example.demo.Repository.ProductRepo;
import com.example.demo.Repository.ShopRepo;
import com.example.demo.Repository.UserRepo;

@CrossOrigin
@RestController
public class MyController 
{
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	ShopRepo shopRepo;
	
	@Autowired
	BuyProductRepo buyProductRepo;
	
	@GetMapping("getAllBuyProductData")
	public List<SpecificBuyProductData> getAllBuyProductData()
	{
		try
		{
			List<SpecificBuyProductData> allBuyProductData = buyProductRepo.getAllBuyProductData();
			return allBuyProductData;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("addProductInUserAcc{productId}and{userId}and{userQuantity}")
	public boolean addProductInUserAcc(@PathVariable int productId,@PathVariable int userId,@PathVariable int userQuantity)
	{
		try
		{
			User user = userRepo.findById(userId).get();
			List<BuyProduct> buyProducts = user.getBuyProducts();
			Product product = productRepo.findById(productId).get();
			
			BuyProduct bp=new BuyProduct();
			bp.setExpDate(product.getExpDate());
			bp.setPrice(product.getPrice());
			bp.setProductName(product.getProductName());
			bp.setQuantity(userQuantity);
			
			buyProductRepo.save(bp);
			buyProducts.add(bp);
			userRepo.save(user);
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@GetMapping("updateProductQuantity{productId}and{userQuantity}")
	public boolean updateProductQuantity(@PathVariable int productId,@PathVariable int userQuantity)
	{
		try 
		{
			Product product = productRepo.findById(productId).get();
			int quantity = product.getQuantity();
			quantity=quantity-userQuantity;
			product.setQuantity(quantity);
			productRepo.save(product);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@GetMapping("getAvailableProd")
	public List<Product> getAvailableProd()
	{
		try 
		{
			return productRepo.getAvailableProd();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("getAllProducts")
	public List<Product> getAllProducts()
	{
		try
		{
			Shop shop = shopRepo.findById(1).get();
			return shop.getProducts();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("addProduct")
	public boolean addProduct(@RequestBody Product product)
	{
		try
		{
			productRepo.save(product);
			Shop shop = shopRepo.findById(1).get();
			List<Product> products = shop.getProducts();
			products.add(product);
			shopRepo.save(shop);
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@GetMapping("getUserRole{UIusername}")
	public User getUserRole(@PathVariable String UIusername)
	{
		try
		{
			User user = userRepo.findByUsername(UIusername);
			return user;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("loginUser{UIusername}and{UIpassword}")
	public boolean loginUser(@PathVariable String UIusername,@PathVariable String UIpassword)
	{
		try
		{
			//username correct check already present in db
			int countByUser = userRepo.countByUsername(UIusername);
			if(countByUser==1) 
			{
				User dbuser = userRepo.findByUsername(UIusername);
				if(UIpassword.equals(dbuser.getPassword()))
					return true;
				else
					return false;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@PostMapping("registerUsers{username}and{password}")
	public boolean registerUsers(@PathVariable String username,@PathVariable String password,@RequestBody User user)
	{
		try
		{
			user.setUsername(username);
			user.setPassword(password);
			user.setUserCreateDate(new Date());
			int countByUsername = userRepo.countByUsername(username);
			if(countByUsername==0)
				userRepo.save(user);
			else
				return false;
			return true;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
}
