package com.example.ktp.dto;

import lombok.Data;

import java.util.Date;

@Data
public class KtpDto {
    private Integer id;
    private String nomorKtp;
    private String namaLengkap;
    private String alamat;
    private Date tanggalLahir;
    private String jenisKelamin;
}
