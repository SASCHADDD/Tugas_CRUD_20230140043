package com.example.ktp.mapper;

import com.example.ktp.dto.KtpDto;
import com.example.ktp.entity.KtpEntity;
import com.example.ktp.model.Ktp;
import org.springframework.stereotype.Component;

@Component
public class KtpMapper {

    public KtpDto toDto(Ktp model) {
        if (model == null) return null;
        KtpDto dto = new KtpDto();
        dto.setId(model.getId());
        dto.setNomorKtp(model.getNomorKtp());
        dto.setNamaLengkap(model.getNamaLengkap());
        dto.setAlamat(model.getAlamat());
        dto.setTanggalLahir(model.getTanggalLahir());
        dto.setJenisKelamin(model.getJenisKelamin());
        return dto;
    }

    public Ktp toModel(KtpEntity entity) {
        if (entity == null) return null;
        Ktp model = new Ktp();
        model.setId(entity.getId());
        model.setNomorKtp(entity.getNomorKtp());
        model.setNamaLengkap(entity.getNamaLengkap());
        model.setAlamat(entity.getAlamat());
        model.setTanggalLahir(entity.getTanggalLahir());
        model.setJenisKelamin(entity.getJenisKelamin());
        return model;
    }

    public KtpEntity toEntity(Ktp model) {
        if (model == null) return null;
        KtpEntity entity = new KtpEntity();
        entity.setId(model.getId());
        entity.setNomorKtp(model.getNomorKtp());
        entity.setNamaLengkap(model.getNamaLengkap());
        entity.setAlamat(model.getAlamat());
        entity.setTanggalLahir(model.getTanggalLahir());
        entity.setJenisKelamin(model.getJenisKelamin());
        return entity;
    }

    public Ktp requestToModel(KtpDto dto) {
        if (dto == null) return null;
        Ktp model = new Ktp();
        model.setId(dto.getId());
        model.setNomorKtp(dto.getNomorKtp());
        model.setNamaLengkap(dto.getNamaLengkap());
        model.setAlamat(dto.getAlamat());
        model.setTanggalLahir(dto.getTanggalLahir());
        model.setJenisKelamin(dto.getJenisKelamin());
        return model;
    }
}
