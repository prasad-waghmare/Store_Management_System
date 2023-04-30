package com.example.demo.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity@Data@NoArgsConstructor@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String firstName;
	String lastName;
	String emailID;
	long contactNumber;
	Date dateOfBirth;
	String username;
	String password;
	Date userCreateDate;
	int userRole;
	int activeUser;
	
	@ManyToMany
	List<BuyProduct> buyProducts;
}
