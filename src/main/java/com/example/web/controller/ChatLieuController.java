package com.example.web.controller;
import com.example.web.model.ChatLieu;
import com.example.web.service.IChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.UUID;

@Controller
@RequestMapping(value = "/chat-lieu")
public class ChatLieuController {

    @Autowired
    private IChatLieuService chatLieuService;

    @GetMapping(value = "/hien-thi")
    public String getAll(@RequestParam(defaultValue = "1") Integer page, Model model) {
        Integer pageSize = 5;
        Page<ChatLieu> chatLieus = chatLieuService.findAll(page, pageSize);
        model.addAttribute("chatLieus", chatLieus);
        model.addAttribute("chatLieu", new ChatLieu());
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * pageSize - 4 : page);
        return "chatlieu/chat-lieu";
    }

    @PostMapping(value = "/add")
    public String addChatLieu(@ModelAttribute("chatLieu") ChatLieu chatLieu) {
        chatLieuService.saveChatLieu(chatLieu);
        return "redirect:/chat-lieu/hien-thi";
    }

    @GetMapping(value = "/view-update/{id}")
    public String viewUpdate(@PathVariable UUID id, Model model) {
        ChatLieu chatLieu = chatLieuService.getChatLieu(id);
        model.addAttribute("chatLieu", chatLieu);
        model.addAttribute("chatLieus", new ChatLieu());
        return "chatlieu/view-update";
    }

    @PostMapping(value = "/update/{id}")
    public String updateChatLieu(@ModelAttribute("chatLieus") ChatLieu chatLieu  , @PathVariable UUID id) {
       ChatLieu chatLieuUpdate = chatLieuService.getChatLieu(id);
       chatLieuUpdate.setTen(chatLieu.getTen());
       chatLieuUpdate.setTrangThai(chatLieu.getTrangThai());
        chatLieuService.saveChatLieu(chatLieuUpdate);
        return "redirect:/chat-lieu/hien-thi";
    }
}
