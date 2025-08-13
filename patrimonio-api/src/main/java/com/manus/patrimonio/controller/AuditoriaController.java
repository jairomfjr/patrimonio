package com.manus.patrimonio.controller;

import com.manus.patrimonio.dto.AuditoriaDTO;
import com.manus.patrimonio.service.AuditoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/auditoria")
@Tag(name = "Auditoria", description = "API para consulta de logs de auditoria")
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/{id}")
    @Operation(summary = "Buscar auditoria por ID", description = "Retorna um registro de auditoria específico")
    public ResponseEntity<AuditoriaDTO> buscarPorId(@PathVariable Long id) {
        AuditoriaDTO auditoria = auditoriaService.buscarPorId(id);
        return ResponseEntity.ok(auditoria);
    }

    @GetMapping
    @Operation(summary = "Listar auditorias", description = "Retorna lista paginada de registros de auditoria")
    public ResponseEntity<Page<AuditoriaDTO>> listar(Pageable pageable) {
        Page<AuditoriaDTO> auditorias = auditoriaService.buscarTodas(pageable);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/entidade/{entidade}")
    @Operation(summary = "Buscar por entidade", description = "Busca auditorias por entidade")
    public ResponseEntity<Page<AuditoriaDTO>> buscarPorEntidade(@PathVariable String entidade, Pageable pageable) {
        Page<AuditoriaDTO> auditorias = auditoriaService.buscarPorEntidade(entidade, pageable);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/entidade/{entidade}/id/{entidadeId}")
    @Operation(summary = "Buscar por entidade e ID", description = "Busca auditorias por entidade e ID específico")
    public ResponseEntity<Page<AuditoriaDTO>> buscarPorEntidadeEId(@PathVariable String entidade,
                                                                  @PathVariable Long entidadeId,
                                                                  Pageable pageable) {
        Page<AuditoriaDTO> auditorias = auditoriaService.buscarPorEntidadeEId(entidade, entidadeId, pageable);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/acao/{acao}")
    @Operation(summary = "Buscar por ação", description = "Busca auditorias por tipo de ação")
    public ResponseEntity<Page<AuditoriaDTO>> buscarPorAcao(@PathVariable String acao, Pageable pageable) {
        Page<AuditoriaDTO> auditorias = auditoriaService.buscarPorAcao(acao, pageable);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Buscar por usuário", description = "Busca auditorias por usuário")
    public ResponseEntity<Page<AuditoriaDTO>> buscarPorUsuario(@PathVariable Long usuarioId, Pageable pageable) {
        Page<AuditoriaDTO> auditorias = auditoriaService.buscarPorUsuario(usuarioId, pageable);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/periodo")
    @Operation(summary = "Buscar por período", description = "Busca auditorias por período")
    public ResponseEntity<Page<AuditoriaDTO>> buscarPorPeriodo(@RequestParam LocalDateTime dataInicio,
                                                              @RequestParam LocalDateTime dataFim,
                                                              Pageable pageable) {
        Page<AuditoriaDTO> auditorias = auditoriaService.buscarPorPeriodo(dataInicio, dataFim, pageable);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/entidade/{entidade}/periodo")
    @Operation(summary = "Buscar por entidade e período", description = "Busca auditorias por entidade e período")
    public ResponseEntity<Page<AuditoriaDTO>> buscarPorEntidadeEPeriodo(@PathVariable String entidade,
                                                                       @RequestParam LocalDateTime dataInicio,
                                                                       @RequestParam LocalDateTime dataFim,
                                                                       Pageable pageable) {
        Page<AuditoriaDTO> auditorias = auditoriaService.buscarPorEntidadeEPeriodo(entidade, dataInicio, dataFim, pageable);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/usuario/{usuarioId}/periodo")
    @Operation(summary = "Buscar por usuário e período", description = "Busca auditorias por usuário e período")
    public ResponseEntity<Page<AuditoriaDTO>> buscarPorUsuarioEPeriodo(@PathVariable Long usuarioId,
                                                                      @RequestParam LocalDateTime dataInicio,
                                                                      @RequestParam LocalDateTime dataFim,
                                                                      Pageable pageable) {
        Page<AuditoriaDTO> auditorias = auditoriaService.buscarPorUsuarioEPeriodo(usuarioId, dataInicio, dataFim, pageable);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/acao/{acao}/periodo")
    @Operation(summary = "Buscar por ação e período", description = "Busca auditorias por ação e período")
    public ResponseEntity<Page<AuditoriaDTO>> buscarPorAcaoEPeriodo(@PathVariable String acao,
                                                                   @RequestParam LocalDateTime dataInicio,
                                                                   @RequestParam LocalDateTime dataFim,
                                                                   Pageable pageable) {
        Page<AuditoriaDTO> auditorias = auditoriaService.buscarPorAcaoEPeriodo(acao, dataInicio, dataFim, pageable);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/ip/{ipAddress}")
    @Operation(summary = "Buscar por IP", description = "Busca auditorias por endereço IP")
    public ResponseEntity<List<AuditoriaDTO>> buscarPorIp(@PathVariable String ipAddress) {
        List<AuditoriaDTO> auditorias = auditoriaService.buscarPorIp(ipAddress);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/entidade/{entidade}/acao/{acao}")
    @Operation(summary = "Buscar por entidade e ação", description = "Busca auditorias por entidade e ação")
    public ResponseEntity<Page<AuditoriaDTO>> buscarPorEntidadeEAcao(@PathVariable String entidade,
                                                                    @PathVariable String acao,
                                                                    Pageable pageable) {
        Page<AuditoriaDTO> auditorias = auditoriaService.buscarPorEntidadeEAcao(entidade, acao, pageable);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/usuario/{usuarioId}/acao/{acao}")
    @Operation(summary = "Buscar por usuário e ação", description = "Busca auditorias por usuário e ação")
    public ResponseEntity<Page<AuditoriaDTO>> buscarPorUsuarioEAcao(@PathVariable Long usuarioId,
                                                                   @PathVariable String acao,
                                                                   Pageable pageable) {
        Page<AuditoriaDTO> auditorias = auditoriaService.buscarPorUsuarioEAcao(usuarioId, acao, pageable);
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/criacoes")
    @Operation(summary = "Buscar auditorias de criação", description = "Busca auditorias de criação")
    public ResponseEntity<List<AuditoriaDTO>> buscarCriacoes() {
        List<AuditoriaDTO> auditorias = auditoriaService.buscarAuditoriasCriacao();
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/atualizacoes")
    @Operation(summary = "Buscar auditorias de atualização", description = "Busca auditorias de atualização")
    public ResponseEntity<List<AuditoriaDTO>> buscarAtualizacoes() {
        List<AuditoriaDTO> auditorias = auditoriaService.buscarAuditoriasAtualizacao();
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/exclusoes")
    @Operation(summary = "Buscar auditorias de exclusão", description = "Busca auditorias de exclusão")
    public ResponseEntity<List<AuditoriaDTO>> buscarExclusoes() {
        List<AuditoriaDTO> auditorias = auditoriaService.buscarAuditoriasExclusao();
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/consultas")
    @Operation(summary = "Buscar auditorias de consulta", description = "Busca auditorias de consulta")
    public ResponseEntity<List<AuditoriaDTO>> buscarConsultas() {
        List<AuditoriaDTO> auditorias = auditoriaService.buscarAuditoriasConsulta();
        return ResponseEntity.ok(auditorias);
    }

    @GetMapping("/estatisticas/entidade/{entidade}")
    @Operation(summary = "Contar por entidade", description = "Conta auditorias por entidade")
    public ResponseEntity<Long> contarPorEntidade(@PathVariable String entidade) {
        Long quantidade = auditoriaService.contarPorEntidade(entidade);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/acao/{acao}")
    @Operation(summary = "Contar por ação", description = "Conta auditorias por ação")
    public ResponseEntity<Long> contarPorAcao(@PathVariable String acao) {
        Long quantidade = auditoriaService.contarPorAcao(acao);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/usuario/{usuarioId}")
    @Operation(summary = "Contar por usuário", description = "Conta auditorias por usuário")
    public ResponseEntity<Long> contarPorUsuario(@PathVariable Long usuarioId) {
        Long quantidade = auditoriaService.contarPorUsuario(usuarioId);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/periodo")
    @Operation(summary = "Contar por período", description = "Conta auditorias por período")
    public ResponseEntity<Long> contarPorPeriodo(@RequestParam LocalDateTime dataInicio, @RequestParam LocalDateTime dataFim) {
        Long quantidade = auditoriaService.contarPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/entidade/{entidade}/periodo")
    @Operation(summary = "Contar por entidade e período", description = "Conta auditorias por entidade e período")
    public ResponseEntity<Long> contarPorEntidadeEPeriodo(@PathVariable String entidade,
                                                         @RequestParam LocalDateTime dataInicio,
                                                         @RequestParam LocalDateTime dataFim) {
        Long quantidade = auditoriaService.contarPorEntidadeEPeriodo(entidade, dataInicio, dataFim);
        return ResponseEntity.ok(quantidade);
    }
}
