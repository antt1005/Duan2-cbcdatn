package com.example.web.controller;

import com.example.web.model.ChatLieu;
import com.example.web.model.FormDang;
import com.example.web.model.SanPham;
import com.example.web.service.IChatLieuService;
import com.example.web.service.IFormDangService;
import com.example.web.service.ISanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("sanpham")
public class SanPhamController {
    @Autowired
    IFormDangService iFormDangService;

    @Autowired
    ISanPhamService iSanPhamService;

    @Autowired
    IChatLieuService iChatLieuService;

    @Autowired
    HttpServletRequest request;


    Page<SanPham> list;

    Page<FormDang> listForm;

    @GetMapping("/hienthi")
    String hienThi(Model model, @RequestParam(defaultValue = "1") int page
    ) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);


        list = iSanPhamService.findAll(pageable);
        model.addAttribute("list", list);

        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);

        return "sanpham/sanpham";
    }

    @GetMapping("/viewadd")
    String viewadd(Model model) {
        List<FormDang> listForm = iFormDangService.getAll();
        model.addAttribute("listForm", listForm);

        List<ChatLieu> listChat = iChatLieuService.getAll();
        model.addAttribute("listChat", listChat);

        HttpSession session = request.getSession();
        String idForm = (String) session.getAttribute("idform");
        String idchat = (String) session.getAttribute("idchat");
        if (idForm != null) {
            model.addAttribute("idform", idForm);
        }
        if (idchat != null) {
            model.addAttribute("idchat", idchat);
        }
        return "sanpham/add";
    }

    @GetMapping("/viewupdate")
    String viewupdate(Model model, @RequestParam(name = "id", required = false) String id) {
        List<FormDang> listForm = iFormDangService.getAll();
        model.addAttribute("listForm", listForm);

        List<ChatLieu> listChat = iChatLieuService.getAll();
        model.addAttribute("listChat", listChat);

        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(id));
        model.addAttribute("sp", sanPham);

        return "sanpham/update";
    }

    @PostMapping("/add")
    String add(
            @RequestParam(name = "ten") String ten,
            @RequestParam(name = "moTa") String moTa,
            @RequestParam(name = "trangthai") String trangthai,
            @RequestParam(name = "gianhap") String gianhap,
            @RequestParam(name = "giaban") String giaban,
            @RequestParam(name = "form") String form,
            @RequestParam(name = "chat") String chat

    ) {
        long millis = System.currentTimeMillis();
        java.sql.Date ngaytao = new java.sql.Date(millis);
        FormDang formDang = iFormDangService.getOne(UUID.fromString(form));
        ChatLieu chatLieu = iChatLieuService.getOne(UUID.fromString(chat));
        SanPham sanPham = new SanPham(ten, Integer.parseInt(trangthai),
                ngaytao, BigDecimal.valueOf(Double.parseDouble(gianhap)),
                BigDecimal.valueOf(Double.parseDouble(giaban)), moTa, formDang, chatLieu);
        SanPham sanPham1 = iSanPhamService.save(sanPham);
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/sanpham/hienthi";
    }

    @PostMapping("/update")
    String update(
            @RequestParam(name = "ten") String ten,
            @RequestParam(name = "moTa") String moTa,
            @RequestParam(name = "trangthai") String trangthai,
            @RequestParam(name = "gianhap") String gianhap,
            @RequestParam(name = "giaban") String giaban,
            @RequestParam(name = "form") String form,
            @RequestParam(name = "chat") String chat,
            @RequestParam(name = "id") String id

    ) {

        long millis = System.currentTimeMillis();
        java.sql.Date ngaytao = new java.sql.Date(millis);
        FormDang formDang = iFormDangService.getOne(UUID.fromString(form));
        ChatLieu chatLieu = iChatLieuService.getOne(UUID.fromString(chat));
        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(id));

        sanPham.setTen(ten);
        sanPham.setTrangThai(Integer.parseInt(trangthai));
        sanPham.setNgayTao(ngaytao);
        sanPham.setGiaNhap(BigDecimal.valueOf(Double.parseDouble(gianhap)));
        sanPham.setGiaBan(BigDecimal.valueOf(Double.parseDouble(giaban)));
        sanPham.setMoTa(moTa);
        sanPham.setFormDang(formDang);
        sanPham.setChatLieu(chatLieu);
        SanPham sanPham1 = iSanPhamService.save(sanPham);
        return "redirect:/sanpham/hienthi";
    }

    @GetMapping("/layten/{id}")
    @ResponseBody
    public FormDang getForm(@PathVariable(name = "id") String id) {
        FormDang formDang = iFormDangService.getOne(UUID.fromString(id));
        return formDang;
    }

    @GetMapping("/chatlieu/{id}")
    @ResponseBody
    public ChatLieu getChat(@PathVariable(name = "id") String id) {
        ChatLieu chatLieu = iChatLieuService.getOne(UUID.fromString(id));
        return chatLieu;
    }

    @GetMapping("/view-kieu-dang")
    String hienThiKieuDang(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(name = "searchKD", required = false) String searchKD
            , Model model) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        listForm = iFormDangService.findAll(pageable);

        if (searchKD != null) {
            listForm = iFormDangService.getTen("%" + searchKD + "%", pageable);
        }
        model.addAttribute("listForm", listForm);
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "sanpham/themnhanhformdang";
    }

    @GetMapping("/view-chat-lieu")
    String hienThiChatLieu(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(name = "searchCL", required = false) String searchCL
            , Model model) {
        Integer pageSize = 5;
        Page<ChatLieu> chatLieus = iChatLieuService.findAll(page, pageSize);

        if (searchCL != null) {
            chatLieus = iChatLieuService.getTen(searchCL, page, pageSize);
        }

        model.addAttribute("chatLieus", chatLieus);
        model.addAttribute("chatLieu", new ChatLieu());
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * pageSize - 4 : page);

        return "sanpham/themnhanhchatlieu";
    }

    @PostMapping("/addkieudang")
    String add(
            @RequestParam(name = "ten") String ten,
            @RequestParam(name = "trangthai") String trangthai) {
        FormDang formDang = new FormDang(ten, Integer.parseInt(trangthai));
        FormDang formDang1 = iFormDangService.save(formDang);
        //hiển thị
        return "redirect:/sanpham/view-kieu-dang";
    }

    @PostMapping(value = "/addchatlieu")
    public String addChatLieu(@ModelAttribute("chatLieu") ChatLieu chatLieu) {
        iChatLieuService.saveChatLieu(chatLieu);
        return "redirect:/sanpham/view-chat-lieu";
    }

    @GetMapping("/idform")
    String idform(@RequestParam(name = "idform", required = false) String idform) {
        HttpSession session = request.getSession();
        session.setAttribute("idform", idform);
        return "redirect:/sanpham/viewadd";
    }

    @GetMapping("/idchat")
    String idchat(@RequestParam(name = "idchat", required = false) String idchat) {
        HttpSession session = request.getSession();
        session.setAttribute("idchat", idchat);
        return "redirect:/sanpham/viewadd";
    }


}
