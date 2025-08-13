package com.manus.patrimonio.repository;

import com.manus.patrimonio.enums.CondicaoBem;
import com.manus.patrimonio.enums.StatusBem;
import com.manus.patrimonio.model.Bem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BemRepository extends JpaRepository<Bem, Long> {

    /**
     * Busca bem por número de série
     */
    Optional<Bem> findByNumeroSerie(String numeroSerie);

    /**
     * Verifica se existe bem com o número de série especificado
     */
    boolean existsByNumeroSerie(String numeroSerie);

    /**
     * Busca bens por status
     */
    Page<Bem> findByStatus(StatusBem status, Pageable pageable);

    /**
     * Busca bens por condição
     */
    Page<Bem> findByCondicao(CondicaoBem condicao, Pageable pageable);

    /**
     * Busca bens por categoria
     */
    Page<Bem> findByCategoriaId(Long categoriaId, Pageable pageable);

    /**
     * Busca bens por localização atual
     */
    Page<Bem> findByLocalizacaoAtualId(Long localizacaoId, Pageable pageable);

    /**
     * Busca bens que contenham o texto no nome, descrição ou número de série
     */
    @Query("SELECT b FROM Bem b WHERE " +
           "LOWER(b.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(b.descricao) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(b.numeroSerie) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(b.observacoes) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Bem> buscarPorTexto(@Param("termo") String termo, Pageable pageable);

    /**
     * Busca bens por faixa de valor
     */
    Page<Bem> findByValorAquisicaoBetween(BigDecimal valorMinimo, BigDecimal valorMaximo, Pageable pageable);

    /**
     * Busca bens por faixa de data de aquisição
     */
    Page<Bem> findByDataAquisicaoBetween(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);

    /**
     * Busca bens com filtros múltiplos
     */
    @Query("SELECT b FROM Bem b WHERE " +
           "(:categoriaId IS NULL OR b.categoria.id = :categoriaId) AND " +
           "(:localizacaoId IS NULL OR b.localizacaoAtual.id = :localizacaoId) AND " +
           "(:status IS NULL OR b.status = :status) AND " +
           "(:condicao IS NULL OR b.condicao = :condicao) AND " +
           "(:valorMinimo IS NULL OR b.valorAquisicao >= :valorMinimo) AND " +
           "(:valorMaximo IS NULL OR b.valorAquisicao <= :valorMaximo) AND " +
           "(:dataInicio IS NULL OR b.dataAquisicao >= :dataInicio) AND " +
           "(:dataFim IS NULL OR b.dataAquisicao <= :dataFim)")
    Page<Bem> buscarComFiltros(
            @Param("categoriaId") Long categoriaId,
            @Param("localizacaoId") Long localizacaoId,
            @Param("status") StatusBem status,
            @Param("condicao") CondicaoBem condicao,
            @Param("valorMinimo") BigDecimal valorMinimo,
            @Param("valorMaximo") BigDecimal valorMaximo,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            Pageable pageable);

    /**
     * Conta bens por status
     */
    @Query("SELECT b.status, COUNT(b) FROM Bem b GROUP BY b.status")
    List<Object[]> contarBensPorStatus();

    /**
     * Conta bens por condição
     */
    @Query("SELECT b.condicao, COUNT(b) FROM Bem b GROUP BY b.condicao")
    List<Object[]> contarBensPorCondicao();

    /**
     * Calcula valor total dos bens por status
     */
    @Query("SELECT b.status, SUM(b.valorAquisicao) FROM Bem b GROUP BY b.status")
    List<Object[]> somarValorPorStatus();

    /**
     * Busca bens ordenados por data de aquisição (mais recentes primeiro)
     */
    List<Bem> findTop10ByOrderByDataAquisicaoDesc();

    /**
     * Busca bens com valor acima de um limite
     */
    List<Bem> findByValorAquisicaoGreaterThan(BigDecimal valor);

    /**
     * Busca bens que nunca foram movimentados
     */
    @Query("SELECT b FROM Bem b WHERE SIZE(b.movimentacoes) = 0")
    List<Bem> buscarBensSemMovimentacao();

    /**
     * Busca bens por categoria e status
     */
    List<Bem> findByCategoriaIdAndStatus(Long categoriaId, StatusBem status);

    /**
     * Busca bens por localização e condição
     */
    List<Bem> findByLocalizacaoAtualIdAndCondicao(Long localizacaoId, CondicaoBem condicao);
}

