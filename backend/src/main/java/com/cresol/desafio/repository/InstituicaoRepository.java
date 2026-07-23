package com.cresol.desafio.repository;

import com.cresol.desafio.entity.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Integer> {
}