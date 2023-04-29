package ru.vsu.cs.simplestorehouse.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.simplestorehouse.dto.OrderProductDto;
import ru.vsu.cs.simplestorehouse.service.OrderProductService;
import ru.vsu.cs.simplestorehouse.utils.ErrorResponse;
import ru.vsu.cs.simplestorehouse.utils.exceptions.OrderProductNotFoundException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order_product")
public class OrderProductController {
    private final OrderProductService orderProductService;

    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @GetMapping("/order_products")
    public List<OrderProductDto> getAll() {
        return orderProductService.getOrderProducts();
    }

    @PostMapping("order_product/new")
    public ResponseEntity<HttpStatus> addOrderProduct(@RequestBody @Valid OrderProductDto orderProductDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        orderProductService.addOrderProduct(orderProductDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/order_product/{id}")
    public OrderProductDto getOrderProductById(@RequestBody Integer id) {
        return orderProductService.getOrderProduct(id);
    }

    @PutMapping("/order_product/update/{id}")
    public ResponseEntity<HttpStatus> updateOrderProduct(@PathVariable Integer id, @RequestBody @Valid OrderProductDto orderProductDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        orderProductService.updateOrderProduct(id, orderProductDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<HttpStatus> clear() {
        orderProductService.clear();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("order_product/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@RequestBody Integer id) {
        orderProductService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(OrderProductNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
                "OrderProduct not found",
                LocalDate.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
