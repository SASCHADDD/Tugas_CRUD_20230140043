package com.example.ktp.service.impl;

import com.example.ktp.dto.KtpDto;
import com.example.ktp.model.Ktp;
import com.example.ktp.repository.KtpRepository;
import com.example.ktp.service.KtpService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {

    private final KtpRepository repository;

    public KtpServiceImpl(KtpRepository repository) {
        this.repository = repository;
    }

    private KtpDto mapToDto(Ktp ktp) {
        KtpDto dto = new KtpDto();
        dto.setId(ktp.getId());
        dto.setNomorKtp(ktp.getNomorKtp());
        dto.setNamaLengkap(ktp.getNamaLengkap());
        dto.setAlamat(ktp.getAlamat());
        dto.setTanggalLahir(ktp.getTanggalLahir());
        dto.setJenisKelamin(ktp.getJenisKelamin());
        return dto;
    }

    private Ktp mapToEntity(KtpDto dto) {
        Ktp ktp = new Ktp();
        ktp.setId(dto.getId());
        ktp.setNomorKtp(dto.getNomorKtp());
        ktp.setNamaLengkap(dto.getNamaLengkap());
        ktp.setAlamat(dto.getAlamat());
        ktp.setTanggalLahir(dto.getTanggalLahir());
        ktp.setJenisKelamin(dto.getJenisKelamin());
        return ktp;
    }

    @Override
    public KtpDto create(KtpDto dto) {
        repository.findByNomorKtp(dto.getNomorKtp())
                .ifPresent(x -> {
                    throw new RuntimeException("Nomor KTP sudah ada");
                });

        Ktp ktp = repository.save(mapToEntity(dto));
        return mapToDto(ktp);
    }

    @Override
    public List<KtpDto> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public KtpDto getById(Integer id) {
        Ktp ktp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));

        return mapToDto(ktp);
    }

    @Override
    public KtpDto update(Integer id, KtpDto dto) {
        Ktp ktp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data tidak ditemukan"));

        ktp.setNomorKtp(dto.getNomorKtp());
        ktp.setNamaLengkap(dto.getNamaLengkap());
        ktp.setAlamat(dto.getAlamat());
        ktp.setTanggalLahir(dto.getTanggalLahir());
        ktp.setJenisKelamin(dto.getJenisKelamin());

        return mapToDto(repository.save(ktp));
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Data tidak ditemukan");
        }
        repository.deleteById(id);
    }
}