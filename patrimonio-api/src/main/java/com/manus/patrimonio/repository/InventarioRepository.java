package com.manus.patrimonio.repository;

import com.manus.patrimonio.enums.StatusInventario;
import com.manus.patrimonio.model.Inventario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    /**
     * Busca inventário por nome (case insensitive)
     */
    Optional<Inventario> findByNomeIgnoreCase(String nome);

    /**
     * Verifica se existe inventário com o nome especificado
     */
    boolean existsByNomeIgnoreCase(String nome);

    /**
     * Busca inventários por status
     */
    Page<Inventario> findByStatus(StatusInventario status, Pageable pageable);

    /**
     * Busca inventários por responsável
     */
    Page<Inventario> findByResponsavelContainingIgnoreCase(String responsavel, Pageable pageable);

    /**
     * Busca inventários por período de início
     */
    Page<Inventario> findByDataInicioBetween(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);

    /**
     * Busca inventários por período de conclusão
     */
    Page<Inventario> findByDataFimBetween(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);

    /**
     * Busca inventários que contenham o texto no nome ou observações
     */
    @Query("SELECT i FROM Inventario i WHERE " +
           "LOWER(i.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(i.observacoes) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(i.responsavel) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Inventario> buscarPorTexto(@Param("termo") String termo, Pageable pageable);

    /**
     * Busca inventários com filtros múltiplos
     */
    @Query("SELECT i FROM Inventario i WHERE " +
           "(:status IS NULL OR i.status = :status) AND " +
           "(:responsavel IS NULL OR LOWER(i.responsavel) LIKE LOWER(CONCAT('%', :responsavel, '%'))) AND " +
           "(:dataInicio IS NULL OR i.dataInicio >= :dataInicio) AND " +
           "(:dataFim IS NULL OR i.dataFim <= :dataFim)")
    Page<Inventario> buscarComFiltros(
            @Param("status") StatusInventario status,
            @Param("responsavel") String responsavel,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            Pageable pageable);

    /**
     * Conta inventários por status
     */
    @Query("SELECT i.status, COUNT(i) FROM Inventario i GROUP BY i.status")
    List<Object[]> contarInventariosPorStatus();

    /**
     * Busca inventários ordenados por data de início (mais recentes primeiro)
     */
    List<Inventario> findTop10ByOrderByDataInicioDesc();

    /**
     * Busca inventários em andamento
     */
    List<Inventario> findByStatusOrderByDataInicioDesc(StatusInventario status);

    /**
     * Busca inventários concluídos em um período
     */
    @Query("SELECT i FROM Inventario i WHERE i.status = 'CONCLUIDO' AND i.dataFim BETWEEN :dataInicio AND :dataFim")
    List<Inventario> buscarInventariosConcluidos(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    /**
     * Conta bens por inventário
     */
    @Query("SELECT i.nome, SIZE(i.bensInventariados) FROM Inventario i")
    List<Object[]> contarBensPorInventario();

    /**
     * Busca inventários que contêm um bem específico
     */
    @Query("SELECT i FROM Inventario i JOIN i.bensInventariados b WHERE b.id = :bemId")
    List<Inventario> buscarInventariosComBem(@Param("bemId") Long bemId);

    /**
     * Busca inventários por responsável e status
     */
    List<Inventario> findByResponsavelContainingIgnoreCaseAndStatus(String responsavel, StatusInventario status);

    /**
     * Busca inventários ativos (em andamento) por responsável
     */
    @Query("SELECT i FROM Inventario i WHERE i.status = 'EM_ANDAMENTO' AND LOWER(i.responsavel) LIKE LOWER(CONCAT('%', :responsavel, '%'))")
    List<Inventario> buscarInventariosAtivos(@Param("responsavel") String responsavel);
}

