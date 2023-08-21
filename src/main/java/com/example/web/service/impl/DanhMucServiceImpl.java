package com.example.web.service.impl;

import com.example.web.model.DanhMuc;
import com.example.web.repository.DanhMucRepository;
import com.example.web.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DanhMucServiceImpl implements DanhMucService {
    @Autowired
    private DanhMucRepository danhMucRepository;

    @Override
    public List<DanhMuc> getAll() {
        return danhMucRepository.findAll();
    }

    @Override
    public DanhMuc getOne(String id) {
        return danhMucRepository.getReferenceById(id);
    }

    @Override
    public DanhMuc getOne1(UUID id) {
        return danhMucRepository.getOne1(id);
    }

    @Override
    public void add(DanhMuc danhMuc) {
        danhMucRepository.save(danhMuc);
    }

    @Override
    public void update(DanhMuc danhMuc) {
        danhMucRepository.save(danhMuc);
    }

    @Override
    public void delete(String id) {
        danhMucRepository.deleteById(id);
    }

    @Override
    public Page<DanhMuc> page(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return danhMucRepository.findAll(pageable);
    }

    @Override
    public Page<DanhMuc> findByTenContains(String ten, Pageable pageable) {
        return danhMucRepository.findByTenContains(ten,pageable);
    }

    @Override
    public Page<DanhMuc> findAll(Pageable pageable) {
        return danhMucRepository.findAll(pageable);
    }
}
