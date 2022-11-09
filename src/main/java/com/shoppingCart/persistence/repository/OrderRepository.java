package com.shoppingCart.persistence.repository;

import com.shoppingCart.persistence.entity.Order;
import com.shoppingCart.persistence.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    /**
     * method remove order by id
     **/
    void removeById(Integer id);

    /**
     * find orders by status
     **/
    List<Order> findOrdersByStatus(OrderStatus status);

}
