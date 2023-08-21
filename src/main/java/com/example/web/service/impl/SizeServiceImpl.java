package com.example.web.service.impl;

import com.example.web.model.Size;
import com.example.web.repository.SizeRepository;
import com.example.web.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> getAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size getOne(String id) {
        return sizeRepository.getReferenceById(id);
    }

    @Override
    public Size getOne1(UUID id) {
        return sizeRepository.getOne1(id);
    }

    @Override
    public void add(Size size) {
        sizeRepository.save(size);
    }

    @Override
    public void update(Size size) {
        sizeRepository.save(size);
    }

    @Override
    public void delete(String id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public Page<Size> pagination(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return sizeRepository.findAll(pageable);
    }

    @Override
    public Page<Size> findAll(Pageable pageable) {
        return sizeRepository.findAll(pageable);
    }

    @Override
    public Page<Size> findByTenContains(String ten, Pageable pageable) {
        return sizeRepository.findByTenContains(ten,pageable);
    }


}
