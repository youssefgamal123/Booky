package com.Application.Booky.repository;


import com.Application.Booky.entity.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository <role,Long> {


    Optional <role> findByname(String role_name);

}
