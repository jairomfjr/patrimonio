package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.AuditoriaDTO;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.mapper.PatrimonioMapper;
import com.manus.patrimonio.model.Auditoria;
import com.manus.patrimonio.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Autowired
    private PatrimonioMapper patrimonioMapper;

    // CRUD Operations
    @Cacheable(value = "auditorias", key = "#id")
    public AuditoriaDTO buscarPorId(Long id) {
        Auditoria auditoria = auditoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Auditoria não encontrada"));

        return patrimonioMapper.auditoriaToAuditoriaDTO(auditoria);
    }

    @Cacheable(value = "auditorias", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<AuditoriaDTO> buscarTodas(Pageable pageable) {
        Page<Auditoria> auditorias = auditoriaRepository.findAll(pageable);
        return auditorias.map(patrimonioMapper::auditoriaToAuditoriaDTO);
    }

    // Search Operations
    public Page<AuditoriaDTO> buscarPorEntidade(String entidade, Pageable pageable) {
        Page<Auditoria> auditorias = auditoriaRepository.findByEntidade(entidade, pageable);
        return auditorias.map(patrimonioMapper::auditoriaToAuditoriaDTO);
    }

    public Page<AuditoriaDTO> buscarPorEntidadeEId(String entidade, Long entidadeId, Pageable pageable) {
        Page<Auditoria> auditorias = auditoriaRepository.findByEntidadeAndEntidadeId(entidade, entidadeId, pageable);
        return auditorias.map(patrimonioMapper::auditoriaToAuditoriaDTO);
    }

    public Page<AuditoriaDTO> buscarPorAcao(String acao, Pageable pageable) {
        Page<Auditoria> auditorias = auditoriaRepository.findByAcao(acao, pageable);
        return auditorias.map(patrimonioMapper::auditoriaToAuditoriaDTO);
    }

    public Page<AuditoriaDTO> buscarPorUsuario(Long usuarioId, Pageable pageable) {
        Page<Auditoria> auditorias = auditoriaRepository.findByUsuarioId(usuarioId, pageable);
        return auditorias.map(patrimonioMapper::auditoriaToAuditoriaDTO);
    }

    public Page<AuditoriaDTO> buscarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable) {
        Page<Auditoria> auditorias = auditoriaRepository.findByDataAcaoBetween(dataInicio, dataFim, pageable);
        return auditorias.map(patrimonioMapper::auditoriaToAuditoriaDTO);
    }

    public Page<AuditoriaDTO> buscarPorEntidadeEPeriodo(String entidade, LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable) {
        List<Auditoria> auditorias = auditoriaRepository.findByEntidadeAndPeriodo(entidade, dataInicio, dataFim);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), auditorias.size());
        List<Auditoria> pageContent = auditorias.subList(start, end);
        return new org.springframework.data.domain.PageImpl<>(pageContent, pageable, auditorias.size())
                .map(patrimonioMapper::auditoriaToAuditoriaDTO);
    }

    public Page<AuditoriaDTO> buscarPorUsuarioEPeriodo(Long usuarioId, LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable) {
        List<Auditoria> auditorias = auditoriaRepository.findByUsuarioAndPeriodo(usuarioId, dataInicio, dataFim);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), auditorias.size());
        List<Auditoria> pageContent = auditorias.subList(start, end);
        return new org.springframework.data.domain.PageImpl<>(pageContent, pageable, auditorias.size())
                .map(patrimonioMapper::auditoriaToAuditoriaDTO);
    }

    public Page<AuditoriaDTO> buscarPorAcaoEPeriodo(String acao, LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable) {
        List<Auditoria> auditorias = auditoriaRepository.findByAcaoAndPeriodo(acao, dataInicio, dataFim);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), auditorias.size());
        List<Auditoria> pageContent = auditorias.subList(start, end);
        return new org.springframework.data.domain.PageImpl<>(pageContent, pageable, auditorias.size())
                .map(patrimonioMapper::auditoriaToAuditoriaDTO);
    }

    public List<AuditoriaDTO> buscarPorIp(String ipAddress) {
        List<Auditoria> auditorias = auditoriaRepository.findByIpAddressOrderByDataAcaoDesc(ipAddress);
        return patrimonioMapper.auditoriasToAuditoriaDTOs(auditorias);
    }

    public Page<AuditoriaDTO> buscarPorEntidadeEAcao(String entidade, String acao, Pageable pageable) {
        Page<Auditoria> auditorias = auditoriaRepository.findByEntidadeAndAcao(entidade, acao, pageable);
        return auditorias.map(patrimonioMapper::auditoriaToAuditoriaDTO);
    }

    public Page<AuditoriaDTO> buscarPorUsuarioEAcao(Long usuarioId, String acao, Pageable pageable) {
        Page<Auditoria> auditorias = auditoriaRepository.findByUsuarioIdAndAcao(usuarioId, acao, pageable);
        return auditorias.map(patrimonioMapper::auditoriaToAuditoriaDTO);
    }

    // Special Queries
    public List<AuditoriaDTO> buscarAuditoriasCriacao() {
        List<Auditoria> auditorias = auditoriaRepository.findAuditoriasCriacao();
        return patrimonioMapper.auditoriasToAuditoriaDTOs(auditorias);
    }

    public List<AuditoriaDTO> buscarAuditoriasAtualizacao() {
        List<Auditoria> auditorias = auditoriaRepository.findAuditoriasAtualizacao();
        return patrimonioMapper.auditoriasToAuditoriaDTOs(auditorias);
    }

    public List<AuditoriaDTO> buscarAuditoriasExclusao() {
        List<Auditoria> auditorias = auditoriaRepository.findAuditoriasExclusao();
        return patrimonioMapper.auditoriasToAuditoriaDTOs(auditorias);
    }

    public List<AuditoriaDTO> buscarAuditoriasConsulta() {
        List<Auditoria> auditorias = auditoriaRepository.findAuditoriasConsulta();
        return patrimonioMapper.auditoriasToAuditoriaDTOs(auditorias);
    }

    // Statistics
    public Long contarPorEntidade(String entidade) {
        return auditoriaRepository.countByEntidade(entidade);
    }

    public Long contarPorAcao(String acao) {
        return auditoriaRepository.countByAcao(acao);
    }

    public Long contarPorUsuario(Long usuarioId) {
        return auditoriaRepository.countByUsuarioId(usuarioId);
    }

    public Long contarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return auditoriaRepository.countByPeriodo(dataInicio, dataFim);
    }

    public Long contarPorEntidadeEPeriodo(String entidade, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return auditoriaRepository.countByEntidadeAndPeriodo(entidade, dataInicio, dataFim);
    }
}
