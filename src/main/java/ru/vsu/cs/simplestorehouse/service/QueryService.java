package ru.vsu.cs.simplestorehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.simplestorehouse.dto.QueryDto;
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

    public QueryService(QueryRepository queryRepository, QueryMapper queryMapper) {
        this.queryRepository = queryRepository;
        this.queryMapper = queryMapper;
    }

    public List<QueryDto> getQueries() {
        return queryRepository.findAll().stream().map(queryMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void addQuery(QueryDto queryDto) {
        queryRepository.save(queryMapper.toEntity(queryDto));
    }

    @Transactional
    public void clear() {
        queryRepository.deleteAll();
    }

    public QueryDto getQuery(Integer id) {
        return queryRepository.findById(id).map(queryMapper::toDto).orElseThrow(QueryNotFoundException::new);
    }

    @Transactional
    public void delete(Integer id) {
        queryRepository.deleteById(id);
    }
}
