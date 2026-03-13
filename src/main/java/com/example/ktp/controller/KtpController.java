package com.example.ktp.controller;

import com.example.ktp.dto.KtpDto;
import com.example.ktp.service.KtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RestControllerAdvice
    public static class GlobalExceptionHandler {
        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
