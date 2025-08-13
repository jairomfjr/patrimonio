package com.manus.patrimonio.controller;

import com.manus.patrimonio.dto.BaixaDTO;
import com.manus.patrimonio.service.BaixaService;
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
@RequestMapping("/baixas")
@Tag(name = "Baixas", description = "API para gerenciamento de baixas de bens")
public class BaixaController {

    @Autowired
    private BaixaService baixaService;

    @PostMapping
    @Operation(summary = "Criar baixa", description = "Cria uma nova baixa de bem")
    public ResponseEntity<BaixaDTO> criar(@Valid @RequestBody BaixaDTO baixaDTO) {
        BaixaDTO baixaCriada = baixaService.criar(baixaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(baixaCriada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar baixa por ID", description = "Retorna uma baixa específica")
    public ResponseEntity<BaixaDTO> buscarPorId(@PathVariable Long id) {
        BaixaDTO baixa = baixaService.buscarPorId(id);
        return ResponseEntity.ok(baixa);
    }

    @GetMapping
    @Operation(summary = "Listar baixas", description = "Retorna lista paginada de baixas")
    public ResponseEntity<Page<BaixaDTO>> listar(Pageable pageable) {
        Page<BaixaDTO> baixas = baixaService.buscarTodas(pageable);
        return ResponseEntity.ok(baixas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar baixa", description = "Atualiza uma baixa existente")
    public ResponseEntity<BaixaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody BaixaDTO baixaDTO) {
        BaixaDTO baixaAtualizada = baixaService.atualizar(id, baixaDTO);
        return ResponseEntity.ok(baixaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir baixa", description = "Exclui uma baixa")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        baixaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/aprovar")
    @Operation(summary = "Aprovar baixa", description = "Aprova uma baixa pendente")
    public ResponseEntity<BaixaDTO> aprovar(@PathVariable Long id,
                                          @RequestParam String aprovadoPor,
                                          @RequestParam LocalDate dataAprovacao) {
        BaixaDTO baixaAprovada = baixaService.aprovar(id, aprovadoPor, dataAprovacao);
        return ResponseEntity.ok(baixaAprovada);
    }

    @PostMapping("/{id}/venda")
    @Operation(summary = "Registrar venda", description = "Registra a venda de um bem baixado")
    public ResponseEntity<BaixaDTO> registrarVenda(@PathVariable Long id,
                                                 @RequestParam String comprador,
                                                 @RequestParam BigDecimal valorVenda,
                                                 @RequestParam LocalDate dataVenda) {
        BaixaDTO baixaVendida = baixaService.registrarVenda(id, comprador, valorVenda, dataVenda);
        return ResponseEntity.ok(baixaVendida);
    }

    @GetMapping("/bem/{bemId}")
    @Operation(summary = "Buscar por bem", description = "Busca baixas de um bem específico")
    public ResponseEntity<Page<BaixaDTO>> buscarPorBem(@PathVariable Long bemId, Pageable pageable) {
        Page<BaixaDTO> baixas = baixaService.buscarPorBem(bemId, pageable);
        return ResponseEntity.ok(baixas);
    }

    @GetMapping("/motivo")
    @Operation(summary = "Buscar por motivo", description = "Busca baixas por motivo")
    public ResponseEntity<Page<BaixaDTO>> buscarPorMotivo(@RequestParam String motivo, Pageable pageable) {
        Page<BaixaDTO> baixas = baixaService.buscarPorMotivo(motivo, pageable);
        return ResponseEntity.ok(baixas);
    }

    @GetMapping("/responsavel")
    @Operation(summary = "Buscar por responsável", description = "Busca baixas por responsável")
    public ResponseEntity<Page<BaixaDTO>> buscarPorResponsavel(@RequestParam String responsavel, Pageable pageable) {
        Page<BaixaDTO> baixas = baixaService.buscarPorResponsavel(responsavel, pageable);
        return ResponseEntity.ok(baixas);
    }

    @GetMapping("/periodo")
    @Operation(summary = "Buscar por período", description = "Busca baixas por período")
    public ResponseEntity<Page<BaixaDTO>> buscarPorPeriodo(@RequestParam LocalDate dataInicio,
                                                          @RequestParam LocalDate dataFim,
                                                          Pageable pageable) {
        Page<BaixaDTO> baixas = baixaService.buscarPorPeriodo(dataInicio, dataFim, pageable);
        return ResponseEntity.ok(baixas);
    }

    @GetMapping("/valor-residual")
    @Operation(summary = "Buscar por valor residual mínimo", description = "Busca baixas com valor residual acima do especificado")
    public ResponseEntity<Page<BaixaDTO>> buscarPorValorResidualMinimo(@RequestParam BigDecimal valorMinimo, Pageable pageable) {
        Page<BaixaDTO> baixas = baixaService.buscarPorValorResidualMinimo(valorMinimo, pageable);
        return ResponseEntity.ok(baixas);
    }

    @GetMapping("/processo-administrativo")
    @Operation(summary = "Buscar por processo administrativo", description = "Busca baixas por processo administrativo")
    public ResponseEntity<Page<BaixaDTO>> buscarPorProcessoAdministrativo(@RequestParam String processo, Pageable pageable) {
        Page<BaixaDTO> baixas = baixaService.buscarPorProcessoAdministrativo(processo, pageable);
        return ResponseEntity.ok(baixas);
    }

    @GetMapping("/destino-final")
    @Operation(summary = "Buscar por destino final", description = "Busca baixas por destino final")
    public ResponseEntity<Page<BaixaDTO>> buscarPorDestinoFinal(@RequestParam String destino, Pageable pageable) {
        Page<BaixaDTO> baixas = baixaService.buscarPorDestinoFinal(destino, pageable);
        return ResponseEntity.ok(baixas);
    }

    @GetMapping("/ganho-financeiro")
    @Operation(summary = "Buscar por ganho financeiro mínimo", description = "Busca baixas com ganho financeiro acima do especificado")
    public ResponseEntity<Page<BaixaDTO>> buscarPorGanhoFinanceiroMinimo(@RequestParam BigDecimal ganhoMinimo, Pageable pageable) {
        Page<BaixaDTO> baixas = baixaService.buscarPorGanhoFinanceiroMinimo(ganhoMinimo, pageable);
        return ResponseEntity.ok(baixas);
    }

    @GetMapping("/estatisticas/valor-total")
    @Operation(summary = "Calcular valor total", description = "Calcula o valor total das baixas")
    public ResponseEntity<BigDecimal> calcularValorTotal() {
        BigDecimal valorTotal = baixaService.calcularValorTotalBaixas();
        return ResponseEntity.ok(valorTotal);
    }

    @GetMapping("/estatisticas/valor-medio")
    @Operation(summary = "Calcular valor médio", description = "Calcula o valor médio das baixas")
    public ResponseEntity<BigDecimal> calcularValorMedio() {
        BigDecimal valorMedio = baixaService.calcularValorMedioBaixas();
        return ResponseEntity.ok(valorMedio);
    }

    @GetMapping("/estatisticas/periodo")
    @Operation(summary = "Contar por período", description = "Conta baixas por período")
    public ResponseEntity<Long> contarPorPeriodo(@RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim) {
        Long quantidade = baixaService.contarBaixasPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/aprovadas")
    @Operation(summary = "Contar aprovadas", description = "Conta baixas aprovadas")
    public ResponseEntity<Long> contarAprovadas() {
        Long quantidade = baixaService.contarBaixasAprovadas();
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/vendidas")
    @Operation(summary = "Contar vendidas", description = "Conta baixas vendidas")
    public ResponseEntity<Long> contarVendidas() {
        Long quantidade = baixaService.contarBaixasVendidas();
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/pendentes-aprovacao")
    @Operation(summary = "Buscar pendentes de aprovação", description = "Busca baixas pendentes de aprovação")
    public ResponseEntity<List<BaixaDTO>> buscarPendentesAprovacao() {
        List<BaixaDTO> baixas = baixaService.buscarBaixasPendentesAprovacao();
        return ResponseEntity.ok(baixas);
    }

    @GetMapping("/aprovadas-pendentes-venda")
    @Operation(summary = "Buscar aprovadas pendentes de venda", description = "Busca baixas aprovadas pendentes de venda")
    public ResponseEntity<List<BaixaDTO>> buscarAprovadasPendentesVenda() {
        List<BaixaDTO> baixas = baixaService.buscarBaixasAprovadasPendentesVenda();
        return ResponseEntity.ok(baixas);
    }
}
