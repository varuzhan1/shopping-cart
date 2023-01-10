package com.shoppingCart.service;


import com.shoppingCart.mapper.OrderMapper;
import com.shoppingCart.persistence.dto.OrderDto;
import com.shoppingCart.persistence.entity.Order;
import com.shoppingCart.persistence.entity.OrderStatus;
import com.shoppingCart.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repo;

    private final OrderMapper orderMapper;

    public OrderDto create(@Valid OrderDto dto) {

        Order mappedOrder = orderMapper.mapToEntity(dto);
        mappedOrder.setStatus(OrderStatus.PROCESSING);
        Order saved = repo.save(mappedOrder);
        return orderMapper.mapToDto(saved);

    }

    public OrderDto update(@Valid OrderDto dto) {

        Order saved = repo.save(orderMapper.mapToEntity(dto));
        return orderMapper.mapToDto(saved);

    }

    public void delete(Integer id) {

        repo.removeById(id);

    }

    public List<OrderDto> getOrders() {

        return repo.findAll().stream().map(orderMapper::mapToDto).collect(Collectors.toList());

    }

    public OrderDto getOrderById(Integer id) {

        return orderMapper.mapToDto(repo.findById(id).orElseThrow(InvalidParameterException::new));

    }

    public List<OrderDto> getOrderByStatus(OrderStatus status) {

        return repo.findOrdersByStatus(status).stream().map(item -> orderMapper.mapToDto(item)).collect(Collectors.toList());

    }
}
