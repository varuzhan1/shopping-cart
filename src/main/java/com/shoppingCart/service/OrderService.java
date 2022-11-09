package com.shoppingCart.service;


import com.shoppingCart.mapper.OrderMapper;
import com.shoppingCart.persistence.dto.OrderDto;
import com.shoppingCart.persistence.entity.Order;
import com.shoppingCart.persistence.entity.OrderStatus;
import com.shoppingCart.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderService {

    private OrderRepository repo;

    private OrderMapper orderMapper;
    @Autowired
    public OrderService(OrderRepository repo, OrderMapper orderMapper) {
        this.repo = repo;
        this.orderMapper = orderMapper;
    }

    public OrderDto create(@Valid OrderDto dto) {

        Order mappedOrder = orderMapper.map(dto, Order.class);
        mappedOrder.setStatus(OrderStatus.PROCESSING);
        Order saved = repo.save(mappedOrder);
        return orderMapper.map(saved, OrderDto.class);

    }

    public OrderDto update(@Valid OrderDto dto) {

        Order saved = repo.save(orderMapper.map(dto, Order.class));
        return orderMapper.map(saved, OrderDto.class);

    }

    public void delete(Integer id) {

        repo.removeById(id);

    }

    public List<OrderDto> getOrders() {

        return repo.findAll().stream().map(item -> orderMapper.map(item, OrderDto.class)).collect(Collectors.toList());

    }

    public OrderDto getOrderById(Integer id) {

        return orderMapper.map(repo.findById(id).orElseThrow(InvalidParameterException::new), OrderDto.class);

    }

    public List<OrderDto> getOrderByStatus(OrderStatus status) {

        return repo.findOrdersByStatus(status).stream().map(item -> orderMapper.map(item, OrderDto.class)).collect(Collectors.toList());

    }
}
