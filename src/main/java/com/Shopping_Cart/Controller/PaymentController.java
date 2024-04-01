package com.Shopping_Cart.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Shopping_Cart.Models.Payment;
import com.Shopping_Cart.Models.User;
import com.Shopping_Cart.Repository.OrderRepository;
import com.Shopping_Cart.Repository.PaymentRepository;
import com.Shopping_Cart.Repository.UserRepository;
import com.Shopping_Cart.Service.UserService;

@RestController
public class PaymentController {
	
	@Autowired
	   private OrderRepository orderRepo;
	
	@Autowired
	PaymentRepository paymentRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;

	@GetMapping("/api/userid/{userid}/orderid/{orderid}")
	public Payment checkPayment(@PathVariable("userid") Integer userid,@PathVariable("orderid") Integer orderId) throws Exception
	{
		Optional<User> user = userRepo.findById(orderId);
		if(user.get().getOrders().get(orderId-1) != null)
		{
			Payment pay = paymentRepo.findPaymentByOrderId(orderId);
			return pay;
		}
		throw new Exception("Invalid userId or OrderId");
	}
}
