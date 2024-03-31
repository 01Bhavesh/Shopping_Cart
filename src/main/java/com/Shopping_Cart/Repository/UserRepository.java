package com.Shopping_Cart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Shopping_Cart.Models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
