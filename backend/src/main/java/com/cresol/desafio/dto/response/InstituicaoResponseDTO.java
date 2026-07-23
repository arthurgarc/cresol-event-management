package com.cresol.desafio.dto.response;

import com.cresol.desafio.entity.TipoInstituicao;
import lombok.Data;

@Data
public class InstituicaoResponseDTO {

    private Integer id;
    private String nome;
    private TipoInstituicao tipo;
}