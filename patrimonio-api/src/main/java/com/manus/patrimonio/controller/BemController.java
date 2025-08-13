package com.manus.patrimonio.controller;

import com.manus.patrimonio.dto.BemDTO;
import com.manus.patrimonio.enums.CondicaoBem;
import com.manus.patrimonio.enums.StatusBem;
import com.manus.patrimonio.service.BemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bens")
@Tag(name = "Bens Patrimoniais", description = "Operações relacionadas aos bens patrimoniais")
public class BemController {

    @Autowired
    private BemService bemService;

    @Operation(summary = "Listar todos os bens", description = "Retorna uma lista paginada de todos os bens patrimoniais")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de bens retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<Page<BemDTO>> listarTodos(
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        Page<BemDTO> bens = bemService.buscarTodos(pageable);
        return ResponseEntity.ok(bens);
    }

    @Operation(summary = "Buscar bem por ID", description = "Retorna um bem específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bem encontrado"),
        @ApiResponse(responseCode = "404", description = "Bem não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BemDTO> buscarPorId(
            @Parameter(description = "ID do bem") @PathVariable Long id) {
        BemDTO bem = bemService.buscarPorId(id);
        return ResponseEntity.ok(bem);
    }

    @Operation(summary = "Buscar bem por número de série", description = "Retorna um bem específico pelo seu número de série")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bem encontrado"),
        @ApiResponse(responseCode = "404", description = "Bem não encontrado")
    })
    @GetMapping("/numero-serie/{numeroSerie}")
    public ResponseEntity<BemDTO> buscarPorNumeroSerie(
            @Parameter(description = "Número de série do bem") @PathVariable String numeroSerie) {
        BemDTO bem = bemService.buscarPorNumeroSerie(numeroSerie);
        return ResponseEntity.ok(bem);
    }

    @Operation(summary = "Buscar bens por status", description = "Retorna bens filtrados por status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<BemDTO>> buscarPorStatus(
            @Parameter(description = "Status do bem") @PathVariable StatusBem status,
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        Page<BemDTO> bens = bemService.buscarPorStatus(status, pageable);
        return ResponseEntity.ok(bens);
    }

    @Operation(summary = "Buscar bens por condição", description = "Retorna bens filtrados por condição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/condicao/{condicao}")
    public ResponseEntity<Page<BemDTO>> buscarPorCondicao(
            @Parameter(description = "Condição do bem") @PathVariable CondicaoBem condicao,
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        Page<BemDTO> bens = bemService.buscarPorCondicao(condicao, pageable);
        return ResponseEntity.ok(bens);
    }

    @Operation(summary = "Buscar bens por categoria", description = "Retorna bens de uma categoria específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<Page<BemDTO>> buscarPorCategoria(
            @Parameter(description = "ID da categoria") @PathVariable Long categoriaId,
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        Page<BemDTO> bens = bemService.buscarPorCategoria(categoriaId, pageable);
        return ResponseEntity.ok(bens);
    }

    @Operation(summary = "Buscar bens por localização", description = "Retorna bens de uma localização específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/localizacao/{localizacaoId}")
    public ResponseEntity<Page<BemDTO>> buscarPorLocalizacao(
            @Parameter(description = "ID da localização") @PathVariable Long localizacaoId,
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        Page<BemDTO> bens = bemService.buscarPorLocalizacao(localizacaoId, pageable);
        return ResponseEntity.ok(bens);
    }

    @Operation(summary = "Buscar bens por texto", description = "Busca bens que contenham o texto no nome, descrição, número de série ou observações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/buscar")
    public ResponseEntity<Page<BemDTO>> buscarPorTexto(
            @Parameter(description = "Termo de busca") @RequestParam String termo,
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        Page<BemDTO> bens = bemService.buscarPorTexto(termo, pageable);
        return ResponseEntity.ok(bens);
    }

    @Operation(summary = "Buscar bens por faixa de valor", description = "Retorna bens dentro de uma faixa de valor de aquisição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/valor")
    public ResponseEntity<Page<BemDTO>> buscarPorFaixaValor(
            @Parameter(description = "Valor mínimo") @RequestParam BigDecimal valorMinimo,
            @Parameter(description = "Valor máximo") @RequestParam BigDecimal valorMaximo,
            @PageableDefault(size = 20, sort = "valorAquisicao") Pageable pageable) {
        Page<BemDTO> bens = bemService.buscarPorFaixaValor(valorMinimo, valorMaximo, pageable);
        return ResponseEntity.ok(bens);
    }

    @Operation(summary = "Buscar bens por faixa de data", description = "Retorna bens dentro de uma faixa de data de aquisição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/data")
    public ResponseEntity<Page<BemDTO>> buscarPorFaixaData(
            @Parameter(description = "Data de início") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @Parameter(description = "Data de fim") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @PageableDefault(size = 20, sort = "dataAquisicao") Pageable pageable) {
        Page<BemDTO> bens = bemService.buscarPorFaixaData(dataInicio, dataFim, pageable);
        return ResponseEntity.ok(bens);
    }

    @Operation(summary = "Buscar bens com filtros", description = "Busca bens aplicando múltiplos filtros")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/filtros")
    public ResponseEntity<Page<BemDTO>> buscarComFiltros(
            @Parameter(description = "ID da categoria") @RequestParam(required = false) Long categoriaId,
            @Parameter(description = "ID da localização") @RequestParam(required = false) Long localizacaoId,
            @Parameter(description = "Status do bem") @RequestParam(required = false) StatusBem status,
            @Parameter(description = "Condição do bem") @RequestParam(required = false) CondicaoBem condicao,
            @Parameter(description = "Valor mínimo") @RequestParam(required = false) BigDecimal valorMinimo,
            @Parameter(description = "Valor máximo") @RequestParam(required = false) BigDecimal valorMaximo,
            @Parameter(description = "Data de início") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @Parameter(description = "Data de fim") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        
        Page<BemDTO> bens = bemService.buscarComFiltros(categoriaId, localizacaoId, status, condicao,
                                                       valorMinimo, valorMaximo, dataInicio, dataFim, pageable);
        return ResponseEntity.ok(bens);
    }

    @Operation(summary = "Criar novo bem", description = "Cria um novo bem patrimonial")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Bem criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou bem já existe")
    })
    @PostMapping
    public ResponseEntity<BemDTO> criar(@Valid @RequestBody BemDTO bemDTO) {
        BemDTO bemCriado = bemService.criar(bemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bemCriado);
    }

    @Operation(summary = "Atualizar bem", description = "Atualiza um bem patrimonial existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bem atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Bem não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BemDTO> atualizar(
            @Parameter(description = "ID do bem") @PathVariable Long id,
            @Valid @RequestBody BemDTO bemDTO) {
        BemDTO bemAtualizado = bemService.atualizar(id, bemDTO);
        return ResponseEntity.ok(bemAtualizado);
    }

    @Operation(summary = "Atualizar status do bem", description = "Atualiza apenas o status de um bem")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Bem não encontrado")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<BemDTO> atualizarStatus(
            @Parameter(description = "ID do bem") @PathVariable Long id,
            @Parameter(description = "Novo status") @RequestParam StatusBem status) {
        BemDTO bemAtualizado = bemService.atualizarStatus(id, status);
        return ResponseEntity.ok(bemAtualizado);
    }

    @Operation(summary = "Excluir bem", description = "Exclui um bem patrimonial (apenas se não estiver ativo)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Bem excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Bem não encontrado"),
        @ApiResponse(responseCode = "400", description = "Bem está ativo e não pode ser excluído")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @Parameter(description = "ID do bem") @PathVariable Long id) {
        bemService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Contar bens por status", description = "Retorna a quantidade de bens por status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contagem realizada com sucesso")
    })
    @GetMapping("/estatisticas/status")
    public ResponseEntity<List<Object[]>> contarBensPorStatus() {
        List<Object[]> estatisticas = bemService.contarBensPorStatus();
        return ResponseEntity.ok(estatisticas);
    }

    @Operation(summary = "Contar bens por condição", description = "Retorna a quantidade de bens por condição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contagem realizada com sucesso")
    })
    @GetMapping("/estatisticas/condicao")
    public ResponseEntity<List<Object[]>> contarBensPorCondicao() {
        List<Object[]> estatisticas = bemService.contarBensPorCondicao();
        return ResponseEntity.ok(estatisticas);
    }

    @Operation(summary = "Somar valor por status", description = "Retorna o valor total dos bens por status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Soma realizada com sucesso")
    })
    @GetMapping("/estatisticas/valor-status")
    public ResponseEntity<List<Object[]>> somarValorPorStatus() {
        List<Object[]> estatisticas = bemService.somarValorPorStatus();
        return ResponseEntity.ok(estatisticas);
    }

    @Operation(summary = "Listar bens recentes", description = "Retorna os 10 bens mais recentemente adquiridos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/recentes")
    public ResponseEntity<List<BemDTO>> listarBensRecentes() {
        List<BemDTO> bens = bemService.buscarBensRecentes();
        return ResponseEntity.ok(bens);
    }

    @Operation(summary = "Listar bens sem movimentação", description = "Retorna bens que nunca foram movimentados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/sem-movimentacao")
    public ResponseEntity<List<BemDTO>> listarBensSemMovimentacao() {
        List<BemDTO> bens = bemService.buscarBensSemMovimentacao();
        return ResponseEntity.ok(bens);
    }
}

