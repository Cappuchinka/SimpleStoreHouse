package ru.vsu.cs.simplestorehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.simplestorehouse.dto.OrderDto;
import ru.vsu.cs.simplestorehouse.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    OrderDto toDto(Order order);
    Order toEntity(OrderDto dto);
}
