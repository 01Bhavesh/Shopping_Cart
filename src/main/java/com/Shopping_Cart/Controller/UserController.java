package com.Shopping_Cart.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Shopping_Cart.Models.Order;
import com.Shopping_Cart.Models.Product;
import com.Shopping_Cart.Models.User;
import com.Shopping_Cart.Repository.ProductRepository;
import com.Shopping_Cart.Repository.UserRepository;
import com.Shopping_Cart.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/api/inventory")
	public List<Product> getAllProduct()
	{
		return productRepo.findAll();
	}
	
	@PostMapping("/api/userid/{userid}")
	public Order placedOrder(@PathVariable("userid") Integer userid,@RequestParam("qty") Integer qty,@RequestParam("coupon") String coupon) throws Exception
	{
		return userService.placedOrder(userid, (int)qty, coupon);
	}
	
	@GetMapping("/api/{userid}/orders")
	public List<Order> findOrderByUserId(@PathVariable("userid") Integer userid) throws Exception
	{
		return userService.findOrderByUserId(userid);
	}
	
	@GetMapping("/api/{userid}/orders/{orderid}")
	public Order findOrderByOrderId(@PathVariable("userid") Integer userid,@PathVariable("orderid") Integer orderid) throws Exception
	{
		List<Order> order = userService.findOrderByUserId(userid);
		if(order.get(orderid-1)!= null)
		{
			return order.get(orderid-1);
		}
		throw new Exception("Order not found");
	}
	
	@GetMapping("/api/users/{userid}")
	public Optional<User> findUser(@PathVariable("userid") Integer userid)
	{
		return userRepo.findById(userid);
	}
	
}
