package com.Shopping_Cart.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "coupons")
public class Coupon {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Integer id;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "discount_amount")
    private double discountAmount;

//    @Temporal(TemporalType.DATE)
//    @Column(name = "expiry_date")
//    private Date expiryDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
