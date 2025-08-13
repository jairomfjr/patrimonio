package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.PerfilDTO;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.exception.RegraDeNegocioException;
import com.manus.patrimonio.mapper.PatrimonioMapper;
import com.manus.patrimonio.model.Perfil;
import com.manus.patrimonio.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PatrimonioMapper patrimonioMapper;

    // CRUD Operations
    public PerfilDTO criar(PerfilDTO perfilDTO) {
        validarPerfil(perfilDTO);
        
        // Verificar se nome já existe
        if (perfilRepository.existsByNome(perfilDTO.getNome())) {
            throw new RegraDeNegocioException("Nome do perfil já existe");
        }
        
        Perfil perfil = patrimonioMapper.perfilDTOToPerfil(perfilDTO);
        Perfil perfilSalvo = perfilRepository.save(perfil);
        return patrimonioMapper.perfilToPerfilDTO(perfilSalvo);
    }

    @Cacheable(value = "perfis", key = "#id")
    public PerfilDTO buscarPorId(Long id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Perfil não encontrado"));
        
        return patrimonioMapper.perfilToPerfilDTO(perfil);
    }

    @Cacheable(value = "perfis", key = "#nome")
    public PerfilDTO buscarPorNome(String nome) {
        Perfil perfil = perfilRepository.findByNome(nome)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Perfil não encontrado"));
        
        return patrimonioMapper.perfilToPerfilDTO(perfil);
    }

    @Cacheable(value = "perfis", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<PerfilDTO> buscarTodos(Pageable pageable) {
        Page<Perfil> perfis = perfilRepository.findAll(pageable);
        return perfis.map(patrimonioMapper::perfilToPerfilDTO);
    }

    @CacheEvict(value = "perfis", allEntries = true)
    public PerfilDTO atualizar(Long id, PerfilDTO perfilDTO) {
        Perfil perfilExistente = perfilRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Perfil não encontrado"));
        
        validarPerfil(perfilDTO);
        
        // Verificar se nome foi alterado e se já existe
        if (!perfilExistente.getNome().equals(perfilDTO.getNome()) &&
            perfilRepository.existsByNome(perfilDTO.getNome())) {
            throw new RegraDeNegocioException("Nome do perfil já existe");
        }
        
        // Não permitir alterar nome de perfis do sistema
        if ("ADMIN".equals(perfilExistente.getNome()) || 
            "GESTOR".equals(perfilExistente.getNome()) ||
            "OPERADOR".equals(perfilExistente.getNome()) ||
            "CONSULTA".equals(perfilExistente.getNome())) {
            throw new RegraDeNegocioException("Não é possível alterar perfis do sistema");
        }
        
        // Atualizar campos
        perfilExistente.setNome(perfilDTO.getNome());
        perfilExistente.setDescricao(perfilDTO.getDescricao());
        perfilExistente.setNivelAcesso(perfilDTO.getNivelAcesso());
        perfilExistente.setPermissoes(perfilDTO.getPermissoes());
        perfilExistente.setAtivo(perfilDTO.getAtivo());
        
        Perfil perfilAtualizado = perfilRepository.save(perfilExistente);
        return patrimonioMapper.perfilToPerfilDTO(perfilAtualizado);
    }

    @CacheEvict(value = "perfis", allEntries = true)
    public void excluir(Long id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Perfil não encontrado"));
        
        // Não permitir excluir perfis do sistema
        if ("ADMIN".equals(perfil.getNome()) ||
            "GESTOR".equals(perfil.getNome()) ||
            "OPERADOR".equals(perfil.getNome()) ||
            "CONSULTA".equals(perfil.getNome())) {
            throw new RegraDeNegocioException("Não é possível excluir perfis do sistema");
        }
        
        // Nota: Verificação de usuários associados removida devido à falta de relacionamento direto
        // entre Perfil e Usuario na entidade Perfil
        
        perfilRepository.delete(perfil);
    }

    // Business Operations
    @CacheEvict(value = "perfis", allEntries = true)
    public PerfilDTO ativar(Long id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Perfil não encontrado"));
        
        perfil.setAtivo(true);
        Perfil perfilAtivado = perfilRepository.save(perfil);
        return patrimonioMapper.perfilToPerfilDTO(perfilAtivado);
    }

    @CacheEvict(value = "perfis", allEntries = true)
    public PerfilDTO desativar(Long id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Perfil não encontrado"));
        
        // Não permitir desativar perfis do sistema
        if ("ADMIN".equals(perfil.getNome())) {
            throw new RegraDeNegocioException("Não é possível desativar o perfil de administrador");
        }
        
        perfil.setAtivo(false);
        Perfil perfilDesativado = perfilRepository.save(perfil);
        return patrimonioMapper.perfilToPerfilDTO(perfilDesativado);
    }

    // Search Operations
    public Page<PerfilDTO> buscarPorNome(String nome, Pageable pageable) {
        Page<Perfil> perfis = perfilRepository.findByNomeContainingIgnoreCase(nome, pageable);
        return perfis.map(patrimonioMapper::perfilToPerfilDTO);
    }

    public Page<PerfilDTO> buscarPorStatus(Boolean ativo, Pageable pageable) {
        List<Perfil> perfis = ativo ? 
            perfilRepository.findByAtivoTrueOrderByNome() : 
            perfilRepository.findByAtivoFalseOrderByNome();
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), perfis.size());
        List<Perfil> pageContent = perfis.subList(start, end);
        return new org.springframework.data.domain.PageImpl<>(pageContent, pageable, perfis.size())
                .map(patrimonioMapper::perfilToPerfilDTO);
    }

    public Page<PerfilDTO> buscarPorNivelAcesso(Integer nivelMinimo, Integer nivelMaximo, Pageable pageable) {
        List<Perfil> perfis = perfilRepository.findPerfisPorNivelAcesso(nivelMinimo, nivelMaximo);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), perfis.size());
        List<Perfil> pageContent = perfis.subList(start, end);
        return new org.springframework.data.domain.PageImpl<>(pageContent, pageable, perfis.size())
                .map(patrimonioMapper::perfilToPerfilDTO);
    }

    public Page<PerfilDTO> buscarPorPermissao(String permissao, Pageable pageable) {
        List<Perfil> perfis = perfilRepository.findPerfisPorPermissao(permissao);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), perfis.size());
        List<Perfil> pageContent = perfis.subList(start, end);
        return new org.springframework.data.domain.PageImpl<>(pageContent, pageable, perfis.size())
                .map(patrimonioMapper::perfilToPerfilDTO);
    }

    // Statistics and Reports
    public Long contarPerfisAtivos() {
        return perfilRepository.countPerfisAtivos();
    }

    public Long contarPerfisInativos() {
        return perfilRepository.countPerfisInativos();
    }

    public Long contarUsuariosPorPerfil(Long perfilId) {
        // Nota: Não é possível contar usuários por perfil sem relacionamento direto
        // Retornando 0 como valor padrão
        return 0L;
    }

    public List<PerfilDTO> buscarPerfisDoSistema() {
        List<Perfil> perfis = perfilRepository.findPerfisAdministradores();
        perfis.addAll(perfilRepository.findPerfisGestores());
        perfis.addAll(perfilRepository.findPerfisOperadores());
        perfis.addAll(perfilRepository.findPerfisConsulta());
        return patrimonioMapper.perfisToPerfilDTOs(perfis);
    }

    public List<PerfilDTO> buscarPerfisCustomizados() {
        // Buscar todos os perfis e remover os do sistema
        List<Perfil> todosPerfis = perfilRepository.findAll();
        List<PerfilDTO> perfisDoSistemaDTO = buscarPerfisDoSistema();
        List<Perfil> perfisDoSistema = perfisDoSistemaDTO.stream()
                .map(patrimonioMapper::perfilDTOToPerfil)
                .toList();
        todosPerfis.removeAll(perfisDoSistema);
        return patrimonioMapper.perfisToPerfilDTOs(todosPerfis);
    }

    // Validation
    private void validarPerfil(PerfilDTO perfilDTO) {
        if (perfilDTO.getNome() == null || perfilDTO.getNome().trim().length() < 2) {
            throw new RegraDeNegocioException("Nome do perfil deve ter pelo menos 2 caracteres");
        }
        
        if (perfilDTO.getNome().trim().length() > 50) {
            throw new RegraDeNegocioException("Nome do perfil deve ter no máximo 50 caracteres");
        }
        
        if (perfilDTO.getNivelAcesso() != null && (perfilDTO.getNivelAcesso() < 1 || perfilDTO.getNivelAcesso() > 100)) {
            throw new RegraDeNegocioException("Nível de acesso deve estar entre 1 e 100");
        }
        
        if (perfilDTO.getDescricao() != null && perfilDTO.getDescricao().trim().length() > 500) {
            throw new RegraDeNegocioException("Descrição deve ter no máximo 500 caracteres");
        }
    }
}
