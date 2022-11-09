package com.shoppingCart.persistence.repository;

import com.shoppingCart.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * find user  by email
     **/
    Optional<User> findByEmail(String email);

}
