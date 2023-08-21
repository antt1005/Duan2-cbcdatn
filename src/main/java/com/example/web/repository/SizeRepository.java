package com.example.web.repository;

import com.example.web.model.MauSac;
import com.example.web.model.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SizeRepository extends JpaRepository<Size, String> {
    @Query(value = "Select * from Size where id=?1", nativeQuery = true)
    Size getOne1(UUID id);

    Page<Size> findByTenContains(String ten, Pageable pageable);
}
