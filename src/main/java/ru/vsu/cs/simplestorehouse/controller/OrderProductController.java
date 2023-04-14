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
    public void saveNew(@RequestBody OrderProductDto orderProductDto) {
        orderProductService.addOrderProduct(orderProductDto);
    }

    @DeleteMapping("/clear")
    public void clear() {
        orderProductService.clear();
    }

//    @DeleteMapping("order_product/delete")
//    public void delete(Integer id) {
//        orderProductService.delete(id);
//    }
}
