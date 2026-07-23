package com.cresol.desafio.controller;

import com.cresol.desafio.dto.request.InstituicaoRequestDTO;
import com.cresol.desafio.dto.response.InstituicaoResponseDTO;
import com.cresol.desafio.entity.Instituicao;
import com.cresol.desafio.mapper.InstituicaoMapper;
import com.cresol.desafio.service.InstituicaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instituicoes")
@RequiredArgsConstructor
public class InstituicaoController {

    private final InstituicaoService instituicaoService;
    private final InstituicaoMapper instituicaoMapper;

    @PostMapping
    public ResponseEntity<InstituicaoResponseDTO> criar(@Valid @RequestBody InstituicaoRequestDTO dto) {
        Instituicao instituicao = instituicaoMapper.toEntity(dto);
        Instituicao instituicaoSalva = instituicaoService.salvar(instituicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(instituicaoMapper.toResponseDTO(instituicaoSalva));
    }

    @GetMapping
    public ResponseEntity<List<InstituicaoResponseDTO>> listarTodos() {
        List<Instituicao> instituicoes = instituicaoService.listarTodas();
        List<InstituicaoResponseDTO> response = instituicoes.stream().map(instituicaoMapper::toResponseDTO).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoResponseDTO> buscarPorId(@PathVariable Integer id) {
        Instituicao instituicao = instituicaoService.buscarPorId(id);
        return ResponseEntity.ok(instituicaoMapper.toResponseDTO(instituicao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstituicaoResponseDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody InstituicaoRequestDTO dto) {
        Instituicao instituicao = instituicaoMapper.toEntity(dto);
        Instituicao instituicaoAtualizada =instituicaoService.atualizar(id, instituicao);
        return ResponseEntity.ok(instituicaoMapper.toResponseDTO(instituicaoAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        instituicaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}