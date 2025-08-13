package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.UsuarioDTO;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.exception.RegraDeNegocioException;
import com.manus.patrimonio.mapper.PatrimonioMapper;
import com.manus.patrimonio.model.Perfil;
import com.manus.patrimonio.model.Usuario;
import com.manus.patrimonio.repository.PerfilRepository;
import com.manus.patrimonio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PatrimonioMapper patrimonioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CRUD Operations
    public UsuarioDTO criar(UsuarioDTO usuarioDTO) {
        validarUsuario(usuarioDTO);
        
        // Verificar se username já existe
        if (usuarioRepository.existsByUsername(usuarioDTO.getUsername())) {
            throw new RegraDeNegocioException("Username já existe");
        }
        
        // Verificar se email já existe
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new RegraDeNegocioException("Email já existe");
        }
        
        Usuario usuario = patrimonioMapper.usuarioDTOToUsuario(usuarioDTO);
        
        // Criptografar senha
        if (usuarioDTO.getSenha() != null) {
            usuario.setSenhaHash(passwordEncoder.encode(usuarioDTO.getSenha()));
        }
        
        // Definir data de criação
        usuario.setDataCriacao(LocalDateTime.now());
        
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return patrimonioMapper.usuarioToUsuarioDTO(usuarioSalvo);
    }

    @Cacheable(value = "usuarios", key = "#id")
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        
        return patrimonioMapper.usuarioToUsuarioDTO(usuario);
    }

    @Cacheable(value = "usuarios", key = "#username")
    public UsuarioDTO buscarPorUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        
        return patrimonioMapper.usuarioToUsuarioDTO(usuario);
    }

    @Cacheable(value = "usuarios", key = "#email")
    public UsuarioDTO buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        
        return patrimonioMapper.usuarioToUsuarioDTO(usuario);
    }

    @Cacheable(value = "usuarios", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<UsuarioDTO> buscarTodos(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return usuarios.map(patrimonioMapper::usuarioToUsuarioDTO);
    }

    @CacheEvict(value = "usuarios", allEntries = true)
    public UsuarioDTO atualizar(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        
        validarUsuario(usuarioDTO);
        
        // Verificar se username foi alterado e se já existe
        if (!usuarioExistente.getUsername().equals(usuarioDTO.getUsername()) &&
            usuarioRepository.existsByUsername(usuarioDTO.getUsername())) {
            throw new RegraDeNegocioException("Username já existe");
        }
        
        // Verificar se email foi alterado e se já existe
        if (!usuarioExistente.getEmail().equals(usuarioDTO.getEmail()) &&
            usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new RegraDeNegocioException("Email já existe");
        }
        
        // Atualizar campos
        usuarioExistente.setUsername(usuarioDTO.getUsername());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        usuarioExistente.setNomeCompleto(usuarioDTO.getNomeCompleto());
        usuarioExistente.setTelefone(usuarioDTO.getTelefone());
        usuarioExistente.setDepartamento(usuarioDTO.getDepartamento());
        usuarioExistente.setCargo(usuarioDTO.getCargo());
        usuarioExistente.setMatricula(usuarioDTO.getMatricula());
        usuarioExistente.setCpf(usuarioDTO.getCpf());
        usuarioExistente.setDataNascimento(usuarioDTO.getDataNascimento());
        usuarioExistente.setEndereco(usuarioDTO.getEndereco());
        // cidade não tem setter no modelo
        usuarioExistente.setEstado(usuarioDTO.getEstado());
        usuarioExistente.setCep(usuarioDTO.getCep());
        usuarioExistente.setPais(usuarioDTO.getPais());
        
        // Atualizar senha se fornecida
        if (usuarioDTO.getSenha() != null && !usuarioDTO.getSenha().trim().isEmpty()) {
            usuarioExistente.setSenhaHash(passwordEncoder.encode(usuarioDTO.getSenha()));
        }
        
        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return patrimonioMapper.usuarioToUsuarioDTO(usuarioAtualizado);
    }

    @CacheEvict(value = "usuarios", allEntries = true)
    public void excluir(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        
        if (usuario.isAdmin()) {
            throw new RegraDeNegocioException("Não é possível excluir um usuário administrador");
        }
        
        usuarioRepository.delete(usuario);
    }

    // Business Operations
    @CacheEvict(value = "usuarios", allEntries = true)
    public UsuarioDTO ativar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        
        usuario.ativar();
        Usuario usuarioAtivado = usuarioRepository.save(usuario);
        return patrimonioMapper.usuarioToUsuarioDTO(usuarioAtivado);
    }

    @CacheEvict(value = "usuarios", allEntries = true)
    public UsuarioDTO desativar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        
        if (usuario.isAdmin()) {
            throw new RegraDeNegocioException("Não é possível desativar um usuário administrador");
        }
        
        usuario.desativar();
        Usuario usuarioDesativado = usuarioRepository.save(usuario);
        return patrimonioMapper.usuarioToUsuarioDTO(usuarioDesativado);
    }

    @CacheEvict(value = "usuarios", allEntries = true)
    public UsuarioDTO desbloquear(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        
        usuario.desbloquear();
        Usuario usuarioDesbloqueado = usuarioRepository.save(usuario);
        return patrimonioMapper.usuarioToUsuarioDTO(usuarioDesbloqueado);
    }

    @CacheEvict(value = "usuarios", allEntries = true)
    public UsuarioDTO alterarSenha(Long id, String senhaAtual, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        
        // Verificar senha atual
        if (!passwordEncoder.matches(senhaAtual, usuario.getSenhaHash())) {
            throw new RegraDeNegocioException("Senha atual incorreta");
        }
        
        // Validar nova senha
        if (novaSenha == null || novaSenha.trim().length() < 6) {
            throw new RegraDeNegocioException("Nova senha deve ter pelo menos 6 caracteres");
        }
        
        // Criptografar nova senha
        usuario.setSenhaHash(passwordEncoder.encode(novaSenha));
        
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return patrimonioMapper.usuarioToUsuarioDTO(usuarioAtualizado);
    }

    @CacheEvict(value = "usuarios", allEntries = true)
    public UsuarioDTO adicionarPerfil(Long usuarioId, Long perfilId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Perfil não encontrado"));
        
        usuario.adicionarPerfil(perfil);
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return patrimonioMapper.usuarioToUsuarioDTO(usuarioAtualizado);
    }

    @CacheEvict(value = "usuarios", allEntries = true)
    public UsuarioDTO removerPerfil(Long usuarioId, Long perfilId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Perfil não encontrado"));
        
        if (usuario.isAdmin() && "ADMIN".equals(perfil.getNome())) {
            throw new RegraDeNegocioException("Não é possível remover o perfil de administrador");
        }
        
        usuario.removerPerfil(perfil);
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return patrimonioMapper.usuarioToUsuarioDTO(usuarioAtualizado);
    }

    // Search Operations
    public Page<UsuarioDTO> buscarPorNome(String nome, Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findByNomeCompletoContainingIgnoreCase(nome, pageable);
        return usuarios.map(patrimonioMapper::usuarioToUsuarioDTO);
    }

    public Page<UsuarioDTO> buscarPorDepartamento(String departamento, Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findByDepartamentoContainingIgnoreCase(departamento, pageable);
        return usuarios.map(patrimonioMapper::usuarioToUsuarioDTO);
    }

    public Page<UsuarioDTO> buscarPorCargo(String cargo, Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findByCargoContainingIgnoreCase(cargo, pageable);
        return usuarios.map(patrimonioMapper::usuarioToUsuarioDTO);
    }

    public Page<UsuarioDTO> buscarPorStatus(Boolean ativo, Pageable pageable) {
        List<Usuario> usuarios = ativo ? 
            usuarioRepository.findByAtivoTrueOrderByNomeCompleto() : 
            usuarioRepository.findByAtivoFalseOrderByNomeCompleto();
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), usuarios.size());
        List<Usuario> pageContent = usuarios.subList(start, end);
        return new PageImpl<>(pageContent, pageable, usuarios.size())
                .map(patrimonioMapper::usuarioToUsuarioDTO);
    }

    public Page<UsuarioDTO> buscarPorPerfil(String nomePerfil, Pageable pageable) {
        List<Usuario> usuarios = usuarioRepository.findUsuariosPorNomePerfil(nomePerfil);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), usuarios.size());
        List<Usuario> pageContent = usuarios.subList(start, end);
        return new PageImpl<>(pageContent, pageable, usuarios.size())
                .map(patrimonioMapper::usuarioToUsuarioDTO);
    }

    public Page<UsuarioDTO> buscarPorCidade(String cidade, Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findByCidadeContainingIgnoreCase(cidade, pageable);
        return usuarios.map(patrimonioMapper::usuarioToUsuarioDTO);
    }

    public Page<UsuarioDTO> buscarPorEstado(String estado, Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findByEstadoContainingIgnoreCase(estado, pageable);
        return usuarios.map(patrimonioMapper::usuarioToUsuarioDTO);
    }

    public Page<UsuarioDTO> buscarUsuariosConsulta(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findUsuariosConsulta(pageable);
        return usuarios.map(patrimonioMapper::usuarioToUsuarioDTO);
    }

    // Statistics and Reports
    public Long contarUsuariosAtivos() {
        return usuarioRepository.countUsuariosAtivos();
    }

    public Long contarUsuariosInativos() {
        return usuarioRepository.countUsuariosInativos();
    }

    public Long contarUsuariosPorDepartamento(String departamento) {
        List<Usuario> usuarios = usuarioRepository.findByDepartamentoContainingIgnoreCaseOrderByNomeCompleto(departamento);
        return (long) usuarios.size();
    }

    public Long contarUsuariosPorPerfil(String nomePerfil) {
        List<Usuario> usuarios = usuarioRepository.findUsuariosPorNomePerfil(nomePerfil);
        return (long) usuarios.size();
    }

    public List<UsuarioDTO> buscarUsuariosBloqueados() {
        List<Usuario> usuarios = usuarioRepository.findUsuariosBloqueados();
        return patrimonioMapper.usuariosToUsuarioDTOs(usuarios);
    }

    public List<UsuarioDTO> buscarUsuariosComSenhaExpirada() {
        List<Usuario> usuarios = usuarioRepository.findUsuariosComSenhaExpirada(LocalDateTime.now());
        return patrimonioMapper.usuariosToUsuarioDTOs(usuarios);
    }

    // Authentication and Security
    public boolean validarCredenciais(String username, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isEmpty()) {
            return false;
        }
        
        Usuario usuario = usuarioOpt.get();
        if (!usuario.isAtivo() || usuario.isBloqueado()) {
            return false;
        }
        
        return passwordEncoder.matches(senha, usuario.getSenhaHash());
    }

    public void registrarLoginSucesso(String username) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.registrarLoginSucesso();
            usuarioRepository.save(usuario);
        }
    }

    public void registrarLoginFalha(String username) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.registrarLoginFalha();
            usuarioRepository.save(usuario);
        }
    }

    // Validation
    private void validarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getUsername() == null || usuarioDTO.getUsername().trim().length() < 3) {
            throw new RegraDeNegocioException("Username deve ter pelo menos 3 caracteres");
        }
        
        if (usuarioDTO.getEmail() == null || !usuarioDTO.getEmail().contains("@")) {
            throw new RegraDeNegocioException("Email deve ser válido");
        }
        
        if (usuarioDTO.getNomeCompleto() == null || usuarioDTO.getNomeCompleto().trim().length() < 2) {
            throw new RegraDeNegocioException("Nome completo deve ter pelo menos 2 caracteres");
        }
        
        if (usuarioDTO.getSenha() != null && usuarioDTO.getSenha().trim().length() < 6) {
            throw new RegraDeNegocioException("Senha deve ter pelo menos 6 caracteres");
        }
    }
}
