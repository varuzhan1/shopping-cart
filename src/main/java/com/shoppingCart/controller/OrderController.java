package com.shoppingCart.controller;

import com.shoppingCart.persistence.dto.OrderDto;
import com.shoppingCart.persistence.entity.Order;
import com.shoppingCart.persistence.entity.OrderStatus;
import com.shoppingCart.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RestController
@RequestMapping(path = "/orders")
@Tag(name = "order", description = "the order API")
public class OrderController {

    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "find all orders", tags = { "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "orders not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @GetMapping()
    public List<OrderDto> getOrders() {
        log.trace("Load orders");
        return orderService.getOrders();
    }

    @Operation(summary = "find by id", tags = { "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable Integer id) {
        log.trace("Load order by id : {}", id);
        return orderService.getOrderById(id);
    }

    @Operation(summary = "filter orders", tags = { "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid order params"),
            @ApiResponse(responseCode = "404", description = "order not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @GetMapping("/filter")
    public List<OrderDto> getOrderByStatus(@RequestParam OrderStatus status) {
        log.trace("Load order by status : {}", status);
        return orderService.getOrderByStatus(status);
    }

    @Operation(summary = "create order",  tags = { "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @PostMapping()
    public OrderDto create(@RequestBody OrderDto dto) {
        log.trace("Received to create: {}", dto);
        return orderService.create(dto);
    }

    @Operation(summary = "Update an existing order", tags = { "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "order not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception") })
    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public OrderDto update(@RequestBody OrderDto dto) {
        log.trace("Received to update: {}", dto);
        return orderService.update(dto);

    }
    @Operation(summary = "Deletes a order", tags = { "order" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "order not found") })
    @DeleteMapping("{id}")
    void delete(@PathVariable Integer id) {
        log.trace("Received to delete: {}", id);
        orderService.delete(id);
    }


}
