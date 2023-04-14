package ru.vsu.cs.simplestorehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.simplestorehouse.dto.OrderDto;
import ru.vsu.cs.simplestorehouse.entity.Order;
import ru.vsu.cs.simplestorehouse.mapper.OrderMapper;
import ru.vsu.cs.simplestorehouse.repository.OrderRepository;
import ru.vsu.cs.simplestorehouse.utils.exceptions.OrderNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public List<OrderDto> getOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void addOrder(OrderDto orderDto) {
        orderRepository.save(orderMapper.toEntity(orderDto));
    }

    @Transactional
    public void clear() {
        orderRepository.deleteAll();
    }

    public OrderDto getOrder(Integer id) {
        return orderRepository.findById(id).map(orderMapper::toDto).orElseThrow(OrderNotFoundException::new);
    }

    @Transactional
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
