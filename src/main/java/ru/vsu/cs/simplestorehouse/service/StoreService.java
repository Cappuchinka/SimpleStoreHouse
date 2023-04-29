package ru.vsu.cs.simplestorehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.simplestorehouse.dto.StoreDto;
import ru.vsu.cs.simplestorehouse.entity.Store;
import ru.vsu.cs.simplestorehouse.mapper.StoreMapper;
import ru.vsu.cs.simplestorehouse.repository.StoreRepository;
import ru.vsu.cs.simplestorehouse.utils.exceptions.StoreNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StoreService {
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    public List<StoreDto> getStores() {
        return storeRepository.findAll().stream().map(storeMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void addStore(StoreDto storeDto) {
        Store store = storeMapper.toEntity(storeDto);
        storeRepository.save(store);
    }

    @Transactional
    public void clear() {
        storeRepository.deleteAll();
    }

    public StoreDto getStore(Integer id) {
        return storeRepository.findById(id).map(storeMapper::toDto).orElseThrow(StoreNotFoundException::new);
    }

    @Transactional
    public void updateStore(Integer id, StoreDto storeDto) {
        Store oldStore = storeRepository.findById(id).orElseThrow(StoreNotFoundException::new);
        Store newStore = storeMapper.toEntity(storeDto);
        newStore.setId(oldStore.getId());
        storeRepository.save(newStore);
    }

    @Transactional
    public void delete(Integer id) {
        storeRepository.deleteById(id);
    }
}
