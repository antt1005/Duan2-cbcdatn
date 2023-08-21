package com.example.web.repository;

import com.example.web.model.ChiTietSanPham;
import com.example.web.model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface IChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {
    @Query(value = "Select * from ChiTietSanPham where id=?1", nativeQuery = true)
    ChiTietSanPham getOne(UUID id);

    @Query(value = "Select * from ChiTietSanPham where IdSanPham=?1", nativeQuery = true)
    Page<ChiTietSanPham> get(UUID id, Pageable pageable);
}
