package com.example.web.repository;
import com.example.web.model.ChatLieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface IChatLieuRepository extends JpaRepository<ChatLieu , UUID> {

    @Override
    Page<ChatLieu> findAll(Pageable pageable);

    @Override
    <S extends ChatLieu> S save(S entity);

    @Override
    Optional<ChatLieu> findById(UUID uuid);

    @Query(value = "Select * from ChatLieu where id=?1",nativeQuery = true)
    ChatLieu getOne(UUID id);

//    @Query(value = "Select * from ChatLieu where ten like ?1",nativeQuery = true)
    Page<ChatLieu> findByTenContains(String ten,Pageable pageable);
}
