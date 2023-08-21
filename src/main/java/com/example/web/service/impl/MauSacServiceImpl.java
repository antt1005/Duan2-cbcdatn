package com.example.web.service.impl;

import com.example.web.model.MauSac;
import com.example.web.repository.IMauSacRepository;
import com.example.web.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MauSacServiceImpl implements IMauSacService {


    @Autowired
    IMauSacRepository mauSacRepository;

    @Override
    public Page<MauSac> findAll(Pageable pageable) {
        return mauSacRepository.findAll(pageable);
    }

    @Override
    public void deleteById(UUID id) {
        mauSacRepository.delele(id);
    }

    @Override
    public MauSac save(MauSac mauSac) {
        return mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac getOne(UUID id) {
        return mauSacRepository.getOne(id);
    }

    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public Page<MauSac> findByTenContains(String ten, Pageable pageable) {
        return mauSacRepository.findByTenContains(ten, pageable);
    }


}
