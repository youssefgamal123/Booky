package com.Application.Booky.repository;


import com.Application.Booky.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository <Role,Long> {


    Optional <Role> findByname(String role_name);

}
