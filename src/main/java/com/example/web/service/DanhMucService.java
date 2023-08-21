package com.example.web.service;

import com.example.web.model.DanhMuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DanhMucService {
    List<DanhMuc> getAll();

    DanhMuc getOne(String id);

    DanhMuc getOne1(UUID id);

    void add(DanhMuc danhMuc);

    void update(DanhMuc danhMuc);

    void delete(String id);

    Page<DanhMuc> page(Integer pageNo, Integer size);

    Page<DanhMuc> findByTenContains(String ten, Pageable pageable);

    Page<DanhMuc> findAll( Pageable pageable);


}
