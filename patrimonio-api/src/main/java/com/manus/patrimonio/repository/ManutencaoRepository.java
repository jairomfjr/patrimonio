package com.manus.patrimonio.repository;

import com.manus.patrimonio.enums.StatusManutencao;
import com.manus.patrimonio.enums.TipoManutencao;
import com.manus.patrimonio.model.Manutencao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

    // Buscar por bem
    List<Manutencao> findByBemIdOrderByDataInicioDesc(Long bemId);
    
    // Buscar por status
    List<Manutencao> findByStatus(StatusManutencao status);
    
    // Buscar por tipo
    List<Manutencao> findByTipoManutencao(TipoManutencao tipoManutencao);
    
    // Buscar por responsável
    List<Manutencao> findByResponsavelContainingIgnoreCaseOrderByDataInicioDesc(String responsavel);
    
    // Buscar por fornecedor
    List<Manutencao> findByFornecedorContainingIgnoreCaseOrderByDataInicioDesc(String fornecedor);
    
    // Buscar manutenções ativas
    @Query("SELECT m FROM Manutencao m WHERE m.status IN ('AGENDADA', 'EM_ANDAMENTO', 'PAUSADA', 'AGUARDANDO_PECAS', 'AGUARDANDO_APROVACAO', 'EM_ANALISE', 'EM_TESTE')")
    List<Manutencao> findManutencoesAtivas();
    
    // Buscar manutenções finalizadas
    @Query("SELECT m FROM Manutencao m WHERE m.status IN ('CONCLUIDA', 'CANCELADA', 'REPROVADA')")
    List<Manutencao> findManutencoesFinalizadas();
    
    // Buscar por período
    List<Manutencao> findByDataInicioBetweenOrderByDataInicioDesc(LocalDate dataInicio, LocalDate dataFim);
    
    // Buscar manutenções agendadas para uma data
    List<Manutencao> findByDataAgendamentoBetweenOrderByDataAgendamento(LocalDate dataInicio, LocalDate dataFim);
    
    // Buscar por prioridade
    List<Manutencao> findByPrioridadeGreaterThanEqualOrderByPrioridadeDesc(Integer prioridade);
    
    // Buscar manutenções em andamento por bem
    @Query("SELECT m FROM Manutencao m WHERE m.bem.id = :bemId AND m.status = 'EM_ANDAMENTO'")
    List<Manutencao> findManutencoesEmAndamentoPorBem(@Param("bemId") Long bemId);
    
    // Buscar manutenções agendadas por bem
    @Query("SELECT m FROM Manutencao m WHERE m.bem.id = :bemId AND m.status = 'AGENDADA' ORDER BY m.dataAgendamento")
    List<Manutencao> findManutencoesAgendadasPorBem(@Param("bemId") Long bemId);
    
    // Buscar manutenções preventivas
    @Query("SELECT m FROM Manutencao m WHERE m.tipoManutencao IN ('PREVENTIVA', 'PREDICTIVA', 'CALIBRACAO', 'LIMPEZA', 'INSPECAO')")
    List<Manutencao> findManutencoesPreventivas();
    
    // Buscar manutenções corretivas
    @Query("SELECT m FROM Manutencao m WHERE m.tipoManutencao IN ('CORRETIVA', 'EMERGENCIA', 'REPARO', 'SUBSTITUICAO')")
    List<Manutencao> findManutencoesCorretivas();
    
    // Buscar manutenções por custo
    @Query("SELECT m FROM Manutencao m WHERE m.custo >= :custoMinimo ORDER BY m.custo DESC")
    List<Manutencao> findManutencoesPorCustoMinimo(@Param("custoMinimo") Double custoMinimo);
    
    // Buscar manutenções urgentes (prioridade alta)
    @Query("SELECT m FROM Manutencao m WHERE m.prioridade >= 8 ORDER BY m.prioridade DESC, m.dataInicio")
    List<Manutencao> findManutencoesUrgentes();
    
    // Buscar manutenções atrasadas
    @Query("SELECT m FROM Manutencao m WHERE m.dataFim IS NULL AND m.dataInicio < :dataLimite AND m.status IN ('EM_ANDAMENTO', 'AGENDADA')")
    List<Manutencao> findManutencoesAtrasadas(@Param("dataLimite") LocalDate dataLimite);
    
    // Buscar manutenções por categoria de bem
    @Query("SELECT m FROM Manutencao m JOIN m.bem b JOIN b.categoria c WHERE c.id = :categoriaId")
    List<Manutencao> findManutencoesPorCategoriaBem(@Param("categoriaId") Long categoriaId);
    
    // Buscar manutenções por localização
    @Query("SELECT m FROM Manutencao m JOIN m.bem b JOIN b.localizacaoAtual l WHERE l.id = :localizacaoId")
    List<Manutencao> findManutencoesPorLocalizacao(@Param("localizacaoId") Long localizacaoId);
    
    // Contar manutenções por status
    @Query("SELECT COUNT(m) FROM Manutencao m WHERE m.status = :status")
    Long countByStatus(@Param("status") StatusManutencao status);
    
    // Contar manutenções por tipo
    @Query("SELECT COUNT(m) FROM Manutencao m WHERE m.tipoManutencao = :tipo")
    Long countByTipoManutencao(@Param("tipo") TipoManutencao tipo);
    
    // Contar manutenções por bem
    @Query("SELECT COUNT(m) FROM Manutencao m WHERE m.bem.id = :bemId")
    Long countByBemId(@Param("bemId") Long bemId);
    
    // Buscar com paginação
    Page<Manutencao> findAll(Pageable pageable);
    
    // Buscar por status com paginação
    Page<Manutencao> findByStatus(StatusManutencao status, Pageable pageable);
    
    // Buscar por tipo com paginação
    Page<Manutencao> findByTipoManutencao(TipoManutencao tipoManutencao, Pageable pageable);
    
    // Buscar por responsável com paginação
    Page<Manutencao> findByResponsavelContainingIgnoreCase(String responsavel, Pageable pageable);
    
    // Buscar por fornecedor com paginação
    Page<Manutencao> findByFornecedorContainingIgnoreCase(String fornecedor, Pageable pageable);
    
    // Buscar por período com paginação
    Page<Manutencao> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
    
    // Buscar manutenções ativas com paginação
    @Query("SELECT m FROM Manutencao m WHERE m.status IN ('AGENDADA', 'EM_ANDAMENTO', 'PAUSADA', 'AGUARDANDO_PECAS', 'AGUARDANDO_APROVACAO', 'EM_ANALISE', 'EM_TESTE')")
    Page<Manutencao> findManutencoesAtivas(Pageable pageable);
    
    // Buscar manutenções finalizadas com paginação
    @Query("SELECT m FROM Manutencao m WHERE m.status IN ('CONCLUIDA', 'CANCELADA', 'REPROVADA')")
    Page<Manutencao> findManutencoesFinalizadas(Pageable pageable);
    
    // Buscar manutenções urgentes com paginação
    @Query("SELECT m FROM Manutencao m WHERE m.prioridade >= 8 ORDER BY m.prioridade DESC, m.dataInicio")
    Page<Manutencao> findManutencoesUrgentes(Pageable pageable);
    
    // Buscar manutenções por categoria de bem com paginação
    @Query("SELECT m FROM Manutencao m JOIN m.bem b JOIN b.categoria c WHERE c.id = :categoriaId")
    Page<Manutencao> findManutencoesPorCategoriaBem(@Param("categoriaId") Long categoriaId, Pageable pageable);
    
    // Buscar manutenções por localização com paginação
    @Query("SELECT m FROM Manutencao m JOIN m.bem b JOIN b.localizacaoAtual l WHERE l.id = :localizacaoId")
    Page<Manutencao> findManutencoesPorLocalizacao(@Param("localizacaoId") Long localizacaoId, Pageable pageable);
}
