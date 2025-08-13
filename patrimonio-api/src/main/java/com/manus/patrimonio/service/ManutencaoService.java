package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.ManutencaoDTO;
import com.manus.patrimonio.enums.StatusManutencao;
import com.manus.patrimonio.enums.TipoManutencao;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.exception.RegraDeNegocioException;
import com.manus.patrimonio.mapper.PatrimonioMapper;
import com.manus.patrimonio.model.Bem;
import com.manus.patrimonio.model.Manutencao;
import com.manus.patrimonio.repository.BemRepository;
import com.manus.patrimonio.repository.ManutencaoRepository;
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

@Service
@Transactional
public class ManutencaoService {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @Autowired
    private BemRepository bemRepository;

    @Autowired
    private PatrimonioMapper patrimonioMapper;

    // CRUD Operations
    public ManutencaoDTO criar(ManutencaoDTO manutencaoDTO) {
        validarManutencao(manutencaoDTO);
        
        Manutencao manutencao = patrimonioMapper.manutencaoDTOToManutencao(manutencaoDTO);
        
        // Buscar o bem
        Bem bem = bemRepository.findById(manutencaoDTO.getBemId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Bem não encontrado"));
        
        manutencao.setBem(bem);
        
        // Se for manutenção preventiva, atualizar data da última manutenção
        if (TipoManutencao.PREVENTIVA.equals(manutencao.getTipoManutencao())) {
            bem.setDataUltimaManutencao(manutencao.getDataInicio());
            bemRepository.save(bem);
        }
        
        Manutencao manutencaoSalva = manutencaoRepository.save(manutencao);
        return patrimonioMapper.manutencaoToManutencaoDTO(manutencaoSalva);
    }

    @Cacheable(value = "manutencoes", key = "#id")
    public ManutencaoDTO buscarPorId(Long id) {
        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Manutenção não encontrada"));
        
        return patrimonioMapper.manutencaoToManutencaoDTO(manutencao);
    }

    @Cacheable(value = "manutencoes", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<ManutencaoDTO> buscarTodos(Pageable pageable) {
        Page<Manutencao> manutencoes = manutencaoRepository.findAll(pageable);
        return manutencoes.map(patrimonioMapper::manutencaoToManutencaoDTO);
    }

    @CacheEvict(value = "manutencoes", allEntries = true)
    public ManutencaoDTO atualizar(Long id, ManutencaoDTO manutencaoDTO) {
        Manutencao manutencaoExistente = manutencaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Manutenção não encontrada"));
        
        validarManutencao(manutencaoDTO);
        
        // Atualizar campos
        manutencaoExistente.setTipoManutencao(manutencaoDTO.getTipoManutencao());
        manutencaoExistente.setDescricao(manutencaoDTO.getDescricao());
        manutencaoExistente.setDataInicio(manutencaoDTO.getDataInicio());
        manutencaoExistente.setDataFim(manutencaoDTO.getDataFim());
        manutencaoExistente.setCusto(manutencaoDTO.getCusto());
        manutencaoExistente.setFornecedor(manutencaoDTO.getFornecedor());
        manutencaoExistente.setResponsavel(manutencaoDTO.getResponsavel());
        manutencaoExistente.setStatus(manutencaoDTO.getStatus());
        manutencaoExistente.setObservacoes(manutencaoDTO.getObservacoes());
        manutencaoExistente.setPrioridade(manutencaoDTO.getPrioridade());
        manutencaoExistente.setTempoEstimadoHoras(manutencaoDTO.getTempoEstimadoHoras());
        
        Manutencao manutencaoAtualizada = manutencaoRepository.save(manutencaoExistente);
        return patrimonioMapper.manutencaoToManutencaoDTO(manutencaoAtualizada);
    }

    @CacheEvict(value = "manutencoes", allEntries = true)
    public void excluir(Long id) {
        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Manutenção não encontrada"));
        
        if (manutencao.isAtiva()) {
            throw new RegraDeNegocioException("Não é possível excluir uma manutenção ativa");
        }
        
        manutencaoRepository.delete(manutencao);
    }

    // Business Operations
    @CacheEvict(value = "manutencoes", allEntries = true)
    public ManutencaoDTO finalizar(Long id, LocalDate dataFim, String solucaoAplicada, String recomendacoes) {
        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Manutenção não encontrada"));
        
        if (!manutencao.isAtiva()) {
            throw new RegraDeNegocioException("Manutenção não está ativa");
        }
        
        manutencao.finalizar(dataFim, solucaoAplicada, recomendacoes);
        
        // Atualizar bem
        if (manutencao.getBem() != null) {
            Bem bem = manutencao.getBem();
            bem.setDataUltimaManutencao(dataFim);
            bemRepository.save(bem);
        }
        
        Manutencao manutencaoFinalizada = manutencaoRepository.save(manutencao);
        return patrimonioMapper.manutencaoToManutencaoDTO(manutencaoFinalizada);
    }

    @CacheEvict(value = "manutencoes", allEntries = true)
    public ManutencaoDTO cancelar(Long id, String motivo) {
        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Manutenção não encontrada"));
        
        if (!manutencao.isAtiva()) {
            throw new RegraDeNegocioException("Manutenção não está ativa");
        }
        
        manutencao.cancelar(motivo);
        Manutencao manutencaoCancelada = manutencaoRepository.save(manutencao);
        return patrimonioMapper.manutencaoToManutencaoDTO(manutencaoCancelada);
    }

    @CacheEvict(value = "manutencoes", allEntries = true)
    public ManutencaoDTO alterarStatus(Long id, StatusManutencao novoStatus) {
        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Manutenção não encontrada"));
        
        manutencao.setStatus(novoStatus);
        Manutencao manutencaoAtualizada = manutencaoRepository.save(manutencao);
        return patrimonioMapper.manutencaoToManutencaoDTO(manutencaoAtualizada);
    }

    // Search Operations
    public Page<ManutencaoDTO> buscarPorBem(Long bemId, Pageable pageable) {
        List<Manutencao> manutencoes = manutencaoRepository.findByBemIdOrderByDataInicioDesc(bemId);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), manutencoes.size());
        List<Manutencao> pageContent = manutencoes.subList(start, end);
        return new PageImpl<>(pageContent, pageable, manutencoes.size())
                .map(patrimonioMapper::manutencaoToManutencaoDTO);
    }

