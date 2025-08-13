package com.manus.patrimonio.repository;

import com.manus.patrimonio.model.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    // Buscar por nome
    Optional<Perfil> findByNome(String nome);
    
    // Buscar por nome ignorando case
    Optional<Perfil> findByNomeIgnoreCase(String nome);
    
    // Buscar por nível de acesso
    List<Perfil> findByNivelAcessoGreaterThanEqualOrderByNivelAcessoDesc(Integer nivelMinimo);
    
    // Buscar por nível de acesso exato
    List<Perfil> findByNivelAcessoOrderByNome(Integer nivelAcesso);
    
    // Buscar perfis ativos
    List<Perfil> findByAtivoTrueOrderByNome();
    
    // Buscar perfis inativos
    List<Perfil> findByAtivoFalseOrderByNome();
    
    // Buscar por descrição
    List<Perfil> findByDescricaoContainingIgnoreCaseOrderByNome(String descricao);
    
    // Buscar perfis administradores
    @Query("SELECT p FROM Perfil p WHERE p.nome = 'ADMIN' OR p.nivelAcesso >= 100")
    List<Perfil> findPerfisAdministradores();
    
    // Buscar perfis gestores
    @Query("SELECT p FROM Perfil p WHERE p.nome = 'GESTOR' OR p.nivelAcesso >= 50")
    List<Perfil> findPerfisGestores();
    
    // Buscar perfis operadores
    @Query("SELECT p FROM Perfil p WHERE p.nome = 'OPERADOR' OR p.nivelAcesso >= 20")
    List<Perfil> findPerfisOperadores();
    
    // Buscar perfis de consulta
    @Query("SELECT p FROM Perfil p WHERE p.nome = 'CONSULTA' OR p.nivelAcesso >= 10")
    List<Perfil> findPerfisConsulta();
    
    // Buscar perfis por permissão
    @Query("SELECT p FROM Perfil p JOIN p.permissoes perm WHERE perm = :permissao")
    List<Perfil> findPerfisPorPermissao(@Param("permissao") String permissao);
    
    // Buscar perfis por múltiplas permissões
    @Query("SELECT p FROM Perfil p JOIN p.permissoes perm WHERE perm IN :permissoes")
    List<Perfil> findPerfisPorPermissoes(@Param("permissoes") List<String> permissoes);
    
    // Buscar perfis com nível de acesso entre valores
    @Query("SELECT p FROM Perfil p WHERE p.nivelAcesso BETWEEN :nivelMinimo AND :nivelMaximo ORDER BY p.nivelAcesso")
    List<Perfil> findPerfisPorNivelAcesso(@Param("nivelMinimo") Integer nivelMinimo, @Param("nivelMaximo") Integer nivelMaximo);
    
    // Contar perfis por status
    @Query("SELECT COUNT(p) FROM Perfil p WHERE p.ativo = true")
    Long countPerfisAtivos();
    
    @Query("SELECT COUNT(p) FROM Perfil p WHERE p.ativo = false")
    Long countPerfisInativos();
    
    // Contar perfis por nível de acesso
    @Query("SELECT COUNT(p) FROM Perfil p WHERE p.nivelAcesso >= :nivelMinimo")
    Long countPerfisPorNivelAcessoMinimo(@Param("nivelMinimo") Integer nivelMinimo);
    
    // Verificar se nome existe
    boolean existsByNome(String nome);
    
    // Verificar se nome existe ignorando case
    boolean existsByNomeIgnoreCase(String nome);
    
    // Buscar com paginação
    Page<Perfil> findAll(Pageable pageable);
    
    // Buscar por nome com paginação
    Page<Perfil> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    
    // Buscar por descrição com paginação
    Page<Perfil> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);
    
    // Buscar por nível de acesso com paginação
    Page<Perfil> findByNivelAcessoGreaterThanEqual(Integer nivelMinimo, Pageable pageable);
    
    // Buscar por nível de acesso exato com paginação
    Page<Perfil> findByNivelAcesso(Integer nivelAcesso, Pageable pageable);
    
    // Buscar perfis ativos com paginação
    Page<Perfil> findByAtivoTrue(Pageable pageable);
    
    // Buscar perfis inativos com paginação
    Page<Perfil> findByAtivoFalse(Pageable pageable);
    
    // Buscar perfis administradores com paginação
    @Query("SELECT p FROM Perfil p WHERE p.nome = 'ADMIN' OR p.nivelAcesso >= 100")
    Page<Perfil> findPerfisAdministradores(Pageable pageable);
    
    // Buscar perfis gestores com paginação
    @Query("SELECT p FROM Perfil p WHERE p.nome = 'GESTOR' OR p.nivelAcesso >= 50")
    Page<Perfil> findPerfisGestores(Pageable pageable);
    
    // Buscar perfis operadores com paginação
    @Query("SELECT p FROM Perfil p WHERE p.nome = 'OPERADOR' OR p.nivelAcesso >= 20")
    Page<Perfil> findPerfisOperadores(Pageable pageable);
    
    // Buscar perfis de consulta com paginação
    @Query("SELECT p FROM Perfil p WHERE p.nome = 'CONSULTA' OR p.nivelAcesso >= 10")
    Page<Perfil> findPerfisConsulta(Pageable pageable);
    
    // Buscar perfis por permissão com paginação
    @Query("SELECT p FROM Perfil p JOIN p.permissoes perm WHERE perm = :permissao")
    Page<Perfil> findPerfisPorPermissao(@Param("permissao") String permissao, Pageable pageable);
    
    // Buscar perfis por múltiplas permissões com paginação
    @Query("SELECT p FROM Perfil p JOIN p.permissoes perm WHERE perm IN :permissoes")
    Page<Perfil> findPerfisPorPermissoes(@Param("permissoes") List<String> permissoes, Pageable pageable);
    
    // Buscar perfis com nível de acesso entre valores com paginação
    @Query("SELECT p FROM Perfil p WHERE p.nivelAcesso BETWEEN :nivelMinimo AND :nivelMaximo ORDER BY p.nivelAcesso")
    Page<Perfil> findPerfisPorNivelAcesso(@Param("nivelMinimo") Integer nivelMinimo, @Param("nivelMaximo") Integer nivelMaximo, Pageable pageable);
}
