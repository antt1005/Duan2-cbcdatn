package com.example.web.controller;

import com.example.web.model.DanhMuc;
import com.example.web.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/danh-muc")
public class DanhMucController {
    @Autowired
    private DanhMucService danhMucService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "p", defaultValue = "0") Integer pageNo){

        model.addAttribute("danhMuc", new DanhMuc());
        model.addAttribute("list", danhMucService.page(pageNo, 5).getContent());
        model.addAttribute("totalPage", danhMucService.page(pageNo, 5).getTotalPages());
        model.addAttribute("currentPage", pageNo);
        return "danhmuc/danhmuc";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(@PathVariable("id") String id, Model model, @ModelAttribute("danhMuc") DanhMuc danhMuc){
        danhMuc = danhMucService.getOne(id);
        model.addAttribute("danhMuc", danhMuc);
        return "danhmuc/update-danhmuc";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id){
        danhMucService.delete(id);
        return "redirect:/danh-muc/hien-thi";
    }

    @GetMapping("/hien-thi/{p}")
    public String page(@PathVariable("p") Integer p, Model model){
        model.addAttribute("danhMuc", new DanhMuc());
        model.addAttribute("list", danhMucService.page(p, 5).getContent());
        model.addAttribute("totalPage", danhMucService.page(p, 5).getTotalPages());
        model.addAttribute("currentPage", p);
        return "danhmuc/danhmuc";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("danhMuc") DanhMuc danhMuc, BindingResult result){
        if(result.hasErrors()){
            return "danhmuc/danhmuc";
        }else{
            UUID uuid = UUID.randomUUID();
            danhMuc.setId(String.valueOf(uuid));
            danhMucService.add(danhMuc);
            return "redirect:/danh-muc/hien-thi";
        }
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") String id, @ModelAttribute("danhMuc") DanhMuc danhMuc, BindingResult result){
        if(result.hasErrors()){
            return "danhmuc/update-danhmuc";
        }else{
            danhMuc.setId(id);
            danhMucService.update(danhMuc);
            return "redirect:/danh-muc/hien-thi";
        }
    }
}
