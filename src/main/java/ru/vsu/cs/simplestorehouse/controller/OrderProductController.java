package ru.vsu.cs.simplestorehouse.controller;

import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.simplestorehouse.dto.OrderProductDto;
import ru.vsu.cs.simplestorehouse.service.OrderProductService;

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
    public void addOrderProduct(@RequestBody OrderProductDto orderProductDto) {
        orderProductService.addOrderProduct(orderProductDto);
    }

    @PostMapping("/order_product/{id}")
    public OrderProductDto getOrderProductById(@RequestBody Integer id) {
        return orderProductService.getOrderProduct(id);
    }

    @PutMapping("/order_product/update/{id}")
    public void updateOrderProduct(@PathVariable Integer id, @RequestBody OrderProductDto orderProductDto) {
        orderProductService.updateOrderProduct(id, orderProductDto);
    }

    @DeleteMapping("/clear")
    public void clear() {
        orderProductService.clear();
    }

    @DeleteMapping("order_product/delete/{id}")
    public void delete(@RequestBody Integer id) {
        orderProductService.delete(id);
    }
}
