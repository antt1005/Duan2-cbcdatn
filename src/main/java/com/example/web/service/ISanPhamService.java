package com.example.web.service;


import com.example.web.model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ISanPhamService {
    Page<SanPham> findAll(Pageable pageable);

    SanPham save(SanPham sanPham);

    SanPham getOne(UUID id);



}
