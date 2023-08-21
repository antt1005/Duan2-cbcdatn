package com.example.web.service;

import com.example.web.model.FormDang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IFormDangService {
    Page<FormDang> findAll(Pageable pageable);

    void deleteById(UUID id);

    FormDang save(FormDang form);

    FormDang getOne(UUID id);

    List<FormDang> getAll();

    Page<FormDang> getTen(String ten, Pageable pageable);
}
