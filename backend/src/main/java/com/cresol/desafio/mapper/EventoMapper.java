package com.cresol.desafio.mapper;

import com.cresol.desafio.dto.request.EventoRequestDTO;
import com.cresol.desafio.dto.response.EventoResponseDTO;
import com.cresol.desafio.entity.Evento;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    public Evento toEntity(EventoRequestDTO dto) {

        Evento entity = new Evento();

        entity.setNome(dto.getNome());
        entity.setDataInicial(dto.getDataInicial());
        entity.setHoraInicial(dto.getHoraInicial());
        entity.setDataFinal(dto.getDataFinal());
        entity.setHoraFinal(dto.getHoraFinal());

        return entity;
    }

    public EventoResponseDTO toResponseDTO(Evento entity) {

        EventoResponseDTO dto = new EventoResponseDTO();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataInicial(entity.getDataInicial());
        dto.setHoraInicial(entity.getHoraInicial());
        dto.setDataFinal(entity.getDataFinal());
        dto.setHoraFinal(entity.getHoraFinal());
        dto.setAtivo(entity.getAtivo());

        if (entity.getInstituicao() != null) {
            dto.setInstituicaoId(entity.getInstituicao().getId());
            dto.setInstituicaoNome(entity.getInstituicao().getNome());
        }
        return dto;
    }
}