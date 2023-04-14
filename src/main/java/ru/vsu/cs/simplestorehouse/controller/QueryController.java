package ru.vsu.cs.simplestorehouse.controller;

import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/query/id")
    public QueryDto getQueryById(@RequestBody Integer id) {
        return queryService.getQuery(id);
    }

    @PostMapping("/query/new")
    public void addQuery(@RequestBody QueryDto queryDto) {
        queryService.addQuery(queryDto);
    }

    @DeleteMapping("/clear")
    public void clear() {
        queryService.clear();
    }

    @DeleteMapping("/query/delete")
    public void delete(@RequestBody Integer id) {
        queryService.delete(id);
    }
}
