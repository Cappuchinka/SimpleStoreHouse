package ru.vsu.cs.simplestorehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.simplestorehouse.entity.Query;
import ru.vsu.cs.simplestorehouse.repository.QueryRepository;
import ru.vsu.cs.simplestorehouse.utils.exceptions.QueryNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class QueryService {
    private final QueryRepository queryRepository;

    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public List<Query> getQueries() {
        return queryRepository.findAll();
    }

    @Transactional
    public void addQuery(Query query) {
        queryRepository.save(query);
    }

    @Transactional
    public void clear() {
        queryRepository.deleteAll();
    }

    public Query getQuery(Integer id) {
        return queryRepository.findById(id).orElseThrow(QueryNotFoundException::new);
    }

    @Transactional
    public void delete(Integer id) {
        queryRepository.deleteById(id);
    }
}
