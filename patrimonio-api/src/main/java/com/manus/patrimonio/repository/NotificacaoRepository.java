package com.manus.patrimonio.repository;

import com.manus.patrimonio.model.Notificacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    // Buscar por usuário
    List<Notificacao> findByUsuarioIdOrderByDataEnvioDesc(Long usuarioId);
    
    // Buscar por tipo
    List<Notificacao> findByTipoOrderByDataEnvioDesc(String tipo);
    
    // Buscar por status de leitura
    List<Notificacao> findByLidaOrderByDataEnvioDesc(Boolean lida);
    
    // Buscar por prioridade
    List<Notificacao> findByPrioridadeGreaterThanEqualOrderByPrioridadeDesc(Integer prioridadeMinima);
    
    // Buscar por categoria
    List<Notificacao> findByCategoriaContainingIgnoreCaseOrderByDataEnvioDesc(String categoria);
    
    // Buscar por entidade relacionada
    List<Notificacao> findByEntidadeRelacionadaContainingIgnoreCaseOrderByDataEnvioDesc(String entidadeRelacionada);
    
    // Buscar por período de envio
    List<Notificacao> findByDataEnvioBetweenOrderByDataEnvioDesc(LocalDateTime dataInicio, LocalDateTime dataFim);
    
    // Buscar por período de leitura
    List<Notificacao> findByDataLeituraBetweenOrderByDataLeituraDesc(LocalDateTime dataInicio, LocalDateTime dataFim);
    
    // Buscar notificações não lidas por usuário
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.lida = false ORDER BY n.dataEnvio DESC")
    List<Notificacao> findNotificacoesNaoLidasPorUsuario(@Param("usuarioId") Long usuarioId);
    
    // Buscar notificações lidas por usuário
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.lida = true ORDER BY n.dataLeitura DESC")
    List<Notificacao> findNotificacoesLidasPorUsuario(@Param("usuarioId") Long usuarioId);
    
    // Buscar notificações urgentes
    @Query("SELECT n FROM Notificacao n WHERE n.prioridade >= 8 ORDER BY n.prioridade DESC, n.dataEnvio DESC")
    List<Notificacao> findNotificacoesUrgentes();
    
    // Buscar notificações importantes
    @Query("SELECT n FROM Notificacao n WHERE n.prioridade >= 5 ORDER BY n.prioridade DESC, n.dataEnvio DESC")
    List<Notificacao> findNotificacoesImportantes();
    
    // Buscar notificações por tipo e usuário
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.tipo = :tipo ORDER BY n.dataEnvio DESC")
    List<Notificacao> findNotificacoesPorTipoEUsuario(@Param("usuarioId") Long usuarioId, @Param("tipo") String tipo);
    
    // Buscar notificações por categoria e usuário
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.categoria = :categoria ORDER BY n.dataEnvio DESC")
    List<Notificacao> findNotificacoesPorCategoriaEUsuario(@Param("usuarioId") Long usuarioId, @Param("categoria") String categoria);
    
    // Buscar notificações por entidade relacionada e usuário
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.entidadeRelacionada = :entidadeRelacionada ORDER BY n.dataEnvio DESC")
    List<Notificacao> findNotificacoesPorEntidadeEUsuario(@Param("usuarioId") Long usuarioId, @Param("entidadeRelacionada") String entidadeRelacionada);
    
    // Buscar notificações por período e usuário
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.dataEnvio BETWEEN :dataInicio AND :dataFim ORDER BY n.dataEnvio DESC")
    List<Notificacao> findNotificacoesPorPeriodoEUsuario(@Param("usuarioId") Long usuarioId, @Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
    
    // Buscar notificações por prioridade e usuário
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.prioridade >= :prioridadeMinima ORDER BY n.prioridade DESC, n.dataEnvio DESC")
    List<Notificacao> findNotificacoesPorPrioridadeEUsuario(@Param("usuarioId") Long usuarioId, @Param("prioridadeMinima") Integer prioridadeMinima);
    
    // Buscar notificações por status de envio
    @Query("SELECT n FROM Notificacao n WHERE n.enviadaPorEmail = :enviadaPorEmail ORDER BY n.dataEnvio DESC")
    List<Notificacao> findNotificacoesPorStatusEmail(@Param("enviadaPorEmail") Boolean enviadaPorEmail);
    
    @Query("SELECT n FROM Notificacao n WHERE n.enviadaPorPush = :enviadaPorPush ORDER BY n.dataEnvio DESC")
    List<Notificacao> findNotificacoesPorStatusPush(@Param("enviadaPorPush") Boolean enviadaPorPush);
    
    @Query("SELECT n FROM Notificacao n WHERE n.enviadaPorSms = :enviadaPorSms ORDER BY n.dataEnvio DESC")
    List<Notificacao> findNotificacoesPorStatusSms(@Param("enviadaPorSms") Boolean enviadaPorSms);
    
    // Buscar notificações com erro de envio
    @Query("SELECT n FROM Notificacao n WHERE n.erroEnvio IS NOT NULL AND n.erroEnvio != '' ORDER BY n.dataUltimaTentativa DESC")
    List<Notificacao> findNotificacoesComErroEnvio();
    
    // Buscar notificações por tentativas de envio
    @Query("SELECT n FROM Notificacao n WHERE n.tentativasEnvio >= :tentativasMinimas ORDER BY n.tentativasEnvio DESC, n.dataUltimaTentativa DESC")
    List<Notificacao> findNotificacoesPorTentativasEnvio(@Param("tentativasMinimas") Integer tentativasMinimas);
    
    // Contar notificações por usuário
    @Query("SELECT COUNT(n) FROM Notificacao n WHERE n.usuario.id = :usuarioId")
    Long countByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    // Contar notificações não lidas por usuário
    @Query("SELECT COUNT(n) FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.lida = false")
    Long countNotificacoesNaoLidasPorUsuario(@Param("usuarioId") Long usuarioId);
    
    // Contar notificações por tipo
    @Query("SELECT COUNT(n) FROM Notificacao n WHERE n.tipo = :tipo")
    Long countByTipo(@Param("tipo") String tipo);
    
    // Contar notificações por status de leitura
    @Query("SELECT COUNT(n) FROM Notificacao n WHERE n.lida = :lida")
    Long countByLida(@Param("lida") Boolean lida);
    
    // Contar notificações por prioridade
    @Query("SELECT COUNT(n) FROM Notificacao n WHERE n.prioridade >= :prioridadeMinima")
    Long countByPrioridadeMinima(@Param("prioridadeMinima") Integer prioridadeMinima);
    
    // Contar notificações por categoria
    @Query("SELECT COUNT(n) FROM Notificacao n WHERE n.categoria = :categoria")
    Long countByCategoria(@Param("categoria") String categoria);
    
    // Contar notificações por entidade relacionada
    @Query("SELECT COUNT(n) FROM Notificacao n WHERE n.entidadeRelacionada = :entidadeRelacionada")
    Long countByEntidadeRelacionada(@Param("entidadeRelacionada") String entidadeRelacionada);
    
    // Contar notificações por período
    @Query("SELECT COUNT(n) FROM Notificacao n WHERE n.dataEnvio BETWEEN :dataInicio AND :dataFim")
    Long countByPeriodoEnvio(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
    
    // Buscar com paginação
    Page<Notificacao> findAll(Pageable pageable);
    
    // Buscar por usuário com paginação
    Page<Notificacao> findByUsuarioId(Long usuarioId, Pageable pageable);
    
    // Buscar por tipo com paginação
    Page<Notificacao> findByTipo(String tipo, Pageable pageable);
    
    // Buscar por status de leitura com paginação
    Page<Notificacao> findByLida(Boolean lida, Pageable pageable);
    
    // Buscar por prioridade com paginação
    Page<Notificacao> findByPrioridadeGreaterThanEqual(Integer prioridadeMinima, Pageable pageable);
    
    // Buscar por categoria com paginação
    Page<Notificacao> findByCategoriaContainingIgnoreCase(String categoria, Pageable pageable);
    
    // Buscar por entidade relacionada com paginação
    Page<Notificacao> findByEntidadeRelacionadaContainingIgnoreCase(String entidadeRelacionada, Pageable pageable);
    
    // Buscar por período de envio com paginação
    Page<Notificacao> findByDataEnvioBetween(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);
    
    // Buscar por período de leitura com paginação
    Page<Notificacao> findByDataLeituraBetween(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);
    
    // Buscar notificações não lidas por usuário com paginação
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.lida = false")
    Page<Notificacao> findNotificacoesNaoLidasPorUsuario(@Param("usuarioId") Long usuarioId, Pageable pageable);
    
    // Buscar notificações lidas por usuário com paginação
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.lida = true")
    Page<Notificacao> findNotificacoesLidasPorUsuario(@Param("usuarioId") Long usuarioId, Pageable pageable);
    
    // Buscar notificações urgentes com paginação
    @Query("SELECT n FROM Notificacao n WHERE n.prioridade >= 8")
    Page<Notificacao> findNotificacoesUrgentes(Pageable pageable);
    
    // Buscar notificações importantes com paginação
    @Query("SELECT n FROM Notificacao n WHERE n.prioridade >= 5")
    Page<Notificacao> findNotificacoesImportantes(Pageable pageable);
    
    // Buscar notificações por tipo e usuário com paginação
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.tipo = :tipo")
    Page<Notificacao> findNotificacoesPorTipoEUsuario(@Param("usuarioId") Long usuarioId, @Param("tipo") String tipo, Pageable pageable);
    
    // Buscar notificações por categoria e usuário com paginação
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.categoria = :categoria")
    Page<Notificacao> findNotificacoesPorCategoriaEUsuario(@Param("usuarioId") Long usuarioId, @Param("categoria") String categoria, Pageable pageable);
    
    // Buscar notificações por entidade relacionada e usuário com paginação
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.entidadeRelacionada = :entidadeRelacionada")
    Page<Notificacao> findNotificacoesPorEntidadeEUsuario(@Param("usuarioId") Long usuarioId, @Param("entidadeRelacionada") String entidadeRelacionada, Pageable pageable);
    
    // Buscar notificações por período e usuário com paginação
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.dataEnvio BETWEEN :dataInicio AND :dataFim")
    Page<Notificacao> findNotificacoesPorPeriodoEUsuario(@Param("usuarioId") Long usuarioId, @Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim, Pageable pageable);
    
    // Buscar notificações por prioridade e usuário com paginação
    @Query("SELECT n FROM Notificacao n WHERE n.usuario.id = :usuarioId AND n.prioridade >= :prioridadeMinima")
    Page<Notificacao> findNotificacoesPorPrioridadeEUsuario(@Param("usuarioId") Long usuarioId, @Param("prioridadeMinima") Integer prioridadeMinima, Pageable pageable);
    
    // Buscar notificações por status de envio com paginação
    @Query("SELECT n FROM Notificacao n WHERE n.enviadaPorEmail = :enviadaPorEmail")
    Page<Notificacao> findNotificacoesPorStatusEmail(@Param("enviadaPorEmail") Boolean enviadaPorEmail, Pageable pageable);
    
    @Query("SELECT n FROM Notificacao n WHERE n.enviadaPorPush = :enviadaPorPush")
    Page<Notificacao> findNotificacoesPorStatusPush(@Param("enviadaPorPush") Boolean enviadaPorPush, Pageable pageable);
    
    @Query("SELECT n FROM Notificacao n WHERE n.enviadaPorSms = :enviadaPorSms")
    Page<Notificacao> findNotificacoesPorStatusSms(@Param("enviadaPorSms") Boolean enviadaPorSms, Pageable pageable);
    
    // Buscar notificações com erro de envio com paginação
    @Query("SELECT n FROM Notificacao n WHERE n.erroEnvio IS NOT NULL AND n.erroEnvio != ''")
    Page<Notificacao> findNotificacoesComErroEnvio(Pageable pageable);
    
    // Buscar notificações por tentativas de envio com paginação
    @Query("SELECT n FROM Notificacao n WHERE n.tentativasEnvio >= :tentativasMinimas")
    Page<Notificacao> findNotificacoesPorTentativasEnvio(@Param("tentativasMinimas") Integer tentativasMinimas, Pageable pageable);
}
