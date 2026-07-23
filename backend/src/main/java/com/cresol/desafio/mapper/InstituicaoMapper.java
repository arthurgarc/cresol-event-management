package com.cresol.desafio.mapper;

import com.cresol.desafio.dto.request.InstituicaoRequestDTO;
import com.cresol.desafio.dto.response.InstituicaoResponseDTO;
import com.cresol.desafio.entity.Instituicao;
import org.springframework.stereotype.Component;

@Component
public class InstituicaoMapper {

    public Instituicao toEntity(InstituicaoRequestDTO dto) {
        Instituicao entity = new Instituicao();
        entity.setNome(dto.getNome());
        entity.setTipo(dto.getTipo());
        return entity;
    }

    public InstituicaoResponseDTO toResponseDTO(Instituicao entity) {
        InstituicaoResponseDTO dto = new InstituicaoResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setTipo(entity.getTipo());
        return dto;
    }
}