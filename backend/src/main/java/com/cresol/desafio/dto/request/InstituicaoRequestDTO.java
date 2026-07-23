package com.cresol.desafio.dto.request;

import com.cresol.desafio.entity.TipoInstituicao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InstituicaoRequestDTO {

    @NotBlank(message = "O nome da instituição é obrigatório")
    private String nome;

    @NotNull(message = "O tipo da instituição é obrigatório")
    private TipoInstituicao tipo;
}