package com.example.web.service;
import com.example.web.model.ChatLieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IChatLieuService {

    Page<ChatLieu> findAll(Integer page , Integer pageSize);

    void saveChatLieu(ChatLieu chatLieu);

    ChatLieu getChatLieu(UUID id);

    ChatLieu getOne(UUID id);

    List<ChatLieu> getAll();

    Page<ChatLieu> getTen(String ten,Integer page, Integer pageSize);
}
