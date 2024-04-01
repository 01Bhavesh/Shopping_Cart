package com.Shopping_Cart.Service;

import java.util.List;

import com.Shopping_Cart.Models.Order;

public interface UserService {

	
	public List<Order> findOrderByUserId(Integer userId) throws Exception;

	public Order placedOrder(Integer userId, Integer qty, String coupon) throws Exception;
}
