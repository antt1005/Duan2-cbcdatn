package com.example.web.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "sanpham")
public class SanPham {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "ten")
    String ten;

    @Column(name = "trangthai")
    Integer trangThai;

    @Column(name = "ngaytao")
    Date ngayTao;

    @Column(name = "gianhap")
    BigDecimal giaNhap;

    @Column(name = "giaban")
    BigDecimal giaBan;

    @Column(name = "mota")
    String moTa;

    @ManyToOne
    @JoinColumn (name = "idformdang")
    FormDang formDang;

    @ManyToOne
    @JoinColumn (name = "idchatlieu")
    ChatLieu chatLieu;

    public SanPham( String ten, Integer trangThai, Date ngayTao, BigDecimal giaNhap, BigDecimal giaBan, String moTa, FormDang formDang, ChatLieu chatLieu) {
        this.ten = ten;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.moTa = moTa;
        this.formDang = formDang;
        this.chatLieu = chatLieu;
    }
}
