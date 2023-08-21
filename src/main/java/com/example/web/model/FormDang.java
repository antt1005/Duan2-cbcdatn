package com.example.web.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
@Table(name = "formdang")
public class FormDang {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    @Column(name = "ten")
    String ten;

    @Column(name = "trangthai")
    Integer trangThai;

    public FormDang(String ten, Integer trangThai) {
        this.ten = ten;
        this.trangThai = trangThai;
    }
}
