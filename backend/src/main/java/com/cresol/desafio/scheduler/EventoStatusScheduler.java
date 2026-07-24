package com.cresol.desafio.scheduler;

import com.cresol.desafio.service.EventoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventoStatusScheduler {

    private final EventoService eventoService;

    @Scheduled(fixedDelayString = "${scheduler.evento.intervalo}")
    public void atualizarStatusEventos() {
        log.info("Iniciando atualização automática do status dos eventos.");
        eventoService.atualizarStatusEventos();
        log.info("Atualização automática finalizada.");
    }
}