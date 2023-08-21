package com.example.web.service;

import com.example.web.model.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IChiTietSanPhamService {
    Page<ChiTietSanPham> findAll(Pageable pageable);

    ChiTietSanPham save(ChiTietSanPham sanPham);

    ChiTietSanPham getOne(UUID id);

    Page<ChiTietSanPham> get(UUID id, Pageable pageable);
}
