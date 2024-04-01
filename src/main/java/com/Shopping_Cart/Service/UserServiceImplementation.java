package com.Shopping_Cart.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Shopping_Cart.Models.Coupon;
import com.Shopping_Cart.Models.Order;
import com.Shopping_Cart.Models.User;
import com.Shopping_Cart.Repository.CouponRepository;
import com.Shopping_Cart.Repository.OrderRepository;
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
	
	 Map<Integer, Integer> coupons = new HashMap<>();
	public Order placedOrder(Integer userId, int qty, String coupon) throws Exception {
		
		Order order = new Order();
		Optional<Coupon> coup = couponRepo.findBycouponCode(coupon);
		if(!coup.isPresent())
		{
			throw new Exception("Invalid coupon") ;
		}
		if(qty<1 || qty > dataloader.product1.getQuantityAvailable())
		{
			throw new Exception( "Invalid quantity") ;
		}
		
		
		if(coupons.containsKey(userId))
		{
//			Optional<User> user = userRepo.findById(userId);
			Optional<User> user = userRepo.findById(userId);
			System.out.println(user);
			if(!user.isEmpty())
			{
				order.setUser(user.get()); 
				order.setQty(qty);
				order.setOrderDate(LocalDateTime.now());
				order.setTotalPrice(dataloader.product1.getPrice()*qty);
				dataloader.product1.setQuantityAvailable(dataloader.product1.getQuantityAvailable()-qty);
				productRepo.save(dataloader.product1);
				 order = orderRepo.save(order);
				 return order;
			}
			
			throw new Exception("User not exit with id "+userId ) ;
			 
		}
		else
		{
			Optional<User> user = userRepo.findById(userId);
			if(user.isPresent())
			{
				coupons.put(userId,1);
				order.setUser(user.get()); 
				order.setQty(qty);
				order.setOrderDate(LocalDateTime.now());
				if(dataloader.coupon1.getCouponCode()==coupon)
				{
					order.setTotalPrice(dataloader.product1.getPrice()*qty*(dataloader.coupon1.getDiscountAmount())/100);
				}
				else
				{
					order.setTotalPrice(dataloader.product1.getPrice()*qty*(dataloader.coupon2.getDiscountAmount())/100);
				}
				dataloader.product1.setQuantityAvailable(dataloader.product1.getQuantityAvailable()-qty);
				productRepo.save(dataloader.product1);
				order = orderRepo.save(order);
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
