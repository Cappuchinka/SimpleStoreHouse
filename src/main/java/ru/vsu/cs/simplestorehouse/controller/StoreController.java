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

    @PostMapping("/store/{id}")
    public StoreDto getStoreById(@PathVariable Integer id) {
        return storeService.getStore(id);
    }

    @PutMapping("/store/update/{id}")
    public void updateStore(@PathVariable Integer id, @RequestBody StoreDto storeDto) {
        storeService.updateStore(id, storeDto);
    }

    @DeleteMapping("/clear")
    public void clear() {
        storeService.clear();
    }

    @DeleteMapping("/store/delete/{id}")
    public void delete(@PathVariable Integer id) {
        storeService.delete(id);
    }
}
