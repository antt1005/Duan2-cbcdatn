package com.example.web.repository;

import com.example.web.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ISanPhamRepository extends JpaRepository<SanPham, UUID> {
    @Query(value = "Select * from SanPham where id=?1", nativeQuery = true)
    SanPham getOne(UUID id);
}
