package com.manus.patrimonio.repository;

import com.manus.patrimonio.enums.TipoMovimentacao;
import com.manus.patrimonio.model.Movimentacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    /**
     * Busca movimentações por bem
     */
    Page<Movimentacao> findByBemId(Long bemId, Pageable pageable);

    /**
     * Busca movimentações por bem ordenadas por data (mais recentes primeiro)
     */
    List<Movimentacao> findByBemIdOrderByDataMovimentacaoDesc(Long bemId);

    /**
     * Busca movimentações por tipo
     */
    Page<Movimentacao> findByTipoMovimentacao(TipoMovimentacao tipo, Pageable pageable);

    /**
     * Busca movimentações por localização de origem
     */
    Page<Movimentacao> findByLocalizacaoOrigemId(Long localizacaoId, Pageable pageable);

    /**
     * Busca movimentações por localização de destino
     */
    Page<Movimentacao> findByLocalizacaoDestinoId(Long localizacaoId, Pageable pageable);

    /**
     * Busca movimentações por responsável
     */
    Page<Movimentacao> findByResponsavelMovimentacaoContainingIgnoreCase(String responsavel, Pageable pageable);

    /**
     * Busca movimentações por período
     */
    Page<Movimentacao> findByDataMovimentacaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);

    /**
     * Busca movimentações com filtros múltiplos
     */
    @Query("SELECT m FROM Movimentacao m WHERE " +
           "(:bemId IS NULL OR m.bem.id = :bemId) AND " +
           "(:tipo IS NULL OR m.tipoMovimentacao = :tipo) AND " +
           "(:localizacaoOrigemId IS NULL OR m.localizacaoOrigem.id = :localizacaoOrigemId) AND " +
           "(:localizacaoDestinoId IS NULL OR m.localizacaoDestino.id = :localizacaoDestinoId) AND " +
           "(:dataInicio IS NULL OR m.dataMovimentacao >= :dataInicio) AND " +
           "(:dataFim IS NULL OR m.dataMovimentacao <= :dataFim) AND " +
           "(:responsavel IS NULL OR LOWER(m.responsavelMovimentacao) LIKE LOWER(CONCAT('%', :responsavel, '%')))")
    Page<Movimentacao> buscarComFiltros(
            @Param("bemId") Long bemId,
            @Param("tipo") TipoMovimentacao tipo,
            @Param("localizacaoOrigemId") Long localizacaoOrigemId,
            @Param("localizacaoDestinoId") Long localizacaoDestinoId,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim,
            @Param("responsavel") String responsavel,
            Pageable pageable);

    /**
     * Conta movimentações por tipo
     */
    @Query("SELECT m.tipoMovimentacao, COUNT(m) FROM Movimentacao m GROUP BY m.tipoMovimentacao")
    List<Object[]> contarMovimentacoesPorTipo();

    /**
     * Busca última movimentação de um bem
     */
    @Query("SELECT m FROM Movimentacao m WHERE m.bem.id = :bemId ORDER BY m.dataMovimentacao DESC")
    List<Movimentacao> buscarUltimaMovimentacao(@Param("bemId") Long bemId);

    /**
     * Busca movimentações recentes (últimas 24 horas)
     */
    @Query("SELECT m FROM Movimentacao m WHERE m.dataMovimentacao >= :dataLimite ORDER BY m.dataMovimentacao DESC")
    List<Movimentacao> buscarMovimentacaesRecentes(@Param("dataLimite") LocalDateTime dataLimite);

    /**
     * Conta movimentações por localização de destino
     */
    @Query("SELECT l.nome, COUNT(m) FROM Movimentacao m JOIN m.localizacaoDestino l GROUP BY l.id, l.nome")
    List<Object[]> contarMovimentacoesPorLocalizacaoDestino();

    /**
     * Busca movimentações por texto (observações ou responsável)
     */
    @Query("SELECT m FROM Movimentacao m WHERE " +
           "LOWER(m.observacoes) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(m.responsavelMovimentacao) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Movimentacao> buscarPorTexto(@Param("termo") String termo, Pageable pageable);
}

