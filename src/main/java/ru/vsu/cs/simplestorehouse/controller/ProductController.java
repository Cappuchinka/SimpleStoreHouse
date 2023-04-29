package ru.vsu.cs.simplestorehouse.controller;

import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.simplestorehouse.dto.OrderProductDto;
import ru.vsu.cs.simplestorehouse.dto.ProductDto;
import ru.vsu.cs.simplestorehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDto> getAll() {
        return productService.getProducts();
    }

    @PostMapping("/product/{id}")
    public ProductDto getProductById(@RequestBody Integer id) {
        return productService.getProduct(id);
    }

    @PostMapping("/product/new")
    public void addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
    }

    @PutMapping("/product/update/{id}")
    public void updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/product/delete/{id}")
    public void delete(@RequestBody Integer id) {
        productService.delete(id);
    }

    @DeleteMapping("/clear")
    public void clear() {
        productService.clear();
    }
}
