package ru.vsu.cs.simplestorehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.simplestorehouse.dto.QueryDto;
import ru.vsu.cs.simplestorehouse.dto.QueryPostDto;
import ru.vsu.cs.simplestorehouse.entity.Product;
import ru.vsu.cs.simplestorehouse.entity.Query;
import ru.vsu.cs.simplestorehouse.mapper.ProductMapper;
import ru.vsu.cs.simplestorehouse.mapper.QueryMapper;
import ru.vsu.cs.simplestorehouse.repository.QueryRepository;
import ru.vsu.cs.simplestorehouse.utils.exceptions.QueryNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class QueryService {
    private final QueryRepository queryRepository;
    private final QueryMapper queryMapper;

    private final ProductMapper productMapper;

    public QueryService(QueryRepository queryRepository, QueryMapper queryMapper, ProductMapper productMapper) {
        this.queryRepository = queryRepository;
        this.queryMapper = queryMapper;
        this.productMapper = productMapper;
    }

    public List<QueryDto> getQueries() {
        return queryRepository.findAll().stream().map(queryMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void addQuery(QueryPostDto queryPostDto) {
        Product product = queryRepository.findProductByName(queryPostDto.getProductName());
        QueryDto queryDto = new QueryDto();
        queryDto.setProduct(productMapper.toDto(product));
        queryDto.setProvider(queryPostDto.getProvider());
        queryDto.setCount(queryPostDto.getCount());
        Query query = queryMapper.toEntity(queryDto);
        queryRepository.save(query);
    }

    @Transactional
    public void clear() {
        queryRepository.deleteAll();
    }

    public QueryDto getQuery(Integer id) {
        return queryRepository.findById(id).map(queryMapper::toDto).orElseThrow(QueryNotFoundException::new);
    }

    @Transactional
    public void updateQuery(Integer id, QueryDto queryDto) {
        Query oldOrderProduct = queryRepository.findById(id).orElseThrow(QueryNotFoundException::new);;
        Query newOrderProduct = queryMapper.toEntity(queryDto);
        newOrderProduct.setId(oldOrderProduct.getId());
        queryRepository.save(newOrderProduct);
    }

    @Transactional
    public void delete(Integer id) {
        queryRepository.deleteById(id);
    }
}
