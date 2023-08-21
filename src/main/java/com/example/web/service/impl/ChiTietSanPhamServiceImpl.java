package com.example.web.service.impl;

import com.example.web.model.ChiTietSanPham;
import com.example.web.repository.IChiTietSanPhamRepository;
import com.example.web.service.IChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChiTietSanPhamServiceImpl implements IChiTietSanPhamService {

    @Autowired
    IChiTietSanPhamRepository iChiTietSanPhamRepository;


    @Override
    public Page<ChiTietSanPham> findAll(Pageable pageable) {
        return iChiTietSanPhamRepository.findAll(pageable);
    }

    @Override
    public ChiTietSanPham save(ChiTietSanPham sanPham) {
        return iChiTietSanPhamRepository.save(sanPham);
    }

    @Override
    public ChiTietSanPham getOne(UUID id) {
        return iChiTietSanPhamRepository.getOne(id);
    }

    @Override
    public Page<ChiTietSanPham> get(UUID id, Pageable pageable) {
        return iChiTietSanPhamRepository.get(id, pageable);
    }
}
