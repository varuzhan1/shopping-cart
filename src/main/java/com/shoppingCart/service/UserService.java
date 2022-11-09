package com.shoppingCart.service;

import com.shoppingCart.persistence.entity.Role;
import com.shoppingCart.persistence.entity.User;
import com.shoppingCart.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {

        user.setRole(Role.CUSTOMER);
        return userRepository.save(user);

    }
}
