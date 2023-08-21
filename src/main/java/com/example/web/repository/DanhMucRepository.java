package com.example.web.repository;

import com.example.web.model.DanhMuc;
import com.example.web.model.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface DanhMucRepository extends JpaRepository<DanhMuc, String> {
    @Query(value = "Select * from DanhMuc where id=?1", nativeQuery = true)
    DanhMuc getOne1(UUID id);

    Page<DanhMuc> findByTenContains(String ten,Pageable pageable);
}
