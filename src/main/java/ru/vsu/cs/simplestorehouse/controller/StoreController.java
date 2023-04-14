package ru.vsu.cs.simplestorehouse.controller;

import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.simplestorehouse.dto.StoreDto;
import ru.vsu.cs.simplestorehouse.service.StoreService;

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
    public void addStore(@RequestBody StoreDto storeDto) {
        storeService.addStore(storeDto);
    }

    @PostMapping("/store/id")
    public StoreDto getStoreById(@RequestBody Integer id) {
        return storeService.getStore(id);
    }

    @DeleteMapping("/clear")
    public void clear() {
        storeService.clear();
    }

    @DeleteMapping("/store/delete")
    public void delete(@RequestBody Integer id) {
        storeService.delete(id);
    }
}
