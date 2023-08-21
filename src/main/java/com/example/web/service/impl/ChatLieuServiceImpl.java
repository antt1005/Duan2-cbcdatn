package com.example.web.service.impl;

import com.example.web.model.ChatLieu;
import com.example.web.repository.IChatLieuRepository;
import com.example.web.service.IChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatLieuServiceImpl implements IChatLieuService {

    @Autowired
    private IChatLieuRepository chatLieuRepository;

    @Override
    public Page<ChatLieu> findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<ChatLieu> chatLieus = chatLieuRepository.findAll(pageable);
        return chatLieus;
    }

    @Override
    public void saveChatLieu(ChatLieu chatLieu) {
        chatLieuRepository.save(chatLieu);
    }

    @Override
    public ChatLieu getChatLieu(UUID id) {
        Optional<ChatLieu> chatLieu = chatLieuRepository.findById(id);
        if (chatLieu.isPresent()) {
            return chatLieu.get();
        }
        return null;
    }

    @Override
    public ChatLieu getOne(UUID id) {
        return chatLieuRepository.getOne(id);
    }

    @Override
    public List<ChatLieu> getAll() {
        return chatLieuRepository.findAll();
    }

    @Override
    public Page<ChatLieu> getTen(String ten, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<ChatLieu> chatLieus = chatLieuRepository.findByTenContains(ten,pageable);
        return chatLieus;
    }


}
