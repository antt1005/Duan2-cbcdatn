package com.example.web.controller;
import com.example.web.model.Size;
import com.example.web.service.impl.SizeServiceImpl;
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
@RequestMapping("/size/")
public class SizeController {

    @Autowired
    private SizeServiceImpl sizeService;

    @GetMapping("hien-thi")
    public String hienThi(Model model,@RequestParam(value = "p",defaultValue = "0")Integer pageNo){
        model.addAttribute("lst",sizeService.pagination(pageNo,5).getContent());
        model.addAttribute("size",new Size());
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalsPage",sizeService.pagination(pageNo,5).getTotalPages());
        return "/kichco/kichco";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("size")Size size, BindingResult result) {
        if (result.hasErrors()){
            return "/kichco/kichco";
        }else {
            UUID id = UUID.randomUUID();
            size.setId(String.valueOf(id));
            sizeService.add(size);
            return "redirect:/size/hien-thi";
        }
    }

    @PostMapping("update/{id}")
    public String update(@ModelAttribute("size")Size size, @PathVariable("id")String id) {
            size.setId(id);
            sizeService.update(size);
            return "redirect:/size/hien-thi";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id")String id){
        sizeService.delete(id);
        return "redirect:/size/hien-thi";
    }
    @GetMapping("view-update/{id}")
    public String viewUpdate(@PathVariable("id")String id,Model model,@ModelAttribute("size")Size size){
        size=sizeService.getOne(id);
        model.addAttribute("size",size);
        return "/kichco/update-size";
    }
    @GetMapping("hien-thi/{p}")
    public String phanTrang(@PathVariable("p")Integer p,Model model){
        model.addAttribute("size",new Size());
        model.addAttribute("currentPage",p);
        model.addAttribute("totalsPage",sizeService.pagination(p,5).getTotalPages());
        model.addAttribute("lst",sizeService.pagination(p,5).getContent());
        return "/kichco/kichco";
    }
}
