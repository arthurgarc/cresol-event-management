package com.cresol.desafio.service;

import com.cresol.desafio.entity.Instituicao;
import com.cresol.desafio.exception.ResourceNotFoundException;
import com.cresol.desafio.repository.InstituicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;

    public List<Instituicao> listarTodas() {
        return instituicaoRepository.findAll();
    }

    public Instituicao buscarPorId(Integer id) {
        return instituicaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Instituição não encontrada com ID: " + id));
    }

    @Transactional
    public Instituicao salvar(Instituicao instituicao) {
        return instituicaoRepository.save(instituicao);
    }

    @Transactional
    public Instituicao atualizar(Integer id, Instituicao instituicaoAtualizada) {
        Instituicao instituicao = buscarPorId(id);
        instituicao.setNome(instituicaoAtualizada.getNome());
        instituicao.setTipo(instituicaoAtualizada.getTipo());
        return instituicaoRepository.save(instituicao);
    }

    @Transactional
    public void excluir(Integer id) {
        Instituicao instituicao = buscarPorId(id);
        instituicaoRepository.delete(instituicao);
    }
}