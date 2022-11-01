package com.shoppingCart.controller;

import com.shoppingCart.persistence.dto.OrderDto;
import com.shoppingCart.persistence.model.OrderStatus;
import com.shoppingCart.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public List<OrderDto> getOrders() {
        log.trace("Load orders");
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Integer id) {
        log.trace("Load order by id : {}", id);
        return orderService.getOrderById(id);
    }

    @GetMapping("/filter")
    public List<OrderDto> getOrderByStatus(@RequestParam OrderStatus status) {
        log.trace("Load order by status : {}", status);
        return orderService.getOrderByStatus(status);
    }

    @PostMapping()
    public OrderDto create(@RequestBody OrderDto dto) {
        log.trace("Received to create: {}", dto);
        return orderService.create(dto);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public OrderDto update(@RequestBody OrderDto dto) {
        log.trace("Received to update: {}", dto);
        return orderService.update(dto);

    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Integer id) {
        log.trace("Received to delete: {}", id);
        orderService.delete(id);
    }


}
