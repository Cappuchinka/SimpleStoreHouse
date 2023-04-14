package ru.vsu.cs.simplestorehouse.controller;

import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.simplestorehouse.dto.OrderDto;
import ru.vsu.cs.simplestorehouse.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<OrderDto> getAll() {
        return orderService.getOrders();
    }

    @PostMapping("/order/id")
    public OrderDto getOrderById(@RequestBody Integer id) {
        return orderService.getOrder(id);
    }

    @PostMapping("order/new")
    public void addOrder(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto);
    }

    @DeleteMapping("/clear")
    public void clear() {
        orderService.clear();
    }

    @DeleteMapping("order/delete")
    public void delete(@RequestBody Integer id) {
        orderService.delete(id);
    }
}
