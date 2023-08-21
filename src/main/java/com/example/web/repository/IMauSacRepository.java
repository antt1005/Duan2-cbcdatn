package com.example.web.repository;

import com.example.web.model.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface IMauSacRepository extends JpaRepository<MauSac, UUID> {

    @Transactional
    @Modifying
    @Query(value = "DELETE from MauSac where id =?1", nativeQuery = true)
    void delele(UUID id);

    @Query(value = "Select * from MauSac where id=?1", nativeQuery = true)
    MauSac getOne(UUID id);


    Page<MauSac> findByTenContains(String ten, Pageable pageable);
}
