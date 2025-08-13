package com.manus.patrimonio.controller;

import com.manus.patrimonio.dto.ManutencaoDTO;
import com.manus.patrimonio.enums.StatusManutencao;
import com.manus.patrimonio.enums.TipoManutencao;
import com.manus.patrimonio.service.ManutencaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/manutencoes")
@Tag(name = "Manutenções", description = "API para gerenciamento de manutenções")
public class ManutencaoController {

    @Autowired
    private ManutencaoService manutencaoService;

    @PostMapping
    @Operation(summary = "Criar manutenção", description = "Cria uma nova manutenção")
    public ResponseEntity<ManutencaoDTO> criar(@Valid @RequestBody ManutencaoDTO manutencaoDTO) {
        ManutencaoDTO manutencaoCriada = manutencaoService.criar(manutencaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(manutencaoCriada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar manutenção por ID", description = "Retorna uma manutenção específica")
    public ResponseEntity<ManutencaoDTO> buscarPorId(@PathVariable Long id) {
        ManutencaoDTO manutencao = manutencaoService.buscarPorId(id);
        return ResponseEntity.ok(manutencao);
    }

    @GetMapping
    @Operation(summary = "Listar manutenções", description = "Retorna lista paginada de manutenções")
    public ResponseEntity<Page<ManutencaoDTO>> listar(Pageable pageable) {
        Page<ManutencaoDTO> manutencoes = manutencaoService.buscarTodos(pageable);
        return ResponseEntity.ok(manutencoes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar manutenção", description = "Atualiza uma manutenção existente")
    public ResponseEntity<ManutencaoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ManutencaoDTO manutencaoDTO) {
        ManutencaoDTO manutencaoAtualizada = manutencaoService.atualizar(id, manutencaoDTO);
        return ResponseEntity.ok(manutencaoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir manutenção", description = "Exclui uma manutenção")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        manutencaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/finalizar")
    @Operation(summary = "Finalizar manutenção", description = "Finaliza uma manutenção ativa")
    public ResponseEntity<ManutencaoDTO> finalizar(@PathVariable Long id, 
                                                 @RequestParam LocalDate dataFim,
                                                 @RequestParam String solucaoAplicada,
                                                 @RequestParam String recomendacoes) {
        ManutencaoDTO manutencaoFinalizada = manutencaoService.finalizar(id, dataFim, solucaoAplicada, recomendacoes);
        return ResponseEntity.ok(manutencaoFinalizada);
    }

    @PostMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar manutenção", description = "Cancela uma manutenção ativa")
    public ResponseEntity<ManutencaoDTO> cancelar(@PathVariable Long id, @RequestParam String motivo) {
        ManutencaoDTO manutencaoCancelada = manutencaoService.cancelar(id, motivo);
        return ResponseEntity.ok(manutencaoCancelada);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Alterar status", description = "Altera o status de uma manutenção")
    public ResponseEntity<ManutencaoDTO> alterarStatus(@PathVariable Long id, @RequestParam StatusManutencao novoStatus) {
        ManutencaoDTO manutencaoAtualizada = manutencaoService.alterarStatus(id, novoStatus);
        return ResponseEntity.ok(manutencaoAtualizada);
    }

    @GetMapping("/bem/{bemId}")
    @Operation(summary = "Buscar por bem", description = "Busca manutenções de um bem específico")
    public ResponseEntity<Page<ManutencaoDTO>> buscarPorBem(@PathVariable Long bemId, Pageable pageable) {
        Page<ManutencaoDTO> manutencoes = manutencaoService.buscarPorBem(bemId, pageable);
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Buscar por status", description = "Busca manutenções por status")
    public ResponseEntity<Page<ManutencaoDTO>> buscarPorStatus(@PathVariable StatusManutencao status, Pageable pageable) {
        Page<ManutencaoDTO> manutencoes = manutencaoService.buscarPorStatus(status, pageable);
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Buscar por tipo", description = "Busca manutenções por tipo")
    public ResponseEntity<Page<ManutencaoDTO>> buscarPorTipo(@PathVariable TipoManutencao tipo, Pageable pageable) {
        Page<ManutencaoDTO> manutencoes = manutencaoService.buscarPorTipo(tipo, pageable);
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/fornecedor")
    @Operation(summary = "Buscar por fornecedor", description = "Busca manutenções por fornecedor")
    public ResponseEntity<Page<ManutencaoDTO>> buscarPorFornecedor(@RequestParam String fornecedor, Pageable pageable) {
        Page<ManutencaoDTO> manutencoes = manutencaoService.buscarPorFornecedor(fornecedor, pageable);
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/responsavel")
    @Operation(summary = "Buscar por responsável", description = "Busca manutenções por responsável")
    public ResponseEntity<Page<ManutencaoDTO>> buscarPorResponsavel(@RequestParam String responsavel, Pageable pageable) {
        Page<ManutencaoDTO> manutencoes = manutencaoService.buscarPorResponsavel(responsavel, pageable);
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/periodo")
    @Operation(summary = "Buscar por período", description = "Busca manutenções por período")
    public ResponseEntity<Page<ManutencaoDTO>> buscarPorPeriodo(@RequestParam LocalDate dataInicio,
                                                               @RequestParam LocalDate dataFim,
                                                               Pageable pageable) {
        Page<ManutencaoDTO> manutencoes = manutencaoService.buscarPorPeriodo(dataInicio, dataFim, pageable);
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/custo-maximo")
    @Operation(summary = "Buscar por custo máximo", description = "Busca manutenções com custo até o valor especificado")
    public ResponseEntity<Page<ManutencaoDTO>> buscarPorCustoMaximo(@RequestParam BigDecimal custoMaximo, Pageable pageable) {
        Page<ManutencaoDTO> manutencoes = manutencaoService.buscarPorCustoMaximo(custoMaximo, pageable);
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/localizacao/{localizacaoId}")
    @Operation(summary = "Buscar por localização", description = "Busca manutenções por localização")
    public ResponseEntity<Page<ManutencaoDTO>> buscarPorLocalizacao(@PathVariable Long localizacaoId, Pageable pageable) {
        Page<ManutencaoDTO> manutencoes = manutencaoService.buscarPorLocalizacao(localizacaoId, pageable);
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/estatisticas/status/{status}")
    @Operation(summary = "Contar por status", description = "Conta manutenções por status")
    public ResponseEntity<Long> contarPorStatus(@PathVariable StatusManutencao status) {
        Long quantidade = manutencaoService.contarManutencoesPorStatus(status);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/tipo/{tipo}")
    @Operation(summary = "Contar por tipo", description = "Conta manutenções por tipo")
    public ResponseEntity<Long> contarPorTipo(@PathVariable TipoManutencao tipo) {
        Long quantidade = manutencaoService.contarManutencoesPorTipo(tipo);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/bem/{bemId}")
    @Operation(summary = "Contar por bem", description = "Conta manutenções por bem")
    public ResponseEntity<Long> contarPorBem(@PathVariable Long bemId) {
        Long quantidade = manutencaoService.contarManutencoesPorBem(bemId);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/atrasadas")
    @Operation(summary = "Buscar atrasadas", description = "Busca manutenções atrasadas")
    public ResponseEntity<List<ManutencaoDTO>> buscarAtrasadas() {
        List<ManutencaoDTO> manutencoes = manutencaoService.buscarManutencoesAtrasadas();
        return ResponseEntity.ok(manutencoes);
    }

    @GetMapping("/urgentes")
    @Operation(summary = "Buscar urgentes", description = "Busca manutenções urgentes")
    public ResponseEntity<List<ManutencaoDTO>> buscarUrgentes() {
        List<ManutencaoDTO> manutencoes = manutencaoService.buscarManutencoesUrgentes();
        return ResponseEntity.ok(manutencoes);
    }
}
