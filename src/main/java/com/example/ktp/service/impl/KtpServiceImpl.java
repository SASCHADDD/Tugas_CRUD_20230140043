package com.example.ktp.service.impl;

import com.example.ktp.dto.KtpDto;
import com.example.ktp.entity.KtpEntity;
import com.example.ktp.mapper.KtpMapper;
import com.example.ktp.model.Ktp;
import com.example.ktp.repository.KtpRepository;
import com.example.ktp.service.KtpService;
import com.example.ktp.util.KtpUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {

    private final KtpRepository repository;
    private final KtpMapper mapper;

    public KtpServiceImpl(KtpRepository repository, KtpMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public KtpDto create(KtpDto dto) {
        repository.findByNomorKtp(dto.getNomorKtp())
                .ifPresent(x -> {
                    throw new RuntimeException(KtpUtil.ERROR_DUPLICATE);
                });

        Ktp model = mapper.requestToModel(dto);
        KtpEntity entity = mapper.toEntity(model);
        KtpEntity savedEntity = repository.save(entity);
        
        return mapper.toDto(mapper.toModel(savedEntity));
    }

    @Override
    public List<KtpDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toModel)
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public KtpDto getById(Integer id) {
        KtpEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(KtpUtil.ERROR_NOT_FOUND));

        return mapper.toDto(mapper.toModel(entity));
    }

    @Override
    public KtpDto update(Integer id, KtpDto dto) {
        KtpEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(KtpUtil.ERROR_NOT_FOUND));

        entity.setNomorKtp(dto.getNomorKtp());
        entity.setNamaLengkap(dto.getNamaLengkap());
        entity.setAlamat(dto.getAlamat());
        entity.setTanggalLahir(dto.getTanggalLahir());
        entity.setJenisKelamin(dto.getJenisKelamin());

        KtpEntity updatedEntity = repository.save(entity);
        return mapper.toDto(mapper.toModel(updatedEntity));
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException(KtpUtil.ERROR_NOT_FOUND);
        }
        repository.deleteById(id);
    }
}