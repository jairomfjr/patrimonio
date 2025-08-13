package com.manus.patrimonio.controller;

import com.manus.patrimonio.dto.UsuarioDTO;
import com.manus.patrimonio.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "API para gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Criar usuário", description = "Cria um novo usuário")
    public ResponseEntity<UsuarioDTO> criar(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioCriado = usuarioService.criar(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Buscar usuário por username", description = "Retorna um usuário por username")
    public ResponseEntity<UsuarioDTO> buscarPorUsername(@PathVariable String username) {
        UsuarioDTO usuario = usuarioService.buscarPorUsername(username);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Buscar usuário por email", description = "Retorna um usuário por email")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(@PathVariable String email) {
        UsuarioDTO usuario = usuarioService.buscarPorEmail(email);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation(summary = "Listar usuários", description = "Retorna lista paginada de usuários")
    public ResponseEntity<Page<UsuarioDTO>> listar(Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioService.buscarTodos(pageable);
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza um usuário existente")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioAtualizado = usuarioService.atualizar(id, usuarioDTO);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuário", description = "Exclui um usuário")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/ativar")
    @Operation(summary = "Ativar usuário", description = "Ativa um usuário inativo")
    public ResponseEntity<UsuarioDTO> ativar(@PathVariable Long id) {
        UsuarioDTO usuarioAtivado = usuarioService.ativar(id);
        return ResponseEntity.ok(usuarioAtivado);
    }

    @PostMapping("/{id}/desativar")
    @Operation(summary = "Desativar usuário", description = "Desativa um usuário ativo")
    public ResponseEntity<UsuarioDTO> desativar(@PathVariable Long id) {
        UsuarioDTO usuarioDesativado = usuarioService.desativar(id);
        return ResponseEntity.ok(usuarioDesativado);
    }

    @PostMapping("/{id}/desbloquear")
    @Operation(summary = "Desbloquear usuário", description = "Desbloqueia um usuário bloqueado")
    public ResponseEntity<UsuarioDTO> desbloquear(@PathVariable Long id) {
        UsuarioDTO usuarioDesbloqueado = usuarioService.desbloquear(id);
        return ResponseEntity.ok(usuarioDesbloqueado);
    }

    @PostMapping("/{id}/alterar-senha")
    @Operation(summary = "Alterar senha", description = "Altera a senha de um usuário")
    public ResponseEntity<UsuarioDTO> alterarSenha(@PathVariable Long id,
                                                 @RequestParam String senhaAtual,
                                                 @RequestParam String novaSenha) {
        UsuarioDTO usuarioAtualizado = usuarioService.alterarSenha(id, senhaAtual, novaSenha);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PostMapping("/{id}/adicionar-perfil/{perfilId}")
    @Operation(summary = "Adicionar perfil", description = "Adiciona um perfil a um usuário")
    public ResponseEntity<UsuarioDTO> adicionarPerfil(@PathVariable Long id, @PathVariable Long perfilId) {
        UsuarioDTO usuarioAtualizado = usuarioService.adicionarPerfil(id, perfilId);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @PostMapping("/{id}/remover-perfil/{perfilId}")
    @Operation(summary = "Remover perfil", description = "Remove um perfil de um usuário")
    public ResponseEntity<UsuarioDTO> removerPerfil(@PathVariable Long id, @PathVariable Long perfilId) {
        UsuarioDTO usuarioAtualizado = usuarioService.removerPerfil(id, perfilId);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @GetMapping("/buscar/nome")
    @Operation(summary = "Buscar por nome", description = "Busca usuários por nome")
    public ResponseEntity<Page<UsuarioDTO>> buscarPorNome(@RequestParam String nome, Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioService.buscarPorNome(nome, pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar/departamento")
    @Operation(summary = "Buscar por departamento", description = "Busca usuários por departamento")
    public ResponseEntity<Page<UsuarioDTO>> buscarPorDepartamento(@RequestParam String departamento, Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioService.buscarPorDepartamento(departamento, pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar/cargo")
    @Operation(summary = "Buscar por cargo", description = "Busca usuários por cargo")
    public ResponseEntity<Page<UsuarioDTO>> buscarPorCargo(@RequestParam String cargo, Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioService.buscarPorCargo(cargo, pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar/status")
    @Operation(summary = "Buscar por status", description = "Busca usuários por status (ativo/inativo)")
    public ResponseEntity<Page<UsuarioDTO>> buscarPorStatus(@RequestParam Boolean ativo, Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioService.buscarPorStatus(ativo, pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar/perfil")
    @Operation(summary = "Buscar por perfil", description = "Busca usuários por perfil")
    public ResponseEntity<Page<UsuarioDTO>> buscarPorPerfil(@RequestParam String nomePerfil, Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioService.buscarPorPerfil(nomePerfil, pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar/cidade")
    @Operation(summary = "Buscar por cidade", description = "Busca usuários por cidade")
    public ResponseEntity<Page<UsuarioDTO>> buscarPorCidade(@RequestParam String cidade, Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioService.buscarPorCidade(cidade, pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar/estado")
    @Operation(summary = "Buscar por estado", description = "Busca usuários por estado")
    public ResponseEntity<Page<UsuarioDTO>> buscarPorEstado(@RequestParam String estado, Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioService.buscarPorEstado(estado, pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/consulta")
    @Operation(summary = "Buscar usuários de consulta", description = "Busca usuários com perfil de consulta")
    public ResponseEntity<Page<UsuarioDTO>> buscarUsuariosConsulta(Pageable pageable) {
        Page<UsuarioDTO> usuarios = usuarioService.buscarUsuariosConsulta(pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/estatisticas/ativos")
    @Operation(summary = "Contar usuários ativos", description = "Conta usuários ativos")
    public ResponseEntity<Long> contarAtivos() {
        Long quantidade = usuarioService.contarUsuariosAtivos();
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/inativos")
    @Operation(summary = "Contar usuários inativos", description = "Conta usuários inativos")
    public ResponseEntity<Long> contarInativos() {
        Long quantidade = usuarioService.contarUsuariosInativos();
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/departamento")
    @Operation(summary = "Contar por departamento", description = "Conta usuários por departamento")
    public ResponseEntity<Long> contarPorDepartamento(@RequestParam String departamento) {
        Long quantidade = usuarioService.contarUsuariosPorDepartamento(departamento);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/perfil")
    @Operation(summary = "Contar por perfil", description = "Conta usuários por perfil")
    public ResponseEntity<Long> contarPorPerfil(@RequestParam String nomePerfil) {
        Long quantidade = usuarioService.contarUsuariosPorPerfil(nomePerfil);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/bloqueados")
    @Operation(summary = "Buscar usuários bloqueados", description = "Busca usuários bloqueados")
    public ResponseEntity<List<UsuarioDTO>> buscarBloqueados() {
        List<UsuarioDTO> usuarios = usuarioService.buscarUsuariosBloqueados();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/senha-expirada")
    @Operation(summary = "Buscar usuários com senha expirada", description = "Busca usuários com senha expirada")
    public ResponseEntity<List<UsuarioDTO>> buscarComSenhaExpirada() {
        List<UsuarioDTO> usuarios = usuarioService.buscarUsuariosComSenhaExpirada();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/autenticar")
    @Operation(summary = "Autenticar usuário", description = "Valida credenciais de um usuário")
    public ResponseEntity<Boolean> autenticar(@RequestParam String username, @RequestParam String senha) {
        boolean autenticado = usuarioService.validarCredenciais(username, senha);
        return ResponseEntity.ok(autenticado);
    }

    @PostMapping("/login-sucesso")
    @Operation(summary = "Registrar login com sucesso", description = "Registra um login bem-sucedido")
    public ResponseEntity<Void> registrarLoginSucesso(@RequestParam String username) {
        usuarioService.registrarLoginSucesso(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login-falha")
    @Operation(summary = "Registrar login com falha", description = "Registra uma tentativa de login falhada")
    public ResponseEntity<Void> registrarLoginFalha(@RequestParam String username) {
        usuarioService.registrarLoginFalha(username);
        return ResponseEntity.ok().build();
    }
}
