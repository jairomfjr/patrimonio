package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.BemDTO;
import com.manus.patrimonio.enums.CondicaoBem;
import com.manus.patrimonio.enums.StatusBem;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.exception.RegraDeNegocioException;
import com.manus.patrimonio.mapper.PatrimonioMapper;
import com.manus.patrimonio.model.Bem;
import com.manus.patrimonio.model.Categoria;
import com.manus.patrimonio.model.Localizacao;
import com.manus.patrimonio.repository.BemRepository;
import com.manus.patrimonio.repository.CategoriaRepository;
import com.manus.patrimonio.repository.LocalizacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Serviço para gerenciamento de bens patrimoniais.
 * Implementa a lógica de negócio para operações CRUD e consultas específicas.
 */
@Service
@Transactional
public class BemService {

    private static final Logger log = LoggerFactory.getLogger(BemService.class);

    private final BemRepository bemRepository;
    private final CategoriaRepository categoriaRepository;
    private final LocalizacaoRepository localizacaoRepository;
    private final PatrimonioMapper patrimonioMapper;

    public BemService(BemRepository bemRepository, CategoriaRepository categoriaRepository,
                     LocalizacaoRepository localizacaoRepository, PatrimonioMapper patrimonioMapper) {
        this.bemRepository = bemRepository;
        this.categoriaRepository = categoriaRepository;
        this.localizacaoRepository = localizacaoRepository;
        this.patrimonioMapper = patrimonioMapper;
    }

    // Constantes
    private static final String BEM_NAO_ENCONTRADO = "Bem não encontrado com ID: %d";
    private static final String CATEGORIA_NAO_ENCONTRADA = "Categoria não encontrada com ID: %d";
    private static final String LOCALIZACAO_NAO_ENCONTRADA = "Localização não encontrada com ID: %d";
    private static final String TOMBAMENTO_JA_EXISTE = "Número de tombamento já existe: %s";

    /**
     * Cria um novo bem patrimonial.
     *
     * @param bemDTO DTO com os dados do bem
     * @return Bem criado
     * @throws RegraDeNegocioException se o número de tombamento já existir
     */
    @CacheEvict(value = "bens", allEntries = true)
    public BemDTO criar(BemDTO bemDTO) {
        log.info("Criando novo bem com descrição: {}", bemDTO.getDescricao());
        
        validarBem(bemDTO);
        if (bemDTO.getNumeroSerie() != null && !bemDTO.getNumeroSerie().trim().isEmpty()) {
            verificarNumeroSerieUnico(bemDTO.getNumeroSerie());
        }
        
        Bem bem = patrimonioMapper.bemDTOToBem(bemDTO);
        bem.setCategoria(buscarCategoria(bemDTO.getCategoriaId()));
        bem.setLocalizacaoAtual(buscarLocalizacao(bemDTO.getLocalizacaoAtualId()));
        
        Bem bemSalvo = bemRepository.save(bem);
        log.info("Bem criado com sucesso. ID: {}", bemSalvo.getId());
        
        return patrimonioMapper.bemToBemDTO(bemSalvo);
    }

