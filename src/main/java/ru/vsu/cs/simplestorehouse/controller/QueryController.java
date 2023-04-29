package ru.vsu.cs.simplestorehouse.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.simplestorehouse.dto.QueryDto;
import ru.vsu.cs.simplestorehouse.service.QueryService;
import ru.vsu.cs.simplestorehouse.utils.ErrorResponse;
import ru.vsu.cs.simplestorehouse.utils.exceptions.QueryNotFoundException;

import java.time.LocalDate;
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
    public ResponseEntity<HttpStatus> addQuery(@RequestBody @Valid QueryDto queryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        queryService.addQuery(queryDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/query/update/{id}")
    public ResponseEntity<HttpStatus> updateQuery(@PathVariable Integer id, @RequestBody @Valid QueryDto queryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        queryService.updateQuery(id, queryDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<HttpStatus> clear() {
        queryService.clear();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/query/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        queryService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(QueryNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
                "Query not found",
                LocalDate.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
