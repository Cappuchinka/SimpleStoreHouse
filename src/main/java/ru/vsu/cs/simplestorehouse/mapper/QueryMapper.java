package ru.vsu.cs.simplestorehouse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.simplestorehouse.dto.QueryDto;
import ru.vsu.cs.simplestorehouse.entity.Query;

@Mapper(componentModel = "spring")
public interface QueryMapper {
    QueryMapper INSTANCE = Mappers.getMapper(QueryMapper.class);
    QueryDto toDto(Query query);
    Query toEntity(QueryDto dto);
}
