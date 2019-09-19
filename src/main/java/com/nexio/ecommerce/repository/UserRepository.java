package com.nexio.ecommerce.repository;

import com.nexio.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(" select u from User u where u.username = ?1")
    Optional<User> findUserWithName(String username);
}
