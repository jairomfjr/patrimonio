package com.manus.patrimonio.controller;

import com.manus.patrimonio.dto.NotificacaoDTO;
import com.manus.patrimonio.service.NotificacaoService;
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
@RequestMapping("/notificacoes")
@Tag(name = "Notificações", description = "API para gerenciamento de notificações")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping
    @Operation(summary = "Criar notificação", description = "Cria uma nova notificação")
    public ResponseEntity<NotificacaoDTO> criar(@Valid @RequestBody NotificacaoDTO notificacaoDTO) {
        NotificacaoDTO notificacaoCriada = notificacaoService.criar(notificacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacaoCriada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar notificação por ID", description = "Retorna uma notificação específica")
    public ResponseEntity<NotificacaoDTO> buscarPorId(@PathVariable Long id) {
        NotificacaoDTO notificacao = notificacaoService.buscarPorId(id);
        return ResponseEntity.ok(notificacao);
    }

    @GetMapping
    @Operation(summary = "Listar notificações", description = "Retorna lista paginada de notificações")
    public ResponseEntity<Page<NotificacaoDTO>> listar(Pageable pageable) {
        Page<NotificacaoDTO> notificacoes = notificacaoService.buscarTodas(pageable);
        return ResponseEntity.ok(notificacoes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar notificação", description = "Atualiza uma notificação existente")
    public ResponseEntity<NotificacaoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody NotificacaoDTO notificacaoDTO) {
        NotificacaoDTO notificacaoAtualizada = notificacaoService.atualizar(id, notificacaoDTO);
        return ResponseEntity.ok(notificacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir notificação", description = "Exclui uma notificação")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        notificacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/marcar-lida")
    @Operation(summary = "Marcar como lida", description = "Marca uma notificação como lida")
    public ResponseEntity<NotificacaoDTO> marcarComoLida(@PathVariable Long id) {
        NotificacaoDTO notificacao = notificacaoService.marcarComoLida(id);
        return ResponseEntity.ok(notificacao);
    }

    @PostMapping("/{id}/marcar-nao-lida")
    @Operation(summary = "Marcar como não lida", description = "Marca uma notificação como não lida")
    public ResponseEntity<NotificacaoDTO> marcarComoNaoLida(@PathVariable Long id) {
        NotificacaoDTO notificacao = notificacaoService.marcarComoNaoLida(id);
        return ResponseEntity.ok(notificacao);
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Buscar por usuário", description = "Busca notificações de um usuário específico")
    public ResponseEntity<Page<NotificacaoDTO>> buscarPorUsuario(@PathVariable Long usuarioId, Pageable pageable) {
        Page<NotificacaoDTO> notificacoes = notificacaoService.buscarPorUsuario(usuarioId, pageable);
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Buscar por tipo", description = "Busca notificações por tipo")
    public ResponseEntity<Page<NotificacaoDTO>> buscarPorTipo(@PathVariable String tipo, Pageable pageable) {
        Page<NotificacaoDTO> notificacoes = notificacaoService.buscarPorTipo(tipo, pageable);
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/status/{lida}")
    @Operation(summary = "Buscar por status", description = "Busca notificações por status (lida/não lida)")
    public ResponseEntity<Page<NotificacaoDTO>> buscarPorStatus(@PathVariable Boolean lida, Pageable pageable) {
        Page<NotificacaoDTO> notificacoes = notificacaoService.buscarPorStatus(lida, pageable);
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/prioridade/{prioridade}")
    @Operation(summary = "Buscar por prioridade", description = "Busca notificações por prioridade")
    public ResponseEntity<Page<NotificacaoDTO>> buscarPorPrioridade(@PathVariable Integer prioridade, Pageable pageable) {
        Page<NotificacaoDTO> notificacoes = notificacaoService.buscarPorPrioridade(prioridade, pageable);
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/estatisticas/usuario/{usuarioId}")
    @Operation(summary = "Contar por usuário", description = "Conta notificações de um usuário")
    public ResponseEntity<Long> contarPorUsuario(@PathVariable Long usuarioId) {
        Long quantidade = notificacaoService.contarNotificacoesPorUsuario(usuarioId);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/tipo/{tipo}")
    @Operation(summary = "Contar por tipo", description = "Conta notificações por tipo")
    public ResponseEntity<Long> contarPorTipo(@PathVariable String tipo) {
        Long quantidade = notificacaoService.contarNotificacoesPorTipo(tipo);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/status/{lida}")
    @Operation(summary = "Contar por status", description = "Conta notificações por status")
    public ResponseEntity<Long> contarPorStatus(@PathVariable Boolean lida) {
        Long quantidade = notificacaoService.contarNotificacoesPorStatus(lida);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/expiradas")
    @Operation(summary = "Buscar expiradas", description = "Busca notificações expiradas")
    public ResponseEntity<List<NotificacaoDTO>> buscarExpiradas() {
        List<NotificacaoDTO> notificacoes = notificacaoService.buscarNotificacoesExpiradas();
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/urgentes")
    @Operation(summary = "Buscar urgentes", description = "Busca notificações urgentes")
    public ResponseEntity<List<NotificacaoDTO>> buscarUrgentes() {
        List<NotificacaoDTO> notificacoes = notificacaoService.buscarNotificacoesUrgentes();
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/pendentes-envio")
    @Operation(summary = "Buscar pendentes de envio", description = "Busca notificações pendentes de envio")
    public ResponseEntity<List<NotificacaoDTO>> buscarPendentesEnvio() {
        List<NotificacaoDTO> notificacoes = notificacaoService.buscarNotificacoesPendentesEnvio();
        return ResponseEntity.ok(notificacoes);
    }
}
