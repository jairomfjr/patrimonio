package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.MovimentacaoDTO;
import com.manus.patrimonio.enums.TipoMovimentacao;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.mapper.PatrimonioMapper;
import com.manus.patrimonio.model.Movimentacao;
import com.manus.patrimonio.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private PatrimonioMapper patrimonioMapper;

    /**
     * Busca todas as movimentações com paginação
     */
    public Page<MovimentacaoDTO> buscarTodas(Pageable pageable) {
        Page<Movimentacao> movimentacoes = movimentacaoRepository.findAll(pageable);
        return movimentacoes.map(patrimonioMapper::movimentacaoToMovimentacaoDTO);
    }

    /**
     * Busca uma movimentação por ID
     */
    public MovimentacaoDTO buscarPorId(Long id) {
        Movimentacao movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Movimentação não encontrada com ID: " + id));
        return patrimonioMapper.movimentacaoToMovimentacaoDTO(movimentacao);
    }

    /**
     * Cria uma nova movimentação
     */
    public MovimentacaoDTO criar(MovimentacaoDTO movimentacaoDTO) {
        Movimentacao movimentacao = patrimonioMapper.movimentacaoDTOToMovimentacao(movimentacaoDTO);
        Movimentacao movimentacaoSalva = movimentacaoRepository.save(movimentacao);
        return patrimonioMapper.movimentacaoToMovimentacaoDTO(movimentacaoSalva);
    }

    /**
     * Atualiza uma movimentação existente
     */
    public MovimentacaoDTO atualizar(Long id, MovimentacaoDTO movimentacaoDTO) {
        if (!movimentacaoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Movimentação não encontrada com ID: " + id);
        }
        
        movimentacaoDTO.setId(id);
        Movimentacao movimentacao = patrimonioMapper.movimentacaoDTOToMovimentacao(movimentacaoDTO);
        Movimentacao movimentacaoSalva = movimentacaoRepository.save(movimentacao);
        return patrimonioMapper.movimentacaoToMovimentacaoDTO(movimentacaoSalva);
    }

    /**
     * Remove uma movimentação
     */
    public void remover(Long id) {
        if (!movimentacaoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Movimentação não encontrada com ID: " + id);
        }
        movimentacaoRepository.deleteById(id);
    }

    /**
     * Busca movimentações por bem
     */
    public List<MovimentacaoDTO> buscarPorBem(Long bemId) {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findByBemIdOrderByDataMovimentacaoDesc(bemId);
        return patrimonioMapper.movimentacoesToMovimentacaoDTOs(movimentacoes);
    }

    /**
     * Busca movimentações por tipo
     */
    public List<MovimentacaoDTO> buscarPorTipo(TipoMovimentacao tipo) {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findByTipoMovimentacao(tipo, Pageable.unpaged()).getContent();
        return patrimonioMapper.movimentacoesToMovimentacaoDTOs(movimentacoes);
    }

    /**
     * Busca movimentações por responsável
     */
    public List<MovimentacaoDTO> buscarPorResponsavel(String responsavel) {
        List<Movimentacao> movimentacoes = movimentacaoRepository.findByResponsavelMovimentacaoContainingIgnoreCase(responsavel, Pageable.unpaged()).getContent();
        return patrimonioMapper.movimentacoesToMovimentacaoDTOs(movimentacoes);
    }
}
