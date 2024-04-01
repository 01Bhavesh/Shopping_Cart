package com.Shopping_Cart.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Shopping_Cart.Models.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{

	public Optional<Coupon> findBycouponCode(String couponCode);
}
