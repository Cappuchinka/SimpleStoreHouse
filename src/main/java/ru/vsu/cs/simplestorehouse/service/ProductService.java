package ru.vsu.cs.simplestorehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.simplestorehouse.dto.OrderProductDto;
import ru.vsu.cs.simplestorehouse.dto.ProductDto;
import ru.vsu.cs.simplestorehouse.entity.Order;
import ru.vsu.cs.simplestorehouse.entity.OrderProduct;
import ru.vsu.cs.simplestorehouse.entity.Product;
import ru.vsu.cs.simplestorehouse.mapper.ProductMapper;
import ru.vsu.cs.simplestorehouse.repository.OrderRepository;
import ru.vsu.cs.simplestorehouse.repository.ProductRepository;
import ru.vsu.cs.simplestorehouse.utils.exceptions.ProductNotFoundException;
import ru.vsu.cs.simplestorehouse.utils.exceptions.StoreNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void addProduct(ProductDto productDto) {
        productRepository.save(productMapper.toEntity(productDto));
    }

    @Transactional
    public void clear() {
        productRepository.deleteAll();
    }

    public ProductDto getProduct(Integer id) {
        return productRepository.findById(id).map(productMapper::toDto).orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public void updateProduct(Integer id, ProductDto productDto) {
        Product oldOrderProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);;
        Product newOrderProduct = productMapper.toEntity(productDto);
        newOrderProduct.setId(oldOrderProduct.getId());
        productRepository.save(newOrderProduct);
    }

    @Transactional
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