    public Page<ManutencaoDTO> buscarPorStatus(StatusManutencao status, Pageable pageable) {
        Page<Manutencao> manutencoes = manutencaoRepository.findByStatus(status, pageable);
        return manutencoes.map(patrimonioMapper::manutencaoToManutencaoDTO);
    }

    public Page<ManutencaoDTO> buscarPorTipo(TipoManutencao tipo, Pageable pageable) {
        Page<Manutencao> manutencoes = manutencaoRepository.findByTipoManutencao(tipo, pageable);
        return manutencoes.map(patrimonioMapper::manutencaoToManutencaoDTO);
    }

    public Page<ManutencaoDTO> buscarPorFornecedor(String fornecedor, Pageable pageable) {
        Page<Manutencao> manutencoes = manutencaoRepository.findByFornecedorContainingIgnoreCase(fornecedor, pageable);
        return manutencoes.map(patrimonioMapper::manutencaoToManutencaoDTO);
    }

    public Page<ManutencaoDTO> buscarPorResponsavel(String responsavel, Pageable pageable) {
        Page<Manutencao> manutencoes = manutencaoRepository.findByResponsavelContainingIgnoreCase(responsavel, pageable);
        return manutencoes.map(patrimonioMapper::manutencaoToManutencaoDTO);
    }

    public Page<ManutencaoDTO> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        Page<Manutencao> manutencoes = manutencaoRepository.findByDataInicioBetween(dataInicio, dataFim, pageable);
        return manutencoes.map(patrimonioMapper::manutencaoToManutencaoDTO);
    }

    public Page<ManutencaoDTO> buscarPorCustoMaximo(BigDecimal custoMaximo, Pageable pageable) {
        List<Manutencao> manutencoes = manutencaoRepository.findManutencoesPorCustoMinimo(custoMaximo.doubleValue());
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), manutencoes.size());
        List<Manutencao> pageContent = manutencoes.subList(start, end);
        return new PageImpl<>(pageContent, pageable, manutencoes.size())
                .map(patrimonioMapper::manutencaoToManutencaoDTO);
    }

    public Page<ManutencaoDTO> buscarPorLocalizacao(Long localizacaoId, Pageable pageable) {
        Page<Manutencao> manutencoes = manutencaoRepository.findManutencoesPorLocalizacao(localizacaoId, pageable);
        return manutencoes.map(patrimonioMapper::manutencaoToManutencaoDTO);
    }

    // Statistics and Reports
    public Long contarManutencoesPorStatus(StatusManutencao status) {
        return manutencaoRepository.countByStatus(status);
    }

    public Long contarManutencoesPorTipo(TipoManutencao tipo) {
        return manutencaoRepository.countByTipoManutencao(tipo);
    }

    public Long contarManutencoesPorBem(Long bemId) {
        return manutencaoRepository.countByBemId(bemId);
    }

    public List<ManutencaoDTO> buscarManutencoesAtrasadas() {
        List<Manutencao> manutencoes = manutencaoRepository.findManutencoesAtrasadas(LocalDate.now().minusDays(30));
        return patrimonioMapper.manutencoesToManutencaoDTOs(manutencoes);
    }

    public List<ManutencaoDTO> buscarManutencoesUrgentes() {
        List<Manutencao> manutencoes = manutencaoRepository.findManutencoesUrgentes();
        return patrimonioMapper.manutencoesToManutencaoDTOs(manutencoes);
    }

    // Validation
    private void validarManutencao(ManutencaoDTO manutencaoDTO) {
        if (manutencaoDTO.getDataInicio() == null) {
            throw new RegraDeNegocioException("Data de início é obrigatória");
        }
        
        if (manutencaoDTO.getDataFim() != null && manutencaoDTO.getDataFim().isBefore(manutencaoDTO.getDataInicio())) {
            throw new RegraDeNegocioException("Data de fim não pode ser anterior à data de início");
        }
        
        if (manutencaoDTO.getCusto() != null && manutencaoDTO.getCusto().compareTo(BigDecimal.ZERO) < 0) {
            throw new RegraDeNegocioException("Custo não pode ser negativo");
        }
        
        if (manutencaoDTO.getTempoEstimadoHoras() != null && manutencaoDTO.getTempoEstimadoHoras() <= 0) {
            throw new RegraDeNegocioException("Tempo estimado deve ser maior que zero");
        }
    }
}
