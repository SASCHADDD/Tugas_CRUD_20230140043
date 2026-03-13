package com.example.ktp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ktp")
public class KtpController {
    private final KtpService service;

    public KtpController(KtpService service) {
        this.service = service;
    }

    @PostMapping
    public KtpDto create(@RequestBody KtpDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<KtpDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public KtpDto getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public KtpDto update(@PathVariable Integer id, @RequestBody KtpDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
