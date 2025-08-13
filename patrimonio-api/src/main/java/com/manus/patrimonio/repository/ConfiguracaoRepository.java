package com.manus.patrimonio.repository;

import com.manus.patrimonio.model.Configuracao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {

    // Buscar por chave
    Optional<Configuracao> findByChave(String chave);
    
    // Buscar por chave ignorando case
    Optional<Configuracao> findByChaveIgnoreCase(String chave);
    
    // Verificar se chave existe
    boolean existsByChave(String chave);
    
    // Verificar se chave existe ignorando case
    boolean existsByChaveIgnoreCase(String chave);
    
    // Buscar por tipo
    List<Configuracao> findByTipoOrderByChave(String tipo);
    
    // Buscar por editável
    List<Configuracao> findByEditavelOrderByChave(Boolean editavel);
    
    // Buscar por descrição
    List<Configuracao> findByDescricaoContainingIgnoreCaseOrderByChave(String descricao);
    
    // Buscar configurações do sistema (não editáveis)
    @Query("SELECT c FROM Configuracao c WHERE c.editavel = false ORDER BY c.chave")
    List<Configuracao> findConfiguracoesDoSistema();
    
    // Buscar configurações editáveis
    @Query("SELECT c FROM Configuracao c WHERE c.editavel = true ORDER BY c.chave")
    List<Configuracao> findConfiguracoesEditaveis();
    
    // Buscar por tipo e editável
    @Query("SELECT c FROM Configuracao c WHERE c.tipo = :tipo AND c.editavel = :editavel ORDER BY c.chave")
    List<Configuracao> findByTipoAndEditavel(@Param("tipo") String tipo, @Param("editavel") Boolean editavel);
    
    // Buscar configurações por padrão de chave
    @Query("SELECT c FROM Configuracao c WHERE c.chave LIKE :padrao ORDER BY c.chave")
    List<Configuracao> findByChaveLike(@Param("padrao") String padrao);
    
    // Contar por tipo
    @Query("SELECT COUNT(c) FROM Configuracao c WHERE c.tipo = :tipo")
    Long countByTipo(@Param("tipo") String tipo);
    
    // Contar configurações editáveis
    @Query("SELECT COUNT(c) FROM Configuracao c WHERE c.editavel = true")
    Long countByEditavelTrue();
    
    // Contar configurações não editáveis
    @Query("SELECT COUNT(c) FROM Configuracao c WHERE c.editavel = false")
    Long countByEditavelFalse();
    
    // Buscar com paginação
    Page<Configuracao> findAll(Pageable pageable);
    
    // Buscar por chave com paginação
    Page<Configuracao> findByChaveContainingIgnoreCase(String chave, Pageable pageable);
    
    // Buscar por tipo com paginação
    Page<Configuracao> findByTipo(String tipo, Pageable pageable);
    
    // Buscar por editável com paginação
    Page<Configuracao> findByEditavel(Boolean editavel, Pageable pageable);
    
    // Buscar por descrição com paginação
    Page<Configuracao> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);
    
    // Buscar por tipo e editável com paginação
    Page<Configuracao> findByTipoAndEditavel(String tipo, Boolean editavel, Pageable pageable);
}
