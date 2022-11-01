package com.shoppingCart.service;


import com.shoppingCart.mapper.OrderMapper;
import com.shoppingCart.persistence.dto.OrderDto;
import com.shoppingCart.persistence.model.Order;
import com.shoppingCart.persistence.model.OrderStatus;
import com.shoppingCart.persistence.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private OrderMapper orderMapper;


    public OrderDto create(@Valid OrderDto dto) {

        Order mappedOrder = orderMapper.convertDtoToEntity(dto);
        mappedOrder.setStatus(OrderStatus.PROCESSING);
        Order saved = repo.save(mappedOrder);
        return orderMapper.convertEntityToDto(saved);

    }

    public OrderDto update(@Valid OrderDto dto) {

        Order saved = repo.save(orderMapper.convertDtoToEntity(dto));
        return orderMapper.convertEntityToDto(saved);

    }

    public void delete(Integer id) {

        repo.removeById(id);

    }

    public List<OrderDto> getOrders() {

        return repo.findAll().stream().map(orderMapper::convertEntityToDto).collect(Collectors.toList());

    }

    public OrderDto getOrderById(Integer id) {

        return orderMapper.convertEntityToDto(repo.findById(id).orElseThrow(InvalidParameterException::new));

    }

    public List<OrderDto> getOrderByStatus(OrderStatus status) {
        return repo.findOrdersByStatus(status).stream().map(orderMapper::convertEntityToDto).collect(Collectors.toList());

    }
}