    /**
     * Busca um bem por ID.
     *
     * @param id ID do bem
     * @return DTO do bem encontrado
     * @throws RecursoNaoEncontradoException se o bem não for encontrado
     */
    @Cacheable(value = "bens", key = "#id")
    public BemDTO buscarPorId(Long id) {
        log.debug("Buscando bem por ID: {}", id);
        
        Bem bem = bemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    String.format(BEM_NAO_ENCONTRADO, id)));
        
        return patrimonioMapper.bemToBemDTO(bem);
    }

    /**
     * Busca um bem por número de série.
     *
     * @param numeroSerie Número de série
     * @return DTO do bem encontrado
     * @throws RecursoNaoEncontradoException se o bem não for encontrado
     */
    @Cacheable(value = "bens", key = "#numeroSerie")
    public BemDTO buscarPorNumeroSerie(String numeroSerie) {
        log.debug("Buscando bem por número de série: {}", numeroSerie);
        
        Bem bem = bemRepository.findByNumeroSerie(numeroSerie)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    "Bem não encontrado com número de série: " + numeroSerie));
        
        return patrimonioMapper.bemToBemDTO(bem);
    }

    /**
     * Lista todos os bens com paginação.
     *
     * @param pageable Configuração de paginação
     * @return Página de bens
     */
    @Cacheable(value = "bens", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<BemDTO> buscarTodos(Pageable pageable) {
        log.debug("Buscando todos os bens com paginação: {}", pageable);
        
        Page<Bem> bens = bemRepository.findAll(pageable);
        return bens.map(patrimonioMapper::bemToBemDTO);
    }

    /**
     * Atualiza um bem existente.
     *
     * @param id ID do bem
     * @param bemDTO DTO com os dados atualizados
     * @return Bem atualizado
     * @throws RecursoNaoEncontradoException se o bem não for encontrado
     * @throws RegraDeNegocioException se o número de tombamento já existir
     */
    @CacheEvict(value = "bens", allEntries = true)
    public BemDTO atualizar(Long id, BemDTO bemDTO) {
        log.info("Atualizando bem com ID: {}", id);
        
        Bem bemExistente = bemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    String.format(BEM_NAO_ENCONTRADO, id)));
        
        validarBem(bemDTO);
        
        // Verificar se o número de série foi alterado e se já existe
        if (!Objects.equals(bemExistente.getNumeroSerie(), bemDTO.getNumeroSerie()) && 
            bemDTO.getNumeroSerie() != null && !bemDTO.getNumeroSerie().trim().isEmpty()) {
            verificarNumeroSerieUnico(bemDTO.getNumeroSerie());
        }
        
        // Atualizar campos
        bemExistente.setDescricao(bemDTO.getDescricao());
        bemExistente.setNumeroSerie(bemDTO.getNumeroSerie());
        bemExistente.setCategoria(buscarCategoria(bemDTO.getCategoriaId()));
        bemExistente.setLocalizacaoAtual(buscarLocalizacao(bemDTO.getLocalizacaoAtualId()));
        bemExistente.setStatus(bemDTO.getStatus());
        bemExistente.setCondicao(bemDTO.getCondicao());
        bemExistente.setDataAquisicao(bemDTO.getDataAquisicao());
        bemExistente.setValorAquisicao(bemDTO.getValorAquisicao());
        bemExistente.setValorAtual(bemDTO.getValorAtual());
        bemExistente.setFornecedor(bemDTO.getFornecedor());
        bemExistente.setFabricante(bemDTO.getFabricante());
        bemExistente.setAnoFabricacao(bemDTO.getAnoFabricacao());
        bemExistente.setGarantiaAte(bemDTO.getGarantiaAte());
        bemExistente.setDataUltimaManutencao(bemDTO.getDataUltimaManutencao());
        bemExistente.setDataProximaManutencao(bemDTO.getDataProximaManutencao());
        bemExistente.setCentroCusto(bemDTO.getCentroCusto());
        bemExistente.setVidaUtilAnos(bemDTO.getVidaUtilAnos());
        bemExistente.setTaxaDepreciacao(bemDTO.getTaxaDepreciacao());
        bemExistente.setObservacoes(bemDTO.getObservacoes());
        bemExistente.setMarca(bemDTO.getModelo());
        bemExistente.setModelo(bemDTO.getModelo());
        bemExistente.setResponsavel(bemDTO.getResponsavelAtual());
        bemExistente.setDepartamento(bemDTO.getDepartamentoResponsavel());
        
        Bem bemAtualizado = bemRepository.save(bemExistente);
        log.info("Bem atualizado com sucesso. ID: {}", bemAtualizado.getId());
        
        return patrimonioMapper.bemToBemDTO(bemAtualizado);
    }

    /**
     * Exclui um bem.
     *
     * @param id ID do bem
     * @throws RecursoNaoEncontradoException se o bem não for encontrado
     * @throws RegraDeNegocioException se o bem não puder ser excluído
     */
    @CacheEvict(value = "bens", allEntries = true)
    public void excluir(Long id) {
        log.info("Excluindo bem com ID: {}", id);
        
        Bem bem = bemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    String.format(BEM_NAO_ENCONTRADO, id)));
        
        if (!bem.podeSerBaixado()) {
            throw new RegraDeNegocioException("Bem não pode ser excluído no status atual");
        }
        
        bemRepository.delete(bem);
        log.info("Bem excluído com sucesso. ID: {}", id);
    }

    /**
     * Ativa um bem inativo.
     *
     * @param id ID do bem
     * @return Bem ativado
     * @throws RecursoNaoEncontradoException se o bem não for encontrado
     */
    @CacheEvict(value = "bens", allEntries = true)
    public BemDTO ativar(Long id) {
        log.info("Ativando bem com ID: {}", id);
        
        Bem bem = bemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    String.format(BEM_NAO_ENCONTRADO, id)));
        
        bem.setAtivo(true);
        bem.setStatus(StatusBem.ATIVO);
        
        Bem bemAtivado = bemRepository.save(bem);
        log.info("Bem ativado com sucesso. ID: {}", bemAtivado.getId());
        
        return patrimonioMapper.bemToBemDTO(bemAtivado);
    }

    /**
     * Desativa um bem ativo.
     *
     * @param id ID do bem
     * @return Bem desativado
     * @throws RecursoNaoEncontradoException se o bem não for encontrado
     */
    @CacheEvict(value = "bens", allEntries = true)
    public BemDTO desativar(Long id) {
        log.info("Desativando bem com ID: {}", id);
        
        Bem bem = bemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    String.format(BEM_NAO_ENCONTRADO, id)));
        
        bem.setAtivo(false);
        bem.setStatus(StatusBem.INATIVO);
        
        Bem bemDesativado = bemRepository.save(bem);
        log.info("Bem desativado com sucesso. ID: {}", bemDesativado.getId());
        
        return patrimonioMapper.bemToBemDTO(bemDesativado);
    }

    /**
     * Altera o status de um bem.
     *
     * @param id ID do bem
     * @param novoStatus Novo status
     * @return Bem com status alterado
     * @throws RecursoNaoEncontradoException se o bem não for encontrado
     */
    @CacheEvict(value = "bens", allEntries = true)
    public BemDTO alterarStatus(Long id, StatusBem novoStatus) {
        log.info("Alterando status do bem {} para: {}", id, novoStatus);
        
        Bem bem = bemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    String.format(BEM_NAO_ENCONTRADO, id)));
        
        bem.setStatus(novoStatus);
        
        Bem bemAtualizado = bemRepository.save(bem);
        log.info("Status do bem alterado com sucesso. ID: {}, Novo status: {}", 
                bemAtualizado.getId(), novoStatus);
        
        return patrimonioMapper.bemToBemDTO(bemAtualizado);
    }

    /**
     * Altera a condição de um bem.
     *
     * @param id ID do bem
     * @param novaCondicao Nova condição
     * @return Bem com condição alterada
     * @throws RecursoNaoEncontradoException se o bem não for encontrado
     */
    @CacheEvict(value = "bens", allEntries = true)
    public BemDTO alterarCondicao(Long id, CondicaoBem novaCondicao) {
        log.info("Alterando condição do bem {} para: {}", id, novaCondicao);
        
        Bem bem = bemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    String.format(BEM_NAO_ENCONTRADO, id)));
        
        bem.setCondicao(novaCondicao);
        
        Bem bemAtualizado = bemRepository.save(bem);
        log.info("Condição do bem alterada com sucesso. ID: {}, Nova condição: {}", 
                bemAtualizado.getId(), novaCondicao);
        
        return patrimonioMapper.bemToBemDTO(bemAtualizado);
    }

    // Métodos de busca
    public Page<BemDTO> buscarPorCategoria(Long categoriaId, Pageable pageable) {
        log.debug("Buscando bens por categoria: {}", categoriaId);
        
        Page<Bem> bens = bemRepository.findByCategoriaId(categoriaId, pageable);
        return bens.map(patrimonioMapper::bemToBemDTO);
    }

    public Page<BemDTO> buscarPorLocalizacao(Long localizacaoId, Pageable pageable) {
        log.debug("Buscando bens por localização: {}", localizacaoId);
        
        Page<Bem> bens = bemRepository.findByLocalizacaoAtualId(localizacaoId, pageable);
        return bens.map(patrimonioMapper::bemToBemDTO);
    }

    public Page<BemDTO> buscarPorStatus(StatusBem status, Pageable pageable) {
        log.debug("Buscando bens por status: {}", status);
        
        Page<Bem> bens = bemRepository.findByStatus(status, pageable);
        return bens.map(patrimonioMapper::bemToBemDTO);
    }

    public Page<BemDTO> buscarPorCondicao(CondicaoBem condicao, Pageable pageable) {
        log.debug("Buscando bens por condição: {}", condicao);
        
        Page<Bem> bens = bemRepository.findByCondicao(condicao, pageable);
        return bens.map(patrimonioMapper::bemToBemDTO);
    }

    public Page<BemDTO> buscarPorValorAquisicao(BigDecimal valorMinimo, BigDecimal valorMaximo, Pageable pageable) {
        log.debug("Buscando bens por valor de aquisição entre {} e {}", valorMinimo, valorMaximo);
        
        Page<Bem> bens = bemRepository.findByValorAquisicaoBetween(valorMinimo, valorMaximo, pageable);
        return bens.map(patrimonioMapper::bemToBemDTO);
    }

    public Page<BemDTO> buscarPorDataAquisicao(LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        log.debug("Buscando bens por data de aquisição entre {} e {}", dataInicio, dataFim);
        
        Page<Bem> bens = bemRepository.findByDataAquisicaoBetween(dataInicio, dataFim, pageable);
        return bens.map(patrimonioMapper::bemToBemDTO);
    }

    public Page<BemDTO> buscarPorTexto(String termo, Pageable pageable) {
        log.debug("Buscando bens por texto: {}", termo);
        
        Page<Bem> bens = bemRepository.buscarPorTexto(termo, pageable);
        return bens.map(patrimonioMapper::bemToBemDTO);
    }

    /**
     * Busca bens com múltiplos filtros.
     *
     * @param categoriaId ID da categoria (opcional)
     * @param localizacaoId ID da localização (opcional)
     * @param status Status do bem (opcional)
     * @param condicao Condição do bem (opcional)
     * @param valorMinimo Valor mínimo de aquisição (opcional)
     * @param valorMaximo Valor máximo de aquisição (opcional)
     * @param dataInicio Data de início de aquisição (opcional)
     * @param dataFim Data de fim de aquisição (opcional)
     * @param pageable Configuração de paginação
     * @return Página de bens filtrados
     */
    public Page<BemDTO> buscarComFiltros(Long categoriaId, Long localizacaoId, StatusBem status,
                                        CondicaoBem condicao, BigDecimal valorMinimo, BigDecimal valorMaximo,
                                        LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        log.debug("Buscando bens com filtros: categoriaId={}, localizacaoId={}, status={}, condicao={}, " +
                 "valorMinimo={}, valorMaximo={}, dataInicio={}, dataFim={}", 
                 categoriaId, localizacaoId, status, condicao, valorMinimo, valorMaximo, dataInicio, dataFim);
        
        Page<Bem> bens = bemRepository.buscarComFiltros(categoriaId, localizacaoId, status, condicao,
                                                       valorMinimo, valorMaximo, dataInicio, dataFim, pageable);
        return bens.map(patrimonioMapper::bemToBemDTO);
    }

    /**
     * Busca bens por faixa de valor.
     *
     * @param valorMinimo Valor mínimo
     * @param valorMaximo Valor máximo
     * @param pageable Configuração de paginação
     * @return Página de bens
     */
    public Page<BemDTO> buscarPorFaixaValor(BigDecimal valorMinimo, BigDecimal valorMaximo, Pageable pageable) {
        log.debug("Buscando bens por faixa de valor entre {} e {}", valorMinimo, valorMaximo);
        
        Page<Bem> bens = bemRepository.findByValorAquisicaoBetween(valorMinimo, valorMaximo, pageable);
        return bens.map(patrimonioMapper::bemToBemDTO);
    }

    /**
     * Busca bens por faixa de data.
     *
     * @param dataInicio Data de início
     * @param dataFim Data de fim
     * @param pageable Configuração de paginação
     * @return Página de bens
     */
    public Page<BemDTO> buscarPorFaixaData(LocalDate dataInicio, LocalDate dataFim, Pageable pageable) {
        log.debug("Buscando bens por faixa de data entre {} e {}", dataInicio, dataFim);
        
        Page<Bem> bens = bemRepository.findByDataAquisicaoBetween(dataInicio, dataFim, pageable);
        return bens.map(patrimonioMapper::bemToBemDTO);
    }

    /**
     * Atualiza o status de um bem.
     *
     * @param id ID do bem
     * @param status Novo status
     * @return Bem com status atualizado
     * @throws RecursoNaoEncontradoException se o bem não for encontrado
     */
    @CacheEvict(value = "bens", allEntries = true)
    public BemDTO atualizarStatus(Long id, StatusBem status) {
        log.info("Atualizando status do bem {} para: {}", id, status);
        
        Bem bem = bemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    String.format(BEM_NAO_ENCONTRADO, id)));
        
        bem.setStatus(status);
        
        Bem bemAtualizado = bemRepository.save(bem);
        log.info("Status do bem atualizado com sucesso. ID: {}, Novo status: {}", 
                bemAtualizado.getId(), status);
        
        return patrimonioMapper.bemToBemDTO(bemAtualizado);
    }

    /**
     * Conta bens por status.
     *
     * @return Lista de arrays com status e contagem
     */
    public List<Object[]> contarBensPorStatus() {
        log.debug("Contando bens por status");
        return bemRepository.contarBensPorStatus();
    }

    /**
     * Conta bens por condição.
     *
     * @return Lista de arrays com condição e contagem
     */
    public List<Object[]> contarBensPorCondicao() {
        log.debug("Contando bens por condição");
        return bemRepository.contarBensPorCondicao();
    }

    /**
     * Soma valor dos bens por status.
     *
     * @return Lista de arrays com status e soma de valores
     */
    public List<Object[]> somarValorPorStatus() {
        log.debug("Somando valor dos bens por status");
        return bemRepository.somarValorPorStatus();
    }

    /**
     * Busca os 10 bens mais recentemente adquiridos.
     *
     * @return Lista de bens recentes
     */
    public List<BemDTO> buscarBensRecentes() {
        log.debug("Buscando bens mais recentes");
        List<Bem> bens = bemRepository.findTop10ByOrderByDataAquisicaoDesc();
        return bens.stream().map(patrimonioMapper::bemToBemDTO).toList();
    }

    /**
     * Busca bens que nunca foram movimentados.
     *
     * @return Lista de bens sem movimentação
     */
    public List<BemDTO> buscarBensSemMovimentacao() {
        log.debug("Buscando bens sem movimentação");
        List<Bem> bens = bemRepository.buscarBensSemMovimentacao();
        return bens.stream().map(patrimonioMapper::bemToBemDTO).toList();
    }

    /**
     * Busca bens por categoria e status.
     *
     * @param categoriaId ID da categoria
     * @param status Status do bem
     * @return Lista de bens
     */
    public List<BemDTO> buscarPorCategoriaEStatus(Long categoriaId, StatusBem status) {
        log.debug("Buscando bens por categoria {} e status {}", categoriaId, status);
        List<Bem> bens = bemRepository.findByCategoriaIdAndStatus(categoriaId, status);
        return bens.stream().map(patrimonioMapper::bemToBemDTO).toList();
    }

    /**
     * Busca bens por localização e condição.
     *
     * @param localizacaoId ID da localização
     * @param condicao Condição do bem
     * @return Lista de bens
     */
    public List<BemDTO> buscarPorLocalizacaoECondicao(Long localizacaoId, CondicaoBem condicao) {
        log.debug("Buscando bens por localização {} e condição {}", localizacaoId, condicao);
        List<Bem> bens = bemRepository.findByLocalizacaoAtualIdAndCondicao(localizacaoId, condicao);
        return bens.stream().map(patrimonioMapper::bemToBemDTO).toList();
    }

    /**
     * Busca bens com valor acima de um limite.
     *
     * @param valorLimite Valor limite
     * @return Lista de bens
     */
    public List<BemDTO> buscarBensComValorAcimaDe(BigDecimal valorLimite) {
        log.debug("Buscando bens com valor acima de {}", valorLimite);
        List<Bem> bens = bemRepository.findByValorAquisicaoGreaterThan(valorLimite);
        return bens.stream().map(patrimonioMapper::bemToBemDTO).toList();
    }


    // Métodos auxiliares privados
    private void validarBem(BemDTO bemDTO) {
        if (bemDTO.getDataAquisicao() != null && bemDTO.getDataAquisicao().isAfter(LocalDate.now())) {
            throw new RegraDeNegocioException("Data de aquisição não pode ser futura");
        }
        
        if (bemDTO.getValorAquisicao() != null && bemDTO.getValorAquisicao().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor de aquisição deve ser maior que zero");
        }
        
        if (bemDTO.getValorAtual() != null && bemDTO.getValorAtual().compareTo(BigDecimal.ZERO) < 0) {
            throw new RegraDeNegocioException("Valor atual não pode ser negativo");
        }
        
        if (bemDTO.getVidaUtilAnos() != null && (bemDTO.getVidaUtilAnos() < 1 || bemDTO.getVidaUtilAnos() > 100)) {
            throw new RegraDeNegocioException("Vida útil deve estar entre 1 e 100 anos");
        }
        
        if (bemDTO.getTaxaDepreciacao() != null && 
            (bemDTO.getTaxaDepreciacao().compareTo(BigDecimal.ZERO) < 0 || 
             bemDTO.getTaxaDepreciacao().compareTo(BigDecimal.valueOf(100)) > 0)) {
            throw new RegraDeNegocioException("Taxa de depreciação deve estar entre 0% e 100%");
        }
    }

    private void verificarNumeroSerieUnico(String numeroSerie) {
        if (numeroSerie != null && !numeroSerie.trim().isEmpty() && bemRepository.existsByNumeroSerie(numeroSerie)) {
            throw new RegraDeNegocioException("Número de série já existe: " + numeroSerie);
        }
    }

    private Categoria buscarCategoria(Long categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    String.format(CATEGORIA_NAO_ENCONTRADA, categoriaId)));
    }

    private Localizacao buscarLocalizacao(Long localizacaoId) {
        return localizacaoRepository.findById(localizacaoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                    String.format(LOCALIZACAO_NAO_ENCONTRADA, localizacaoId)));
    }
}

