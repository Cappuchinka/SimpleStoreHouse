package ru.vsu.cs.simplestorehouse.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.simplestorehouse.dto.StoreDto;
import ru.vsu.cs.simplestorehouse.service.StoreService;
import ru.vsu.cs.simplestorehouse.utils.ErrorResponse;
import ru.vsu.cs.simplestorehouse.utils.exceptions.StoreNotFoundException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/stores")
    public List<StoreDto> getAll() {
        return storeService.getStores();
    }

    @PostMapping("/store/new")
    public ResponseEntity<HttpStatus> addStore(@RequestBody @Valid StoreDto storeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        storeService.addStore(storeDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/store/{id}")
    public StoreDto getStoreById(@PathVariable Integer id) {
        return storeService.getStore(id);
    }

    @PutMapping("/store/update/{id}")
    public ResponseEntity<HttpStatus> updateStore(@PathVariable Integer id, @RequestBody @Valid StoreDto storeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
        storeService.updateStore(id, storeDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<HttpStatus> clear() {
        storeService.clear();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/store/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        storeService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(StoreNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
                "Store not found",
                LocalDate.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
