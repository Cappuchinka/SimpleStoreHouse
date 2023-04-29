package ru.vsu.cs.simplestorehouse.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.simplestorehouse.dto.ProductDto;
import ru.vsu.cs.simplestorehouse.service.ProductService;
import ru.vsu.cs.simplestorehouse.utils.ErrorResponse;
import ru.vsu.cs.simplestorehouse.utils.exceptions.ProductNotFoundException;

import java.time.LocalDate;
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
    public ResponseEntity<HttpStatus> addProduct(@RequestBody @Valid ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        productService.addProduct(productDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<HttpStatus> updateProduct(@PathVariable Integer id, @RequestBody @Valid ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(id, productDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@RequestBody Integer id) {
        productService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<HttpStatus> clear() {
        productService.clear();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(ProductNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
                "Product not found",
                LocalDate.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
