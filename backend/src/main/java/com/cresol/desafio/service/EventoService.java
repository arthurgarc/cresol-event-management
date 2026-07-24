package com.cresol.desafio.service;

import com.cresol.desafio.entity.Evento;
import com.cresol.desafio.entity.Instituicao;
import com.cresol.desafio.repository.EventoRepository;
import com.cresol.desafio.repository.InstituicaoRepository;
import com.cresol.desafio.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final InstituicaoRepository instituicaoRepository;

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Evento buscarPorId(Integer id) {
        return eventoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com ID: " + id));
    }

    @Transactional
    public Evento salvar(Evento evento, Integer instituicaoId) {
        validarPeriodo(evento);
        Instituicao instituicao = buscarInstituicao(instituicaoId);

        evento.setInstituicao(instituicao);
        evento.setAtivo(false);
        
        return eventoRepository.save(evento);
    }

    @Transactional
    public Evento atualizar(Integer id, Evento eventoAtualizado, Integer instituicaoId) {
        Evento evento = buscarPorId(id);
        validarPeriodo(eventoAtualizado);
        Instituicao instituicao = buscarInstituicao(instituicaoId);

        evento.setNome(eventoAtualizado.getNome());
        evento.setDataInicial(eventoAtualizado.getDataInicial());
        evento.setHoraInicial(eventoAtualizado.getHoraInicial());
        evento.setDataFinal(eventoAtualizado.getDataFinal());
        evento.setHoraFinal(eventoAtualizado.getHoraFinal());
        evento.setInstituicao(instituicao);

        return eventoRepository.save(evento);
    }

    @Transactional
    public void excluir(Integer id) {
        Evento evento = buscarPorId(id);
        eventoRepository.delete(evento);
    }

    private void validarPeriodo(Evento evento) {
        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime inicio = LocalDateTime.of(evento.getDataInicial(), evento.getHoraInicial());
        LocalDateTime fim = LocalDateTime.of(evento.getDataFinal(), evento.getHoraFinal());

        if (inicio.isBefore(horaAtual)) {
            throw new IllegalArgumentException("A hora inicial deve ser maior do que a hora atual.");
        }

        if (fim.isBefore(horaAtual)) {
            throw new IllegalArgumentException("A hora final deve ser maior do que a hora atual.");
        }

        if (fim.isBefore(inicio) || fim.isEqual(inicio)) {
            throw new IllegalArgumentException("A data/hora final deve ser posterior à data/hora inicial.");
        }
    }

    private Instituicao buscarInstituicao(Integer instituicaoId) {
        return instituicaoRepository.findById(instituicaoId).orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada com ID: " + instituicaoId));
    }

    @Transactional
    public void atualizarStatusEventos() {

        LocalDate dataAtual = LocalDate.now();
        LocalTime horarioAtual = LocalTime.now();

        List<Evento> eventosParaAtivar = eventoRepository.findEventosParaAtivar(dataAtual, horarioAtual);
        List<Evento> eventosParaInativar = eventoRepository.findEventosParaInativar(dataAtual, horarioAtual);

        if (!eventosParaAtivar.isEmpty()) {
            eventosParaAtivar.forEach(evento -> evento.setAtivo(true));
            eventoRepository.saveAll(eventosParaAtivar);
        }

        if (!eventosParaInativar.isEmpty()) {
            eventosParaInativar.forEach(evento -> evento.setAtivo(false));
            eventoRepository.saveAll(eventosParaInativar);
        }
    }
}