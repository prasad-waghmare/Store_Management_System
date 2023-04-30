package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Shop;

public interface ShopRepo extends JpaRepository<Shop, Integer>
{

}
