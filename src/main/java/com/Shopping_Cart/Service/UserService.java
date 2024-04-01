package com.Shopping_Cart.Service;

import java.util.List;

import com.Shopping_Cart.Models.Order;

public interface UserService {

	
	public Order placedOrder(Integer userId,int qty,String coupon) throws Exception;
	
	public List<Order> findOrderByUserId(Integer userId) throws Exception;
}
