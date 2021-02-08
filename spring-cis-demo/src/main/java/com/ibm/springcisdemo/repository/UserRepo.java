package com.ibm.springcisdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.springcisdemo.entity.User;
import org.springframework.cache.annotation.Cacheable;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    //@Cacheable
	 Optional<User> findByUsername(String username);
}
