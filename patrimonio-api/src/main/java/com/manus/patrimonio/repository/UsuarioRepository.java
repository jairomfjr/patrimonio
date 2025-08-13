package com.manus.patrimonio.repository;

import com.manus.patrimonio.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar por username
    Optional<Usuario> findByUsername(String username);
    
    // Buscar por email
    Optional<Usuario> findByEmail(String email);
    
    // Buscar por CPF
    Optional<Usuario> findByCpf(String cpf);
    
    // Buscar por matrícula
    Optional<Usuario> findByMatricula(String matricula);
    
    // Buscar por nome completo
    List<Usuario> findByNomeCompletoContainingIgnoreCaseOrderByNomeCompleto(String nomeCompleto);
    
    // Buscar por departamento
    List<Usuario> findByDepartamentoContainingIgnoreCaseOrderByNomeCompleto(String departamento);
    
    // Buscar por cargo
    List<Usuario> findByCargoContainingIgnoreCaseOrderByNomeCompleto(String cargo);
    
    // Buscar usuários ativos
    List<Usuario> findByAtivoTrueOrderByNomeCompleto();
    
    // Buscar usuários inativos
    List<Usuario> findByAtivoFalseOrderByNomeCompleto();
    
    // Buscar usuários bloqueados
    @Query("SELECT u FROM Usuario u WHERE u.dataBloqueio IS NOT NULL")
    List<Usuario> findUsuariosBloqueados();
    
    // Buscar usuários não bloqueados
    @Query("SELECT u FROM Usuario u WHERE u.dataBloqueio IS NULL")
    List<Usuario> findUsuariosNaoBloqueados();
    
    // Buscar usuários com senha expirada
    @Query("SELECT u FROM Usuario u WHERE u.dataExpiracaoSenha IS NOT NULL AND u.dataExpiracaoSenha < :dataAtual")
    List<Usuario> findUsuariosComSenhaExpirada(@Param("dataAtual") LocalDateTime dataAtual);
    
    // Buscar usuários por cidade
    List<Usuario> findByCidadeContainingIgnoreCaseOrderByNomeCompleto(String cidade);
    
    // Buscar usuários por estado
    List<Usuario> findByEstadoContainingIgnoreCaseOrderByNomeCompleto(String estado);
    
    // Buscar usuários por último login
    @Query("SELECT u FROM Usuario u WHERE u.dataUltimoLogin IS NOT NULL AND u.dataUltimoLogin >= :dataLimite ORDER BY u.dataUltimoLogin DESC")
    List<Usuario> findUsuariosAtivosPorUltimoLogin(@Param("dataLimite") LocalDateTime dataLimite);
    
    // Buscar usuários inativos por último login
    @Query("SELECT u FROM Usuario u WHERE u.dataUltimoLogin IS NULL OR u.dataUltimoLogin < :dataLimite ORDER BY u.dataUltimoLogin")
    List<Usuario> findUsuariosInativosPorUltimoLogin(@Param("dataLimite") LocalDateTime dataLimite);
    
    // Buscar usuários por perfil
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.id = :perfilId")
    List<Usuario> findUsuariosPorPerfil(@Param("perfilId") Long perfilId);
    
    // Buscar usuários por nome do perfil
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.nome = :nomePerfil")
    List<Usuario> findUsuariosPorNomePerfil(@Param("nomePerfil") String nomePerfil);
    
    // Buscar usuários administradores
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.nome = 'ADMIN' OR p.nivelAcesso >= 100")
    List<Usuario> findUsuariosAdministradores();
    
    // Buscar usuários gestores
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.nome = 'GESTOR' OR p.nivelAcesso >= 50")
    List<Usuario> findUsuariosGestores();
    
    // Buscar usuários operadores
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.nome = 'OPERADOR' OR p.nivelAcesso >= 20")
    List<Usuario> findUsuariosOperadores();
    
    // Buscar usuários de consulta
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.nome = 'CONSULTA' OR p.nivelAcesso >= 10")
    List<Usuario> findUsuariosConsulta();
    
    // Contar usuários por status
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.ativo = true")
    Long countUsuariosAtivos();
    
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.ativo = false")
    Long countUsuariosInativos();
    
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.dataBloqueio IS NOT NULL")
    Long countUsuariosBloqueados();
    
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.dataBloqueio IS NULL")
    Long countUsuariosNaoBloqueados();
    
    // Contar usuários por perfil
    @Query("SELECT COUNT(u) FROM Usuario u JOIN u.perfis p WHERE p.id = :perfilId")
    Long countUsuariosPorPerfil(@Param("perfilId") Long perfilId);
    
    // Verificar se username existe
    boolean existsByUsername(String username);
    
    // Verificar se email existe
    boolean existsByEmail(String email);
    
    // Verificar se CPF existe
    boolean existsByCpf(String cpf);
    
    // Verificar se matrícula existe
    boolean existsByMatricula(String matricula);
    
    // Buscar com paginação
    Page<Usuario> findAll(Pageable pageable);
    
    // Buscar por nome completo com paginação
    Page<Usuario> findByNomeCompletoContainingIgnoreCase(String nomeCompleto, Pageable pageable);
    
    // Buscar por departamento com paginação
    Page<Usuario> findByDepartamentoContainingIgnoreCase(String departamento, Pageable pageable);
    
    // Buscar por cargo com paginação
    Page<Usuario> findByCargoContainingIgnoreCase(String cargo, Pageable pageable);
    
    // Buscar usuários ativos com paginação
    Page<Usuario> findByAtivoTrue(Pageable pageable);
    
    // Buscar usuários inativos com paginação
    Page<Usuario> findByAtivoFalse(Pageable pageable);
    
    // Buscar usuários bloqueados com paginação
    @Query("SELECT u FROM Usuario u WHERE u.dataBloqueio IS NOT NULL")
    Page<Usuario> findUsuariosBloqueados(Pageable pageable);
    
    // Buscar usuários não bloqueados com paginação
    @Query("SELECT u FROM Usuario u WHERE u.dataBloqueio IS NULL")
    Page<Usuario> findUsuariosNaoBloqueados(Pageable pageable);
    
    // Buscar usuários por cidade com paginação
    Page<Usuario> findByCidadeContainingIgnoreCase(String cidade, Pageable pageable);
    
    // Buscar usuários por estado com paginação
    Page<Usuario> findByEstadoContainingIgnoreCase(String estado, Pageable pageable);
    
    // Buscar usuários por perfil com paginação
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.id = :perfilId")
    Page<Usuario> findUsuariosPorPerfil(@Param("perfilId") Long perfilId, Pageable pageable);
    
    // Buscar usuários por nome do perfil com paginação
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.nome = :nomePerfil")
    Page<Usuario> findUsuariosPorNomePerfil(@Param("nomePerfil") String nomePerfil, Pageable pageable);
    
    // Buscar usuários administradores com paginação
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.nome = 'ADMIN' OR p.nivelAcesso >= 100")
    Page<Usuario> findUsuariosAdministradores(Pageable pageable);
    
    // Buscar usuários gestores com paginação
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.nome = 'GESTOR' OR p.nivelAcesso >= 50")
    Page<Usuario> findUsuariosGestores(Pageable pageable);
    
    // Buscar usuários operadores com paginação
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.nome = 'OPERADOR' OR p.nivelAcesso >= 20")
    Page<Usuario> findUsuariosOperadores(Pageable pageable);
    
    // Buscar usuários de consulta com paginação
    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.nome = 'CONSULTA' OR p.nivelAcesso >= 10")
    Page<Usuario> findUsuariosConsulta(Pageable pageable);
}
