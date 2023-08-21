package com.example.web.repository;

import com.example.web.model.FormDang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface IFormDangRepository extends JpaRepository<FormDang, UUID> {
    @Transactional
    @Modifying
    @Query(value = "DELETE from FormDang where id =?1", nativeQuery = true)
    void delele(UUID id);

    @Query(value = "Select * from FormDang where id=?1", nativeQuery = true)
    FormDang getOne(UUID id);

    @Query(value = "Select * from FormDang where ten like ?1", nativeQuery = true)
    Page<FormDang> getTen(String ten, Pageable pageable);


}
