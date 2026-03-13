package com.example.ktp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "ktp")
@Data
public class KtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String nomorKtp;

    private String namaLengkap;

    private String alamat;

    private LocalDate tanggalLahir;

    private String jenisKelamin;
}
