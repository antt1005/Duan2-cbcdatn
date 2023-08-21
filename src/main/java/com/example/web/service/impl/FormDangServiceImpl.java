package com.example.web.service.impl;

import com.example.web.model.FormDang;
import com.example.web.repository.IFormDangRepository;
import com.example.web.service.IFormDangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FormDangServiceImpl implements IFormDangService {
    @Autowired
    IFormDangRepository iFormDangRepository;

    @Override
    public Page<FormDang> findAll(Pageable pageable) {
        return iFormDangRepository.findAll(pageable);
    }

    @Override
    public void deleteById(UUID id) {
        iFormDangRepository.delele(id);
    }

    @Override
    public FormDang save(FormDang form) {
        return iFormDangRepository.save(form);
    }

    @Override
    public FormDang getOne(UUID id) {
        return iFormDangRepository.getOne(id);
    }

    @Override
    public List<FormDang> getAll() {
        return iFormDangRepository.findAll();
    }

    @Override
    public Page<FormDang> getTen(String ten, Pageable pageable) {
        return iFormDangRepository.getTen(ten,pageable);
    }
}
