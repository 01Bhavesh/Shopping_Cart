package com.Shopping_Cart.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Shopping_Cart.Models.Coupon;
import com.Shopping_Cart.Models.Order;
import com.Shopping_Cart.Models.Payment;
import com.Shopping_Cart.Models.User;
import com.Shopping_Cart.Repository.CouponRepository;
import com.Shopping_Cart.Repository.OrderRepository;
import com.Shopping_Cart.Repository.PaymentRepository;
import com.Shopping_Cart.Repository.ProductRepository;
import com.Shopping_Cart.Repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CouponRepository couponRepo;
	
	@Autowired
	DataLoader dataloader;
	
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	PaymentRepository paymentRepo;
	
	 Set<Integer> set = new HashSet<>();
	 
	@Override
	public Order placedOrder(Integer userId, Integer qty, String coupon) throws Exception {
		Payment payment = new Payment();
		Order order = new Order();
		Optional<Coupon> coup = couponRepo.findBycouponCode(coupon);
		if(!coup.isPresent())
		{
			throw new Exception("Invalid coupon") ;   //Checking for coupon is valid or not
		}
		if(qty<1 || qty > dataloader.product1.getQuantityAvailable())
		{
			throw new Exception( "Invalid quantity") ;  //Quantity is right or not
		}
		
		System.out.println(set.contains(userId));
		
		if(set.contains(userId))
		{
			Optional<User> user = userRepo.findById(userId);  //Checking user is present with userId or not
			if(user.isPresent())
			{
//				System.out.println(dataloader.coupon2.getDiscountAmount()+"ye nahi be");
				order.setUser(user.get()); 
				order.setQty(qty);
				order.setOrderDate(LocalDateTime.now());
				order.setTotalPrice(dataloader.product1.getPrice()*qty);
				dataloader.product1.setQuantityAvailable(dataloader.product1.getQuantityAvailable()-qty);  //reset the Available quantity
				productRepo.save(dataloader.product1);
				 order = orderRepo.save(order);
				 payment.setUserId(userId);
				 payment.setOrderId(order.getId());
				 payment.setTransactionId(123456789);
				 payment.setStatus("Successfull");
				 paymentRepo.save(payment);
				 return order;
			}
			
			throw new Exception("User not exit with id "+userId ) ;
			 
		}
		else
		{
			Optional<User> user = userRepo.findById(userId);
			if(user.isPresent())
			{
//				System.out.println(dataloader.coupon2.getDiscountAmount()+"yahi hai");
				set.add(userId);
				order.setUser(user.get()); 
				order.setQty(qty);
				order.setOrderDate(LocalDateTime.now());
				if(dataloader.coupon1.getCouponCode()==coupon)
				{
					Double val = dataloader.product1.getPrice()-dataloader.product1.getPrice()/dataloader.coupon2.getDiscountAmount();// Logic for Discount
					order.setTotalPrice(val*qty);
				}
				else
				{
					Double val = dataloader.product1.getPrice()-dataloader.product1.getPrice()/dataloader.coupon2.getDiscountAmount();
					order.setTotalPrice(val*qty);
				}
				dataloader.product1.setQuantityAvailable(dataloader.product1.getQuantityAvailable()-qty);//reset the Available quantity
				productRepo.save(dataloader.product1);
				order = orderRepo.save(order);
				payment.setUserId(userId);
				 payment.setOrderId(order.getId());
				 payment.setTransactionId(123456789);
				 payment.setStatus("Successfull");
				 paymentRepo.save(payment);
				return order;
			}
			
			throw new Exception("User not exit with id "+userId ) ;
		}
	}


	@Override
	public List<Order> findOrderByUserId(Integer userId) throws Exception {
		Optional<User> user = userRepo.findById(userId);
		if(user.isEmpty())
		{
			throw new Exception("User not found");
		}
		return user.get().getOrders();
	}






}
