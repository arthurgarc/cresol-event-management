package com.cresol.desafio.controller;

import com.cresol.desafio.dto.request.EventoRequestDTO;
import com.cresol.desafio.dto.response.EventoResponseDTO;
import com.cresol.desafio.entity.Evento;
import com.cresol.desafio.mapper.EventoMapper;
import com.cresol.desafio.service.EventoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;
    private final EventoMapper eventoMapper;

    @PostMapping
    public ResponseEntity<EventoResponseDTO> criar(@Valid @RequestBody EventoRequestDTO dto) {
        Evento evento = eventoMapper.toEntity(dto);
        Evento eventoSalvar = eventoService.salvar(evento, dto.getInstituicaoId());
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoMapper.toResponseDTO(eventoSalvar));
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> listarTodos() {
        List<Evento> eventos = eventoService.listarTodos();
        List<EventoResponseDTO> response = eventos.stream().map(eventoMapper::toResponseDTO).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> buscarPorId(@PathVariable Integer id) {
        Evento evento = eventoService.buscarPorId(id);
        return ResponseEntity.ok(eventoMapper.toResponseDTO(evento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody EventoRequestDTO dto) {
        Evento evento = eventoMapper.toEntity(dto);
        Evento eventoAtualizar = eventoService.atualizar(id, evento, dto.getInstituicaoId());
        return ResponseEntity.ok(eventoMapper.toResponseDTO(eventoAtualizar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        eventoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}