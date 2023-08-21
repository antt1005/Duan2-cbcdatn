package com.example.web.service.impl;

import com.example.web.model.SanPham;
import com.example.web.repository.ISanPhamRepository;
import com.example.web.service.ISanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SanPhamServiceImpl implements ISanPhamService {
    @Autowired
    ISanPhamRepository iSanPhamRepository;

    @Override
    public Page<SanPham> findAll(Pageable pageable) {
        return iSanPhamRepository.findAll(pageable);
    }

    @Override
    public SanPham save(SanPham sanPham) {
        return iSanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham getOne(UUID id) {
        return iSanPhamRepository.getOne(id);
    }

}
