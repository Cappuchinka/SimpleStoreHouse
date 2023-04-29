package ru.vsu.cs.simplestorehouse.controller;

import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.simplestorehouse.dto.OrderProductDto;
import ru.vsu.cs.simplestorehouse.dto.QueryDto;
import ru.vsu.cs.simplestorehouse.service.QueryService;

import java.util.List;

@RestController
@RequestMapping("/query")
public class QueryController {
    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/queries")
    public List<QueryDto> getAll() {
        return queryService.getQueries();
    }

    @PostMapping("/query/{id}")
    public QueryDto getQueryById(@PathVariable Integer id) {
        return queryService.getQuery(id);
    }

    @PostMapping("/query/new")
    public void addQuery(@RequestBody QueryDto queryDto) {
        queryService.addQuery(queryDto);
    }

    @PutMapping("/query/update/{id}")
    public void updateQuery(@PathVariable Integer id, @RequestBody QueryDto queryDto) {
        queryService.updateQuery(id, queryDto);
    }

    @DeleteMapping("/clear")
    public void clear() {
        queryService.clear();
    }

    @DeleteMapping("/query/delete/{id}")
    public void delete(@PathVariable Integer id) {
        queryService.delete(id);
    }
}
