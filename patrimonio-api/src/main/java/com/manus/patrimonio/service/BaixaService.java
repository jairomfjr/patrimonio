package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.BaixaDTO;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.exception.RegraDeNegocioException;
import com.manus.patrimonio.mapper.PatrimonioMapper;
import com.manus.patrimonio.model.Baixa;
import com.manus.patrimonio.model.Bem;
import com.manus.patrimonio.repository.BaixaRepository;
import com.manus.patrimonio.repository.BemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class BaixaService {

    @Autowired
    private BaixaRepository baixaRepository;

    @Autowired
    private BemRepository bemRepository;

    @Autowired
    private PatrimonioMapper patrimonioMapper;

    // CRUD Operations
    public BaixaDTO criar(BaixaDTO baixaDTO) {
        validarBaixa(baixaDTO);
        
        Baixa baixa = patrimonioMapper.baixaDTOToBaixa(baixaDTO);
        
        // Buscar o bem
        Bem bem = bemRepository.findById(baixaDTO.getBemId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Bem não encontrado"));
        
        // Verificar se o bem já foi baixado
        if (bem.getBaixas() != null && !bem.getBaixas().isEmpty()) {
            throw new RegraDeNegocioException("Bem já foi baixado anteriormente");
        }
        
        baixa.setBem(bem);
        
        // Atualizar status do bem para BAIXADO
        bem.setStatus(com.manus.patrimonio.enums.StatusBem.BAIXADO);
        bemRepository.save(bem);
        
        Baixa baixaSalva = baixaRepository.save(baixa);
        return patrimonioMapper.baixaToBaixaDTO(baixaSalva);
    }

    @Cacheable(value = "baixas", key = "#id")
    public BaixaDTO buscarPorId(Long id) {
        Baixa baixa = baixaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Baixa não encontrada"));
        
        return patrimonioMapper.baixaToBaixaDTO(baixa);
    }

    @Cacheable(value = "baixas", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<BaixaDTO> buscarTodas(Pageable pageable) {
        Page<Baixa> baixas = baixaRepository.findAll(pageable);
        return baixas.map(patrimonioMapper::baixaToBaixaDTO);
    }

    @CacheEvict(value = "baixas", allEntries = true)
    public BaixaDTO atualizar(Long id, BaixaDTO baixaDTO) {
        Baixa baixaExistente = baixaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Baixa não encontrada"));
        
        validarBaixa(baixaDTO);
        
        // Atualizar campos
        baixaExistente.setMotivo(baixaDTO.getMotivo());
        baixaExistente.setDescricao(baixaDTO.getDescricao());
        baixaExistente.setDataBaixa(baixaDTO.getDataBaixa());
        baixaExistente.setValorResidual(baixaDTO.getValorResidual());
        baixaExistente.setProcessoAdministrativo(baixaDTO.getProcessoAdministrativo());
        baixaExistente.setResponsavel(baixaDTO.getResponsavel());
        baixaExistente.setObservacoes(baixaDTO.getObservacoes());
        baixaExistente.setJustificativaTecnica(baixaDTO.getJustificativaTecnica());
        baixaExistente.setDocumentacaoAnexada(baixaDTO.getDocumentacaoAnexada());
        baixaExistente.setDestinoFinal(baixaDTO.getDestinoFinal());
        
        Baixa baixaAtualizada = baixaRepository.save(baixaExistente);
        return patrimonioMapper.baixaToBaixaDTO(baixaAtualizada);
    }

    @CacheEvict(value = "baixas", allEntries = true)
    public void excluir(Long id) {
        Baixa baixa = baixaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Baixa não encontrada"));
        
        if (baixa.isAprovada()) {
            throw new RegraDeNegocioException("Não é possível excluir uma baixa aprovada");
        }
        
        // Restaurar status do bem
        if (baixa.getBem() != null) {
            Bem bem = baixa.getBem();
            bem.setStatus(com.manus.patrimonio.enums.StatusBem.ATIVO);
            bemRepository.save(bem);
        }
        
        baixaRepository.delete(baixa);
    }

    // Business Operations
    @CacheEvict(value = "baixas", allEntries = true)
    public BaixaDTO aprovar(Long id, String aprovadoPor, LocalDate dataAprovacao) {
        Baixa baixa = baixaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Baixa não encontrada"));
        
        if (baixa.isAprovada()) {
            throw new RegraDeNegocioException("Baixa já foi aprovada");
        }
        
        baixa.aprovar(aprovadoPor, dataAprovacao);
        Baixa baixaAprovada = baixaRepository.save(baixa);
        return patrimonioMapper.baixaToBaixaDTO(baixaAprovada);
    }

    @CacheEvict(value = "baixas", allEntries = true)
    public BaixaDTO registrarVenda(Long id, String comprador, BigDecimal valorVenda, LocalDate dataVenda) {
        Baixa baixa = baixaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Baixa não encontrada"));
        
        if (!baixa.isAprovada()) {
            throw new RegraDeNegocioException("Baixa deve ser aprovada antes de registrar venda");
        }
        
        if (baixa.isVendida()) {
            throw new RegraDeNegocioException("Baixa já foi vendida");
        }
        
        baixa.registrarVenda(comprador, valorVenda, dataVenda);
        Baixa baixaVendida = baixaRepository.save(baixa);
        return patrimonioMapper.baixaToBaixaDTO(baixaVendida);
    }

    // Search Operations
    public Page<BaixaDTO> buscarPorBem(Long bemId, Pageable pageable) {
        List<Baixa> baixas = baixaRepository.findByBemIdOrderByDataBaixaDesc(bemId);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), baixas.size());
        List<Baixa> pageContent = baixas.subList(start, end);
        return new PageImpl<>(pageContent, pageable, baixas.size())
                .map(patrimonioMapper::baixaToBaixaDTO);
    }

    public Page<BaixaDTO> buscarPorMotivo(String motivo, Pageable pageable) {
        List<Baixa> baixas = baixaRepository.findByMotivoContainingIgnoreCaseOrderByDataBaixaDesc(motivo);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), baixas.size());
        List<Baixa> pageContent = baixas.subList(start, end);
        return new PageImpl<>(pageContent, pageable, baixas.size())
                .map(patrimonioMapper::baixaToBaixaDTO);
    }

    public Page<BaixaDTO> buscarPorResponsavel(String responsavel, Pageable pageable) {
        List<Baixa> baixas = baixaRepository.findByResponsavelContainingIgnoreCaseOrderByDataBaixaDesc(responsavel);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), baixas.size());
        List<Baixa> pageContent = baixas.subList(start, end);
        return new PageImpl<>(pageContent, pageable, baixas.size())
                .map(patrimonioMapper::baixaToBaixaDTO);
    }

    public Page<BaixaDTO> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        List<Baixa> baixas = baixaRepository.findByDataBaixaBetweenOrderByDataBaixaDesc(dataInicio, dataFim);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), baixas.size());
        List<Baixa> pageContent = baixas.subList(start, end);
        return new PageImpl<>(pageContent, pageable, baixas.size())
                .map(patrimonioMapper::baixaToBaixaDTO);
    }

    public Page<BaixaDTO> buscarPorValorResidualMinimo(BigDecimal valorMinimo, Pageable pageable) {
        Page<Baixa> baixas = baixaRepository.findByValorResidualGreaterThanEqual(valorMinimo, pageable);
        return baixas.map(patrimonioMapper::baixaToBaixaDTO);
    }

    public Page<BaixaDTO> buscarPorProcessoAdministrativo(String processo, Pageable pageable) {
        List<Baixa> baixas = baixaRepository.findByProcessoAdministrativoContainingIgnoreCaseOrderByDataBaixaDesc(processo);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), baixas.size());
        List<Baixa> pageContent = baixas.subList(start, end);
        return new PageImpl<>(pageContent, pageable, baixas.size())
                .map(patrimonioMapper::baixaToBaixaDTO);
    }

    public Page<BaixaDTO> buscarPorDestinoFinal(String destino, Pageable pageable) {
        // Usar método existente e filtrar por destino
        Page<Baixa> baixas = baixaRepository.findAll(pageable);
        List<Baixa> baixasFiltradas = baixas.getContent().stream()
                .filter(b -> b.getDestinoFinal() != null && b.getDestinoFinal().toLowerCase().contains(destino.toLowerCase()))
                .toList();
        return new PageImpl<>(baixasFiltradas, pageable, baixasFiltradas.size())
                .map(patrimonioMapper::baixaToBaixaDTO);
    }

    public Page<BaixaDTO> buscarPorGanhoFinanceiroMinimo(BigDecimal ganhoMinimo, Pageable pageable) {
        List<Baixa> baixas = baixaRepository.findBaixasPorGanhoFinanceiroMinimo(ganhoMinimo);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), baixas.size());
        List<Baixa> pageContent = baixas.subList(start, end);
        return new PageImpl<>(pageContent, pageable, baixas.size())
                .map(patrimonioMapper::baixaToBaixaDTO);
    }

    // Statistics and Reports
    public BigDecimal calcularValorTotalBaixas() {
        List<Baixa> todasBaixas = baixaRepository.findAll();
        return todasBaixas.stream()
                .map(Baixa::getValorResidual)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularValorMedioBaixas() {
        List<Baixa> todasBaixas = baixaRepository.findAll();
        List<BigDecimal> valores = todasBaixas.stream()
                .map(Baixa::getValorResidual)
                .filter(Objects::nonNull)
                .toList();
        
        if (valores.isEmpty()) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal soma = valores.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return soma.divide(BigDecimal.valueOf(valores.size()), 2, BigDecimal.ROUND_HALF_UP);
    }

    public Long contarBaixasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        List<Baixa> baixas = baixaRepository.findByDataBaixaBetweenOrderByDataBaixaDesc(dataInicio, dataFim);
        return (long) baixas.size();
    }

    public Long contarBaixasAprovadas() {
        List<Baixa> baixas = baixaRepository.findBaixasAprovadas();
        return (long) baixas.size();
    }

    public Long contarBaixasVendidas() {
        List<Baixa> baixas = baixaRepository.findBaixasVendidas();
        return (long) baixas.size();
    }

    public List<BaixaDTO> buscarBaixasPendentesAprovacao() {
        List<Baixa> baixas = baixaRepository.findBaixasNaoAprovadas();
        return patrimonioMapper.baixasToBaixaDTOs(baixas);
    }

    public List<BaixaDTO> buscarBaixasAprovadasPendentesVenda() {
        List<Baixa> baixas = baixaRepository.findBaixasAprovadas();
        List<Baixa> baixasPendentesVenda = baixas.stream()
                .filter(b -> b.getValorVenda() == null || b.getDataVenda() == null)
                .toList();
        return patrimonioMapper.baixasToBaixaDTOs(baixasPendentesVenda);
    }

    // Validation
    private void validarBaixa(BaixaDTO baixaDTO) {
        if (baixaDTO.getDataBaixa() == null) {
            throw new RegraDeNegocioException("Data da baixa é obrigatória");
        }
        
        if (baixaDTO.getDataBaixa().isAfter(LocalDate.now())) {
            throw new RegraDeNegocioException("Data da baixa não pode ser futura");
        }
        
        if (baixaDTO.getValorResidual() != null && baixaDTO.getValorResidual().compareTo(BigDecimal.ZERO) < 0) {
            throw new RegraDeNegocioException("Valor residual não pode ser negativo");
        }
        
        if (baixaDTO.getValorVenda() != null && baixaDTO.getValorVenda().compareTo(BigDecimal.ZERO) < 0) {
            throw new RegraDeNegocioException("Valor de venda não pode ser negativo");
        }
    }
}
