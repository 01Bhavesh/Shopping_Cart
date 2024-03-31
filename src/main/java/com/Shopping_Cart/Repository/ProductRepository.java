package com.Shopping_Cart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Shopping_Cart.Models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
