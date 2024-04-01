package com.Shopping_Cart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Shopping_Cart.Models.Coupon;
import com.Shopping_Cart.Repository.CouponRepository;

@RestController
public class CouponController {

	@Autowired
	CouponRepository couponRepo;
	
	@GetMapping("/api/fetchCoupons")
	public List<Coupon> findAllCoupon()
	{
		return couponRepo.findAll();
	}
}
