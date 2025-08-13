package com.manus.patrimonio.repository;

import com.manus.patrimonio.model.Auditoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    // Buscar por entidade
    List<Auditoria> findByEntidadeOrderByDataAcaoDesc(String entidade);
    
    // Buscar por entidade e ID
    List<Auditoria> findByEntidadeAndEntidadeIdOrderByDataAcaoDesc(String entidade, Long entidadeId);
    
    // Buscar por ação
    List<Auditoria> findByAcaoOrderByDataAcaoDesc(String acao);
    
    // Buscar por usuário
    List<Auditoria> findByUsuarioIdOrderByDataAcaoDesc(Long usuarioId);
    
    // Buscar por período
    List<Auditoria> findByDataAcaoBetweenOrderByDataAcaoDesc(LocalDateTime dataInicio, LocalDateTime dataFim);
    
    // Buscar por entidade e período
    @Query("SELECT a FROM Auditoria a WHERE a.entidade = :entidade AND a.dataAcao BETWEEN :dataInicio AND :dataFim ORDER BY a.dataAcao DESC")
    List<Auditoria> findByEntidadeAndPeriodo(@Param("entidade") String entidade, 
                                            @Param("dataInicio") LocalDateTime dataInicio, 
                                            @Param("dataFim") LocalDateTime dataFim);
    
    // Buscar por usuário e período
    @Query("SELECT a FROM Auditoria a WHERE a.usuario.id = :usuarioId AND a.dataAcao BETWEEN :dataInicio AND :dataFim ORDER BY a.dataAcao DESC")
    List<Auditoria> findByUsuarioAndPeriodo(@Param("usuarioId") Long usuarioId, 
                                           @Param("dataInicio") LocalDateTime dataInicio, 
                                           @Param("dataFim") LocalDateTime dataFim);
    
    // Buscar por ação e período
    @Query("SELECT a FROM Auditoria a WHERE a.acao = :acao AND a.dataAcao BETWEEN :dataInicio AND :dataFim ORDER BY a.dataAcao DESC")
    List<Auditoria> findByAcaoAndPeriodo(@Param("acao") String acao, 
                                        @Param("dataInicio") LocalDateTime dataInicio, 
                                        @Param("dataFim") LocalDateTime dataFim);
    
    // Buscar por IP
    List<Auditoria> findByIpAddressOrderByDataAcaoDesc(String ipAddress);
    
    // Buscar por entidade e ação
    @Query("SELECT a FROM Auditoria a WHERE a.entidade = :entidade AND a.acao = :acao ORDER BY a.dataAcao DESC")
    List<Auditoria> findByEntidadeAndAcao(@Param("entidade") String entidade, @Param("acao") String acao);
    
    // Buscar por usuário e ação
    @Query("SELECT a FROM Auditoria a WHERE a.usuario.id = :usuarioId AND a.acao = :acao ORDER BY a.dataAcao DESC")
    List<Auditoria> findByUsuarioAndAcao(@Param("usuarioId") Long usuarioId, @Param("acao") String acao);
    
    // Buscar auditorias de criação
    @Query("SELECT a FROM Auditoria a WHERE a.acao = 'CREATE' ORDER BY a.dataAcao DESC")
    List<Auditoria> findAuditoriasCriacao();
    
    // Buscar auditorias de atualização
    @Query("SELECT a FROM Auditoria a WHERE a.acao = 'UPDATE' ORDER BY a.dataAcao DESC")
    List<Auditoria> findAuditoriasAtualizacao();
    
    // Buscar auditorias de exclusão
    @Query("SELECT a FROM Auditoria a WHERE a.acao = 'DELETE' ORDER BY a.dataAcao DESC")
    List<Auditoria> findAuditoriasExclusao();
    
    // Buscar auditorias de consulta
    @Query("SELECT a FROM Auditoria a WHERE a.acao = 'READ' ORDER BY a.dataAcao DESC")
    List<Auditoria> findAuditoriasConsulta();
    
    // Contar por entidade
    @Query("SELECT COUNT(a) FROM Auditoria a WHERE a.entidade = :entidade")
    Long countByEntidade(@Param("entidade") String entidade);
    
    // Contar por ação
    @Query("SELECT COUNT(a) FROM Auditoria a WHERE a.acao = :acao")
    Long countByAcao(@Param("acao") String acao);
    
    // Contar por usuário
    @Query("SELECT COUNT(a) FROM Auditoria a WHERE a.usuario.id = :usuarioId")
    Long countByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    // Contar por período
    @Query("SELECT COUNT(a) FROM Auditoria a WHERE a.dataAcao BETWEEN :dataInicio AND :dataFim")
    Long countByPeriodo(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
    
    // Contar por entidade e período
    @Query("SELECT COUNT(a) FROM Auditoria a WHERE a.entidade = :entidade AND a.dataAcao BETWEEN :dataInicio AND :dataFim")
    Long countByEntidadeAndPeriodo(@Param("entidade") String entidade, 
                                  @Param("dataInicio") LocalDateTime dataInicio, 
                                  @Param("dataFim") LocalDateTime dataFim);
    
    // Buscar com paginação
    Page<Auditoria> findAll(Pageable pageable);
    
    // Buscar por entidade com paginação
    Page<Auditoria> findByEntidade(String entidade, Pageable pageable);
    
    // Buscar por ação com paginação
    Page<Auditoria> findByAcao(String acao, Pageable pageable);
    
    // Buscar por usuário com paginação
    Page<Auditoria> findByUsuarioId(Long usuarioId, Pageable pageable);
    
    // Buscar por período com paginação
    Page<Auditoria> findByDataAcaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);
    
    // Buscar por entidade e ID com paginação
    Page<Auditoria> findByEntidadeAndEntidadeId(String entidade, Long entidadeId, Pageable pageable);
    
    // Buscar por entidade e ação com paginação
    Page<Auditoria> findByEntidadeAndAcao(String entidade, String acao, Pageable pageable);
    
    // Buscar por usuário e ação com paginação
    Page<Auditoria> findByUsuarioIdAndAcao(Long usuarioId, String acao, Pageable pageable);
}
