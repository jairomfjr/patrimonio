package com.manus.patrimonio.controller;

import com.manus.patrimonio.dto.MovimentacaoDTO;
import com.manus.patrimonio.enums.TipoMovimentacao;
import com.manus.patrimonio.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movimentacoes")
@Tag(name = "Movimentações", description = "API para gerenciamento de movimentações de bens patrimoniais")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    @Operation(summary = "Listar movimentações", description = "Retorna uma lista paginada de todas as movimentações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Movimentações listadas com sucesso",
                    content = @Content(schema = @Schema(implementation = Page.class))),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Page<MovimentacaoDTO>> listarMovimentacoes(
            @Parameter(description = "Configurações de paginação") Pageable pageable) {
        Page<MovimentacaoDTO> movimentacoes = movimentacaoService.buscarTodas(pageable);
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar movimentação por ID", description = "Retorna uma movimentação específica pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Movimentação encontrada com sucesso",
                    content = @Content(schema = @Schema(implementation = MovimentacaoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Movimentação não encontrada"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<MovimentacaoDTO> buscarPorId(
            @Parameter(description = "ID da movimentação") @PathVariable Long id) {
        MovimentacaoDTO movimentacao = movimentacaoService.buscarPorId(id);
        return ResponseEntity.ok(movimentacao);
    }

    @PostMapping
    @Operation(summary = "Criar movimentação", description = "Cria uma nova movimentação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Movimentação criada com sucesso",
                    content = @Content(schema = @Schema(implementation = MovimentacaoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<MovimentacaoDTO> criarMovimentacao(
            @Parameter(description = "Dados da movimentação") @Valid @RequestBody MovimentacaoDTO movimentacaoDTO) {
        MovimentacaoDTO movimentacaoCriada = movimentacaoService.criar(movimentacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoCriada);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar movimentação", description = "Atualiza uma movimentação existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Movimentação atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = MovimentacaoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Movimentação não encontrada"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<MovimentacaoDTO> atualizarMovimentacao(
            @Parameter(description = "ID da movimentação") @PathVariable Long id,
            @Parameter(description = "Dados atualizados da movimentação") @Valid @RequestBody MovimentacaoDTO movimentacaoDTO) {
        MovimentacaoDTO movimentacaoAtualizada = movimentacaoService.atualizar(id, movimentacaoDTO);
        return ResponseEntity.ok(movimentacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover movimentação", description = "Remove uma movimentação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Movimentação removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Movimentação não encontrada"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Void> removerMovimentacao(
            @Parameter(description = "ID da movimentação") @PathVariable Long id) {
        movimentacaoService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bem/{bemId}")
    @Operation(summary = "Buscar movimentações por bem", description = "Retorna movimentações filtradas por bem")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Movimentações encontradas com sucesso",
                    content = @Content(schema = @Schema(implementation = List.class))),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<List<MovimentacaoDTO>> buscarPorBem(
            @Parameter(description = "ID do bem") @PathVariable Long bemId) {
        List<MovimentacaoDTO> movimentacoes = movimentacaoService.buscarPorBem(bemId);
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Buscar movimentações por tipo", description = "Retorna movimentações filtradas por tipo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Movimentações encontradas com sucesso",
                    content = @Content(schema = @Schema(implementation = List.class))),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<List<MovimentacaoDTO>> buscarPorTipo(
            @Parameter(description = "Tipo da movimentação") @PathVariable TipoMovimentacao tipo) {
        List<MovimentacaoDTO> movimentacoes = movimentacaoService.buscarPorTipo(tipo);
        return ResponseEntity.ok(movimentacoes);
    }

    @GetMapping("/responsavel/{responsavel}")
    @Operation(summary = "Buscar movimentações por responsável", description = "Retorna movimentações filtradas por responsável")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Movimentações encontradas com sucesso",
                    content = @Content(schema = @Schema(implementation = List.class))),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<List<MovimentacaoDTO>> buscarPorResponsavel(
            @Parameter(description = "Nome do responsável") @PathVariable String responsavel) {
        List<MovimentacaoDTO> movimentacoes = movimentacaoService.buscarPorResponsavel(responsavel);
        return ResponseEntity.ok(movimentacoes);
    }
}
