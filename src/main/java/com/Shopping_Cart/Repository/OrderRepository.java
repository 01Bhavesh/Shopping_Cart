package com.Shopping_Cart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Shopping_Cart.Models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
