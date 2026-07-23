package com.cresol.desafio.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventoResponseDTO {

    private Integer id;
    private String nome;
    private LocalDate dataInicial;
    private LocalTime horaInicial;
    private LocalDate dataFinal;
    private LocalTime horaFinal;
    private Boolean ativo;
    private Integer instituicaoId;
    private String instituicaoNome;
}