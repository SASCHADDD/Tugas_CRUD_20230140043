package com.example.ktp.repository;

import com.example.ktp.entity.KtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KtpRepository extends JpaRepository<KtpEntity, Integer> {

    Optional<KtpEntity> findByNomorKtp(String nomorKtp);

}
