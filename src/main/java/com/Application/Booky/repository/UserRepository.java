package com.Application.Booky.repository;

import com.Application.Booky.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Integer> {

    Optional <User> findByemail(String email);
}
