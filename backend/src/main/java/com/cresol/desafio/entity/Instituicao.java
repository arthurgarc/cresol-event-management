package com.cresol.desafio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instituicao")
@Data
@NoArgsConstructor
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TipoInstituicao tipo;
}