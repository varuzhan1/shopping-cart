package com.shoppingCart.persistence.repository;

import com.shoppingCart.persistence.model.Order;
import com.shoppingCart.persistence.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    void removeById(Integer id);

    List<Order> findOrdersByStatus(OrderStatus status);

}
