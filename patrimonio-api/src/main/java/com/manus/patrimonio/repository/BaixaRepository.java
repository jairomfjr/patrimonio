package com.manus.patrimonio.repository;

import com.manus.patrimonio.model.Baixa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BaixaRepository extends JpaRepository<Baixa, Long> {

    // Buscar por bem
    List<Baixa> findByBemIdOrderByDataBaixaDesc(Long bemId);
    
    // Buscar por motivo
    List<Baixa> findByMotivoContainingIgnoreCaseOrderByDataBaixaDesc(String motivo);
    
    // Buscar por responsável
    List<Baixa> findByResponsavelContainingIgnoreCaseOrderByDataBaixaDesc(String responsavel);
    
    // Buscar por período
    List<Baixa> findByDataBaixaBetweenOrderByDataBaixaDesc(LocalDate dataInicio, LocalDate dataFim);
    
    // Buscar por valor residual
    List<Baixa> findByValorResidualGreaterThanEqualOrderByValorResidualDesc(BigDecimal valorMinimo);
    
    // Buscar por valor de venda
    List<Baixa> findByValorVendaGreaterThanEqualOrderByValorVendaDesc(BigDecimal valorMinimo);
    
    // Buscar baixas aprovadas
    @Query("SELECT b FROM Baixa b WHERE b.dataAprovacao IS NOT NULL")
    List<Baixa> findBaixasAprovadas();
    
    // Buscar baixas não aprovadas
    @Query("SELECT b FROM Baixa b WHERE b.dataAprovacao IS NULL")
    List<Baixa> findBaixasNaoAprovadas();
    
    // Buscar baixas vendidas
    @Query("SELECT b FROM Baixa b WHERE b.valorVenda IS NOT NULL AND b.dataVenda IS NOT NULL")
    List<Baixa> findBaixasVendidas();
    
    // Buscar baixas não vendidas
    @Query("SELECT b FROM Baixa b WHERE b.valorVenda IS NULL OR b.dataVenda IS NULL")
    List<Baixa> findBaixasNaoVendidas();
    
    // Buscar por processo administrativo
    List<Baixa> findByProcessoAdministrativoContainingIgnoreCaseOrderByDataBaixaDesc(String processoAdministrativo);
    
    // Buscar por comprador
    List<Baixa> findByCompradorContainingIgnoreCaseOrderByDataVendaDesc(String comprador);
    
    // Buscar baixas por categoria de bem
    @Query("SELECT b FROM Baixa b JOIN b.bem bem JOIN bem.categoria c WHERE c.id = :categoriaId")
    List<Baixa> findBaixasPorCategoriaBem(@Param("categoriaId") Long categoriaId);
    
    // Buscar baixas por localização
    @Query("SELECT b FROM Baixa b JOIN b.bem bem JOIN bem.localizacaoAtual l WHERE l.id = :localizacaoId")
    List<Baixa> findBaixasPorLocalizacao(@Param("localizacaoId") Long localizacaoId);
    
    // Buscar baixas por valor de aquisição
    @Query("SELECT b FROM Baixa b JOIN b.bem bem WHERE bem.valorAquisicao >= :valorMinimo ORDER BY bem.valorAquisicao DESC")
    List<Baixa> findBaixasPorValorAquisicaoMinimo(@Param("valorMinimo") BigDecimal valorMinimo);
    
    // Buscar baixas por perda financeira
    @Query("SELECT b FROM Baixa b WHERE (b.bem.valorAquisicao - b.valorResidual) >= :perdaMinima ORDER BY (b.bem.valorAquisicao - b.valorResidual) DESC")
    List<Baixa> findBaixasPorPerdaFinanceiraMinima(@Param("perdaMinima") BigDecimal perdaMinima);
    
    // Buscar baixas por ganho financeiro
    @Query("SELECT b FROM Baixa b WHERE (b.valorVenda - b.valorResidual) >= :ganhoMinimo ORDER BY (b.valorVenda - b.valorResidual) DESC")
    List<Baixa> findBaixasPorGanhoFinanceiroMinimo(@Param("ganhoMinimo") BigDecimal ganhoMinimo);
    
    // Contar baixas por status de aprovação
    @Query("SELECT COUNT(b) FROM Baixa b WHERE b.dataAprovacao IS NOT NULL")
    Long countBaixasAprovadas();
    
    @Query("SELECT COUNT(b) FROM Baixa b WHERE b.dataAprovacao IS NULL")
    Long countBaixasNaoAprovadas();
    
    // Contar baixas por status de venda
    @Query("SELECT COUNT(b) FROM Baixa b WHERE b.valorVenda IS NOT NULL AND b.dataVenda IS NOT NULL")
    Long countBaixasVendidas();
    
    @Query("SELECT COUNT(b) FROM Baixa b WHERE b.valorVenda IS NULL OR b.dataVenda IS NULL")
    Long countBaixasNaoVendidas();
    
    // Contar baixas por bem
    @Query("SELECT COUNT(b) FROM Baixa b WHERE b.bem.id = :bemId")
    Long countByBemId(@Param("bemId") Long bemId);
    
    // Buscar com paginação
    Page<Baixa> findAll(Pageable pageable);
    
    // Buscar por motivo com paginação
    Page<Baixa> findByMotivoContainingIgnoreCase(String motivo, Pageable pageable);
    
    // Buscar por responsável com paginação
    Page<Baixa> findByResponsavelContainingIgnoreCase(String responsavel, Pageable pageable);
    
    // Buscar por período com paginação
    Page<Baixa> findByDataBaixaBetween(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
    
    // Buscar por valor residual com paginação
    Page<Baixa> findByValorResidualGreaterThanEqual(BigDecimal valorMinimo, Pageable pageable);
    
    // Buscar por valor de venda com paginação
    Page<Baixa> findByValorVendaGreaterThanEqual(BigDecimal valorMinimo, Pageable pageable);
    
    // Buscar baixas aprovadas com paginação
    @Query("SELECT b FROM Baixa b WHERE b.dataAprovacao IS NOT NULL")
    Page<Baixa> findBaixasAprovadas(Pageable pageable);
    
    // Buscar baixas não aprovadas com paginação
    @Query("SELECT b FROM Baixa b WHERE b.dataAprovacao IS NULL")
    Page<Baixa> findBaixasNaoAprovadas(Pageable pageable);
    
    // Buscar baixas vendidas com paginação
    @Query("SELECT b FROM Baixa b WHERE b.valorVenda IS NOT NULL AND b.dataVenda IS NOT NULL")
    Page<Baixa> findBaixasVendidas(Pageable pageable);
    
    // Buscar baixas não vendidas com paginação
    @Query("SELECT b FROM Baixa b WHERE b.valorVenda IS NULL OR b.dataVenda IS NULL")
    Page<Baixa> findBaixasNaoVendidas(Pageable pageable);
    
    // Buscar por processo administrativo com paginação
    Page<Baixa> findByProcessoAdministrativoContainingIgnoreCase(String processoAdministrativo, Pageable pageable);
    
    // Buscar por comprador com paginação
    Page<Baixa> findByCompradorContainingIgnoreCase(String comprador, Pageable pageable);
    
    // Buscar baixas por categoria de bem com paginação
    @Query("SELECT b FROM Baixa b JOIN b.bem bem JOIN bem.categoria c WHERE c.id = :categoriaId")
    Page<Baixa> findBaixasPorCategoriaBem(@Param("categoriaId") Long categoriaId, Pageable pageable);
    
    // Buscar baixas por localização com paginação
    @Query("SELECT b FROM Baixa b JOIN b.bem bem JOIN bem.localizacaoAtual l WHERE l.id = :localizacaoId")
    Page<Baixa> findBaixasPorLocalizacao(@Param("localizacaoId") Long localizacaoId, Pageable pageable);
    
    // Buscar baixas por valor de aquisição com paginação
    @Query("SELECT b FROM Baixa b JOIN b.bem bem WHERE bem.valorAquisicao >= :valorMinimo ORDER BY bem.valorAquisicao DESC")
    Page<Baixa> findBaixasPorValorAquisicaoMinimo(@Param("valorMinimo") BigDecimal valorMinimo, Pageable pageable);
    
    // Buscar baixas por perda financeira com paginação
    @Query("SELECT b FROM Baixa b WHERE (b.bem.valorAquisicao - b.valorResidual) >= :perdaMinima ORDER BY (b.bem.valorAquisicao - b.valorResidual) DESC")
    Page<Baixa> findBaixasPorPerdaFinanceiraMinima(@Param("perdaMinima") BigDecimal perdaMinima, Pageable pageable);
    
    // Buscar baixas por ganho financeiro com paginação
    @Query("SELECT b FROM Baixa b WHERE (b.valorVenda - b.valorResidual) >= :ganhoMinimo ORDER BY (b.valorVenda - b.valorResidual) DESC")
    Page<Baixa> findBaixasPorGanhoFinanceiroMinimo(@Param("ganhoMinimo") BigDecimal ganhoMinimo, Pageable pageable);
}
