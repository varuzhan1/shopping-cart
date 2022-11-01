package com.shoppingCart.controller;


import com.shoppingCart.persistence.model.User;
import com.shoppingCart.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(path = "/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED, reason = "OK")
    public void save(@RequestBody User user) {
        userService.save(user);
    }

}
