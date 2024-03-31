package com.Shopping_Cart.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.Shopping_Cart.Models.Coupon;
import com.Shopping_Cart.Models.Product;
import com.Shopping_Cart.Models.User;
import com.Shopping_Cart.Repository.CouponRepository;
import com.Shopping_Cart.Repository.ProductRepository;
import com.Shopping_Cart.Repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {

	    private UserRepository userRepository;
	    private ProductRepository productRepository;
	    private CouponRepository couponRepository;

	    @Autowired
	    public DataLoader(UserRepository userRepository, ProductRepository productRepository, CouponRepository couponRepository) {
	        this.userRepository = userRepository;
	        this.productRepository = productRepository;
	        this.couponRepository = couponRepository;
	    }

	    @Override
	    public void run(ApplicationArguments args) {
	        // Insert data into users table
	        User user1 = new User(1,"Rohit","Rohit45","rohit45@gmail.com","Mumbai",null,null);
	        userRepository.save(user1);
	        User user2 = new User(2,"Virat","Virat18","virat18@gmail.com","Bangloru",null,null);
	        userRepository.save(user2);
	        
	        // Insert data into products table
	        Product product1 = new Product(1,"Bat", "Cricket Bat", 1000, 100,null);
	        productRepository.save(product1);

	        // Insert data into coupons table
	        Coupon coupon1 = new Coupon(1,"OFF5", 0.05,null);
	        couponRepository.save(coupon1);
	        Coupon coupon2 = new Coupon(2,"OFF10", 0.1 , null);
	        couponRepository.save(coupon2);
	    }
	}

