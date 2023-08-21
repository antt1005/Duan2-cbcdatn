package com.example.web.controller;

import com.example.web.model.*;
import com.example.web.service.DanhMucService;
import com.example.web.service.IChiTietSanPhamService;
import com.example.web.service.IMauSacService;
import com.example.web.service.ISanPhamService;
import com.example.web.service.impl.SizeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("chi-tiet-san-pham")
public class ChiTietSanPhamController {
    Page<ChiTietSanPham> list;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private IChiTietSanPhamService iChiTietSanPhamService;

    @Autowired
    private ISanPhamService iSanPhamService;

    @Autowired
    private IMauSacService iMauSacService;

    @Autowired
    private SizeServiceImpl sizeService;

    @Autowired
    private DanhMucService danhMucService;

    @GetMapping("/hien-thi")
    String hienThi(@RequestParam(defaultValue = "1") Integer page, Model model,
                   @RequestParam(name = "idsp", required = false) String idsp) {
        if (page < 1) page = 1;
        List<MauSac> listMau = iMauSacService.getAll();
        model.addAttribute("listMau", listMau);

        List<Size> listSize = sizeService.getAll();
        model.addAttribute("listSize", listSize);

        List<DanhMuc> listDanh = danhMucService.getAll();
        model.addAttribute("listDanh", listDanh);
        Pageable pageable = PageRequest.of(page - 1, 5);
        if (idsp != null) {// khi ấn thêm chi tiết
            model.addAttribute("idsp", idsp);
            list = iChiTietSanPhamService.get(UUID.fromString(idsp), pageable);
        }

        if (idsp == null) {// khi ấn update hoặc add xong
            HttpSession session = request.getSession();
            String idSP = (String) session.getAttribute("idSP");
            model.addAttribute("idsp", idSP);
            list = iChiTietSanPhamService.get(UUID.fromString(idSP), pageable);
        }
        HttpSession session = request.getSession();
        String idmau = (String) session.getAttribute("idmau");
        if (idmau != null) {
            model.addAttribute("idmau", idmau);
        }
        String idsize = (String) session.getAttribute("idsize");
        if (idsize != null) {
            model.addAttribute("idsize", idsize);
        }

        String iddanhmuc = (String) session.getAttribute("iddanhmuc");
        if (iddanhmuc != null) {
            model.addAttribute("iddanhmuc", iddanhmuc);
        }

        model.addAttribute("list", list);
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);


