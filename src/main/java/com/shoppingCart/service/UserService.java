package com.shoppingCart.service;

import com.shoppingCart.persistence.model.Role;
import com.shoppingCart.persistence.model.User;
import com.shoppingCart.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user) {

        user.setRole(Role.CUSTOMER);
        return userRepository.save(user);

    }
}
