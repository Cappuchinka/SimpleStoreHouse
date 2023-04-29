package ru.vsu.cs.simplestorehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.simplestorehouse.dto.OrderProductDto;
import ru.vsu.cs.simplestorehouse.entity.OrderProduct;
import ru.vsu.cs.simplestorehouse.mapper.OrderProductMapper;
import ru.vsu.cs.simplestorehouse.repository.OrderProductRepository;
import ru.vsu.cs.simplestorehouse.utils.exceptions.OrderProductNotFoundException;
import ru.vsu.cs.simplestorehouse.utils.exceptions.StoreNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;
    private final OrderProductMapper orderProductMapper;

    public OrderProductService(OrderProductRepository orderProductRepository, OrderProductMapper orderProductMapper) {
        this.orderProductRepository = orderProductRepository;
        this.orderProductMapper = orderProductMapper;
    }

    public List<OrderProductDto> getOrderProducts() {
        return orderProductRepository.findAll().stream().map(orderProductMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void addOrderProduct(OrderProductDto orderProductDto) {
        orderProductRepository.save(orderProductMapper.toEntity(orderProductDto));
    }

    @Transactional
    public void clear() {
        orderProductRepository.deleteAll();
    }

    public OrderProductDto getOrderProduct(Integer id) {
        return orderProductRepository.findById(id).map(orderProductMapper::toDto).orElseThrow(OrderProductNotFoundException::new);
    }

    @Transactional
    public void updateOrderProduct(Integer id, OrderProductDto orderProductDto) {
        OrderProduct oldOrderProduct = orderProductRepository.findById(id).orElseThrow(OrderProductNotFoundException::new);;
        OrderProduct newOrderProduct = orderProductMapper.toEntity(orderProductDto);
        newOrderProduct.setId(oldOrderProduct.getId());
        orderProductRepository.save(newOrderProduct);
    }

    @Transactional
    public void delete(Integer id) {
        orderProductRepository.deleteById(id);
    }
}
