package ru.vsu.cs.simplestorehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.simplestorehouse.dto.ProductDto;
import ru.vsu.cs.simplestorehouse.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDto toDto(Product product);
    Product toEntity(ProductDto dto);
}
