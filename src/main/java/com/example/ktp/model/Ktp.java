package com.example.ktp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "ktp")
@Data
public class Ktp {
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
