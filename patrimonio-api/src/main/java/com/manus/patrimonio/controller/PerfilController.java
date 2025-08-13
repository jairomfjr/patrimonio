package com.manus.patrimonio.controller;

import com.manus.patrimonio.dto.PerfilDTO;
import com.manus.patrimonio.service.PerfilService;
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
@RequestMapping("/perfis")
@Tag(name = "Perfis", description = "API para gerenciamento de perfis de usuário")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    @Operation(summary = "Criar perfil", description = "Cria um novo perfil")
    public ResponseEntity<PerfilDTO> criar(@Valid @RequestBody PerfilDTO perfilDTO) {
        PerfilDTO perfilCriado = perfilService.criar(perfilDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(perfilCriado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar perfil por ID", description = "Retorna um perfil específico")
    public ResponseEntity<PerfilDTO> buscarPorId(@PathVariable Long id) {
        PerfilDTO perfil = perfilService.buscarPorId(id);
        return ResponseEntity.ok(perfil);
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Buscar perfil por nome", description = "Retorna um perfil por nome")
    public ResponseEntity<PerfilDTO> buscarPorNome(@PathVariable String nome) {
        PerfilDTO perfil = perfilService.buscarPorNome(nome);
        return ResponseEntity.ok(perfil);
    }

    @GetMapping
    @Operation(summary = "Listar perfis", description = "Retorna lista paginada de perfis")
    public ResponseEntity<Page<PerfilDTO>> listar(Pageable pageable) {
        Page<PerfilDTO> perfis = perfilService.buscarTodos(pageable);
        return ResponseEntity.ok(perfis);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar perfil", description = "Atualiza um perfil existente")
    public ResponseEntity<PerfilDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PerfilDTO perfilDTO) {
        PerfilDTO perfilAtualizado = perfilService.atualizar(id, perfilDTO);
        return ResponseEntity.ok(perfilAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir perfil", description = "Exclui um perfil")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        perfilService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/ativar")
    @Operation(summary = "Ativar perfil", description = "Ativa um perfil inativo")
    public ResponseEntity<PerfilDTO> ativar(@PathVariable Long id) {
        PerfilDTO perfilAtivado = perfilService.ativar(id);
        return ResponseEntity.ok(perfilAtivado);
    }

    @PostMapping("/{id}/desativar")
    @Operation(summary = "Desativar perfil", description = "Desativa um perfil ativo")
    public ResponseEntity<PerfilDTO> desativar(@PathVariable Long id) {
        PerfilDTO perfilDesativado = perfilService.desativar(id);
        return ResponseEntity.ok(perfilDesativado);
    }

    @GetMapping("/buscar/nivel-acesso")
    @Operation(summary = "Buscar por nível de acesso", description = "Busca perfis por nível de acesso")
    public ResponseEntity<Page<PerfilDTO>> buscarPorNivelAcesso(@RequestParam Integer nivelMinimo,
                                                               @RequestParam Integer nivelMaximo,
                                                               Pageable pageable) {
        Page<PerfilDTO> perfis = perfilService.buscarPorNivelAcesso(nivelMinimo, nivelMaximo, pageable);
        return ResponseEntity.ok(perfis);
    }

    @GetMapping("/buscar/permissao")
    @Operation(summary = "Buscar por permissão", description = "Busca perfis por permissão")
    public ResponseEntity<Page<PerfilDTO>> buscarPorPermissao(@RequestParam String permissao, Pageable pageable) {
        Page<PerfilDTO> perfis = perfilService.buscarPorPermissao(permissao, pageable);
        return ResponseEntity.ok(perfis);
    }

    @GetMapping("/buscar/status")
    @Operation(summary = "Buscar por status", description = "Busca perfis por status")
    public ResponseEntity<Page<PerfilDTO>> buscarPorStatus(@RequestParam Boolean ativo, Pageable pageable) {
        Page<PerfilDTO> perfis = perfilService.buscarPorStatus(ativo, pageable);
        return ResponseEntity.ok(perfis);
    }

    @GetMapping("/estatisticas/ativos")
    @Operation(summary = "Contar perfis ativos", description = "Conta perfis ativos")
    public ResponseEntity<Long> contarAtivos() {
        Long quantidade = perfilService.contarPerfisAtivos();
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/inativos")
    @Operation(summary = "Contar perfis inativos", description = "Conta perfis inativos")
    public ResponseEntity<Long> contarInativos() {
        Long quantidade = perfilService.contarPerfisInativos();
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/sistema")
    @Operation(summary = "Buscar perfis do sistema", description = "Busca perfis do sistema")
    public ResponseEntity<List<PerfilDTO>> buscarPerfisDoSistema() {
        List<PerfilDTO> perfis = perfilService.buscarPerfisDoSistema();
        return ResponseEntity.ok(perfis);
    }

    @GetMapping("/customizados")
    @Operation(summary = "Buscar perfis customizados", description = "Busca perfis customizados")
    public ResponseEntity<List<PerfilDTO>> buscarPerfisCustomizados() {
        List<PerfilDTO> perfis = perfilService.buscarPerfisCustomizados();
        return ResponseEntity.ok(perfis);
    }
}
