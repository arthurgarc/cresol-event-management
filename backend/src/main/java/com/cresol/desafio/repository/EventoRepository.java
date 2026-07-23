package com.cresol.desafio.repository;

import com.cresol.desafio.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @Query("SELECT evt FROM Evento evt WHERE evt.ativo = false AND (evt.dataInicial < :data OR (evt.dataInicial = :data AND evt.horaInicial <= :hora))")
    List<Evento> findEventosParaAtivar(@Param("data") LocalDate data, @Param("hora") LocalTime hora);

    @Query("SELECT evt FROM Evento evt WHERE evt.ativo = true AND (evt.dataFinal < :data OR (evt.dataFinal = :data AND evt.horaFinal <= :hora))")
    List<Evento> findEventosParaInativar(@Param("data") LocalDate data, @Param("hora") LocalTime hora);
}