        return "chitietsanpham/chitiet";
    }

    @GetMapping("/lay-mau/{id}")
    @ResponseBody
    public MauSac layMau(@PathVariable(name = "id") String id) {
        MauSac mauSac = iMauSacService.getOne(UUID.fromString(id));
        return mauSac;
    }

    @GetMapping("/lay-size/{id}")
    @ResponseBody
    public Size laysize(@PathVariable(name = "id") String id) {
        Size siZe = sizeService.getOne1(UUID.fromString(id));
        return siZe;

    }

    @GetMapping("/lay-danh-muc/{id}")
    @ResponseBody
    public DanhMuc layDanhMuc(@PathVariable(name = "id") String id) {
        DanhMuc danhMuc = danhMucService.getOne1(UUID.fromString(id));
        return danhMuc;
    }

    @PostMapping("/add")
    String add(
            @RequestParam(name = "idsp") String idsp,
            @RequestParam(name = "soluong") String soluong,
            @RequestParam(name = "trangthai") String trangthai,
            @RequestParam(name = "mau") String mau,
            @RequestParam(name = "size") String size,
            @RequestParam(name = "danhmuc") String danhmuc

    ) {

        MauSac mauSac = iMauSacService.getOne(UUID.fromString(mau));
        Size siZe = sizeService.getOne1(UUID.fromString(size));
        DanhMuc danhMuc = danhMucService.getOne1(UUID.fromString(danhmuc));
        SanPham sanPham = iSanPhamService.getOne(UUID.fromString(idsp));

        ChiTietSanPham chiTietSanPham = new ChiTietSanPham(sanPham, Integer.parseInt(soluong), Integer.parseInt(trangthai), mauSac, siZe, danhMuc);
        ChiTietSanPham chiTietSanPham1 = iChiTietSanPhamService.save(chiTietSanPham);
        HttpSession session = request.getSession();
        session.setAttribute("idSP", idsp);
        session.removeAttribute("idmau");
        session.removeAttribute("idsize");
        session.removeAttribute("iddanhmuc");
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @GetMapping("/viewupdate")
    String viewupdate(Model model, @RequestParam(name = "id", required = false) String id) {

        if (id != null) {
            List<MauSac> listMau = iMauSacService.getAll();
            model.addAttribute("listMau", listMau);

            List<Size> listSize = sizeService.getAll();
            model.addAttribute("listSize", listSize);

            List<DanhMuc> listDanh = danhMucService.getAll();
            model.addAttribute("listDanh", listDanh);
            ChiTietSanPham chiTietSanPham = iChiTietSanPhamService.getOne(UUID.fromString(id));
            model.addAttribute("sp", chiTietSanPham);
            return "chitietsanpham/update";
        }
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @PostMapping("/update")
    String update(@RequestParam(name = "id", required = false) String id,
                  @RequestParam(name = "soluong") String soluong,
                  @RequestParam(name = "trangthai") String trangthai,
                  @RequestParam(name = "mau") String mau,
                  @RequestParam(name = "size") String size,
                  @RequestParam(name = "danhmuc") String danhmuc,
                  @RequestParam(name = "idsp", required = false) String idsp) {
        MauSac mauSac = iMauSacService.getOne(UUID.fromString(mau));
        Size siZe = sizeService.getOne1(UUID.fromString(size));
        DanhMuc danhMuc = danhMucService.getOne1(UUID.fromString(danhmuc));
        ChiTietSanPham chiTietSanPham = iChiTietSanPhamService.getOne(UUID.fromString(id));
        SanPham sanPham = chiTietSanPham.getSanPham();
        chiTietSanPham.setSanPham(sanPham);
        chiTietSanPham.setTrangThai(Integer.parseInt(trangthai));
        chiTietSanPham.setDanhMuc(danhMuc);
        chiTietSanPham.setMauSac(mauSac);
        chiTietSanPham.setSize(siZe);
        chiTietSanPham.setSoLuong(Integer.parseInt(soluong));

        ChiTietSanPham chiTietSanPham1 = iChiTietSanPhamService.save(chiTietSanPham);
        HttpSession session = request.getSession();
        session.setAttribute("idSP", idsp);

        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @GetMapping("/view-mau-sac/{idsp}")
    String hienThiMau(@RequestParam(defaultValue = "1") int page, Model model,
                      @PathVariable(name = "idsp") String idsp,
                      @RequestParam(name = "searchMS", required = false) String searchMS) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<MauSac> listMau;
        listMau = iMauSacService.findAll(pageable);
        if (searchMS != null) {
            listMau = iMauSacService.findByTenContains(searchMS, pageable);
        }
        model.addAttribute("listMau", listMau);
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        model.addAttribute("idsp", idsp);
        return "chitietsanpham/themnhanhmausac";
    }

    @GetMapping("/view-size/{idsp}")
    String hienThiSize(@RequestParam(defaultValue = "1") int page, Model model,
                       @PathVariable(name = "idsp") String idsp,
                       @RequestParam(name = "searchSize", required = false) String searchSize) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Size> lst;
        lst = sizeService.findAll(pageable);
        if (searchSize != null) {
            lst = sizeService.findByTenContains(searchSize, pageable);
        }
        model.addAttribute("lst", lst);
        model.addAttribute("size", new Size());
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        model.addAttribute("idsp", idsp);
        return "chitietsanpham/themnhanhsize";
    }

    @GetMapping("/view-danh-muc/{idsp}")
    String hienThiDanhMuc(@RequestParam(defaultValue = "1") int page, Model model,
                          @PathVariable(name = "idsp") String idsp,
                          @RequestParam(name = "searchDM", required = false) String searchDM) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<DanhMuc> list;
        list = danhMucService.findAll(pageable);
        if (searchDM != null) {
            list = danhMucService.findByTenContains(searchDM, pageable);
        }
        model.addAttribute("list", list);
        model.addAttribute("danhMuc", new DanhMuc());
        model.addAttribute("pageNo", page);
        model.addAttribute("page", page != 1 ? page * 5 - 4 : page);
        model.addAttribute("idsp", idsp);
        return "chitietsanpham/themnhanhdanhmuc";
    }

    @PostMapping("/add-mau-sac/{idsp}")
    String addmau(@RequestParam(name = "ten") String ten,
                  @RequestParam(name = "trangthai") String trangthai, Model model,
                  @PathVariable(name = "idsp") String idsp) {
        MauSac mauSac = new MauSac(ten, Integer.parseInt(trangthai));
        MauSac mauSac1 = iMauSacService.save(mauSac);
        HttpSession session = request.getSession();
        session.setAttribute("idsp", idsp);
        //hiển thị
        return "redirect:/chi-tiet-san-pham/view-mau-sac/{idsp}";
    }

    @PostMapping("/add-size/{idsp}")
    String addsize(@ModelAttribute("size") Size size, BindingResult result,
                   @PathVariable(name = "idsp") String idsp) {
        if (result.hasErrors()) {
            return "/kichco/kichco";
        } else {
            UUID id = UUID.randomUUID();
            size.setId(String.valueOf(id));
            sizeService.add(size);
            return "redirect:/chi-tiet-san-pham/view-size/{idsp}";
        }

    }

    @PostMapping("/add-danh-muc/{idsp}")
    public String add(@ModelAttribute("danhMuc") DanhMuc danhMuc, BindingResult result, @PathVariable(name = "idsp") String idsp) {
        if (result.hasErrors()) {
            return "danhmuc/danhmuc";
        } else {
            UUID uuid = UUID.randomUUID();
            danhMuc.setId(String.valueOf(uuid));
            danhMucService.add(danhMuc);
            return "redirect:/chi-tiet-san-pham/view-danh-muc/{idsp}";
        }
    }

    @GetMapping("/idmau/{idsp}")
    String idmau(@RequestParam(name = "idmau", required = false) String idmau,
                 @PathVariable(name = "idsp") String idsp) {
        HttpSession session = request.getSession();
        session.setAttribute("idmau", idmau);
        session.setAttribute("idSP", idsp);
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @GetMapping("/idsize/{idsp}")
    String idsize(@RequestParam(name = "idsize", required = false) String idsize,
                  @PathVariable(name = "idsp") String idsp) {
        HttpSession session = request.getSession();
        session.setAttribute("idsize", idsize);
        session.setAttribute("idSP", idsp);
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @GetMapping("/iddanhmuc/{idsp}")
    String iddanhmuc(@RequestParam(name = "iddanhmuc", required = false) String iddanhmuc,
                     @PathVariable(name = "idsp") String idsp) {
        HttpSession session = request.getSession();
        session.setAttribute("iddanhmuc", iddanhmuc);
        session.setAttribute("idSP", idsp);
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }


}
