package com.example.web.service;

import com.example.web.model.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IMauSacService {

    Page<MauSac> findAll(Pageable pageable);

    void deleteById(UUID id);

    MauSac save(MauSac mauSac);

    MauSac getOne(UUID id);

    List<MauSac> getAll();

    Page<MauSac> findByTenContains(String ten, Pageable pageable);

}
