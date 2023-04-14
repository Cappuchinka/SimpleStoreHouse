package ru.vsu.cs.simplestorehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.simplestorehouse.entity.Order;
import ru.vsu.cs.simplestorehouse.repository.OrderRepository;
import ru.vsu.cs.simplestorehouse.utils.exceptions.OrderNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrderProducts() {
        return orderRepository.findAll();
    }

    @Transactional
    public void addOrderProduct(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public void clear() {
        orderRepository.deleteAll();
    }

    public Order getOrderProduct(Integer id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    @Transactional
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
