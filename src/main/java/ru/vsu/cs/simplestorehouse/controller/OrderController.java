package ru.vsu.cs.simplestorehouse.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.simplestorehouse.dto.OrderDto;
import ru.vsu.cs.simplestorehouse.service.OrderService;
import ru.vsu.cs.simplestorehouse.utils.ErrorResponse;
import ru.vsu.cs.simplestorehouse.utils.exceptions.OrderNotFoundException;

import java.time.LocalDate;
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

    @PostMapping("/order/{id}")
    public OrderDto getOrderById(@RequestBody Integer id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/order/new")
    public ResponseEntity<HttpStatus> addOrder(@RequestBody @Valid OrderDto orderDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        orderService.addOrder(orderDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/order/update/{id}")
    public ResponseEntity<HttpStatus> updateOrder(@PathVariable Integer id, @RequestBody @Valid OrderDto orderDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        orderService.updateOrder(id, orderDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<HttpStatus> clear() {
        orderService.clear();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@RequestBody Integer id) {
        orderService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(OrderNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
                "Order not found",
                LocalDate.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
