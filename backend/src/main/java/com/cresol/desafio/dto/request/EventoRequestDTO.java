package com.cresol.desafio.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventoRequestDTO {

    @NotBlank(message = "O nome do evento é obrigatório")
    private String nome;

    @NotNull(message = "A data inicial é obrigatória")
    @FutureOrPresent(message = "A data inicial deve ser hoje ou futura")
    private LocalDate dataInicial;

    @NotNull(message = "A hora inicial é obrigatória")
    private LocalTime horaInicial;

    @NotNull(message = "A data final é obrigatória")
    private LocalDate dataFinal;

    @NotNull(message = "A hora final é obrigatória")
    private LocalTime horaFinal;

    @NotNull(message = "O ID da instituição é obrigatório")
    private Integer instituicaoId;
}