package com.example.web.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "chitietsanpham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChiTietSanPham {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idsanpham")
    SanPham sanPham;

    @Column(name = "soluong")
    Integer soLuong;

    @Column(name = "trangthai")
    Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "idmausac")
    MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "idsize")
    Size size;

    @ManyToOne
    @JoinColumn(name = "iddanhmuc")
    DanhMuc danhMuc;

    public ChiTietSanPham(SanPham sanPham, Integer soLuong, Integer trangThai, MauSac mauSac, Size size, DanhMuc danhMuc) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.mauSac = mauSac;
        this.size = size;
        this.danhMuc = danhMuc;
    }
}
