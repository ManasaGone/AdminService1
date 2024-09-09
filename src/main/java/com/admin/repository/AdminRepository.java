package com.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.admin.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>  {

	 boolean existsByUsername(String username);
     Optional<Admin> findByUsername(String username);
     Optional<Admin> findByEmail(String email);
     boolean existsByEmail(String email);

	
}
