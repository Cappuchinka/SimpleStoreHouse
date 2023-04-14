package ru.vsu.cs.simplestorehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.simplestorehouse.dto.OrderProductDto;
import ru.vsu.cs.simplestorehouse.entity.OrderProduct;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    OrderProductMapper INSTANCE = Mappers.getMapper(OrderProductMapper.class);
    OrderProductDto toDto(OrderProduct orderProduct);
    OrderProduct toEntity(OrderProductDto dto);
}
