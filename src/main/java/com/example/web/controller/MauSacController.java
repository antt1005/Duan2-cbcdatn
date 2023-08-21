package com.example.web.controller;

import com.example.web.model.MauSac;
import com.example.web.service.IMauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("mausac")
public class MauSacController {
    @Autowired
    IMauSacService iMauSacService;
    Page<MauSac> list;

    @GetMapping("/hienthi")
    String hienThi(@RequestParam(defaultValue = "1") int page, Model model) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        list = iMauSacService.findAll(pageable);
        model.addAttribute("list", list);
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        return "qlimausac/mausac";
    }

    @GetMapping("/delete/{id}")
    String delete(@PathVariable(name = "id") String id) {
        iMauSacService.deleteById(UUID.fromString(id));
        return "redirect:/mausac/hienthi";
    }

    @PostMapping("/add")
    String add(@RequestParam(name = "ten") String ten,
               @RequestParam(name = "trangthai") String trangthai, Model model) {
        MauSac mauSac = new MauSac(ten, Integer.parseInt(trangthai));
        MauSac mauSac1 = iMauSacService.save(mauSac);
        //hiển thị
        return "redirect:/mausac/hienthi";
    }

    @PostMapping("/update")
    String update(@RequestParam(name = "id") String id,
                  @RequestParam(name = "ten") String ten,
                  @RequestParam(name = "trangthai") String trangthai
    ) {
        MauSac mauSac = iMauSacService.getOne(UUID.fromString(id));
        mauSac.setTen(ten);
        mauSac.setTrangThai(Integer.parseInt(trangthai));
        MauSac mauSac1 = iMauSacService.save(mauSac);

        return "redirect:/mausac/hienthi";
    }
}
