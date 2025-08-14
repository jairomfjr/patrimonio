package com.manus.patrimonio.controller;

import com.manus.patrimonio.dto.InventarioDTO;
import com.manus.patrimonio.enums.StatusInventario;
import com.manus.patrimonio.service.InventarioService;
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
@RequestMapping("/api/v1/inventario")
@Tag(name = "Inventário", description = "API para gerenciamento de inventários patrimoniais")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    @Operation(summary = "Listar inventários", description = "Retorna uma lista paginada de todos os inventários")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventários listados com sucesso",
                    content = @Content(schema = @Schema(implementation = Page.class))),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Page<InventarioDTO>> listarInventarios(
            @Parameter(description = "Configurações de paginação") Pageable pageable) {
        Page<InventarioDTO> inventarios = inventarioService.buscarTodos(pageable);
        return ResponseEntity.ok(inventarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar inventário por ID", description = "Retorna um inventário específico pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventário encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = InventarioDTO.class))),
        @ApiResponse(responseCode = "404", description = "Inventário não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<InventarioDTO> buscarPorId(
            @Parameter(description = "ID do inventário") @PathVariable Long id) {
        InventarioDTO inventario = inventarioService.buscarPorId(id);
        return ResponseEntity.ok(inventario);
    }

    @PostMapping
    @Operation(summary = "Criar inventário", description = "Cria um novo inventário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Inventário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = InventarioDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<InventarioDTO> criarInventario(
            @Parameter(description = "Dados do inventário") @Valid @RequestBody InventarioDTO inventarioDTO) {
        InventarioDTO inventarioCriado = inventarioService.criar(inventarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventarioCriado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar inventário", description = "Atualiza um inventário existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventário atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = InventarioDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Inventário não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<InventarioDTO> atualizarInventario(
            @Parameter(description = "ID do inventário") @PathVariable Long id,
            @Parameter(description = "Dados atualizados do inventário") @Valid @RequestBody InventarioDTO inventarioDTO) {
        InventarioDTO inventarioAtualizado = inventarioService.atualizar(id, inventarioDTO);
        return ResponseEntity.ok(inventarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover inventário", description = "Remove um inventário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Inventário removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Inventário não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<Void> removerInventario(
            @Parameter(description = "ID do inventário") @PathVariable Long id) {
        inventarioService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Buscar inventários por status", description = "Retorna inventários filtrados por status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventários encontrados com sucesso",
                    content = @Content(schema = @Schema(implementation = List.class))),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<List<InventarioDTO>> buscarPorStatus(
            @Parameter(description = "Status do inventário") @PathVariable StatusInventario status) {
        List<InventarioDTO> inventarios = inventarioService.buscarPorStatus(status);
        return ResponseEntity.ok(inventarios);
    }

    @GetMapping("/responsavel/{responsavel}")
    @Operation(summary = "Buscar inventários por responsável", description = "Retorna inventários filtrados por responsável")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventários encontrados com sucesso",
                    content = @Content(schema = @Schema(implementation = List.class))),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<List<InventarioDTO>> buscarPorResponsavel(
            @Parameter(description = "Nome do responsável") @PathVariable String responsavel) {
        List<InventarioDTO> inventarios = inventarioService.buscarPorResponsavel(responsavel);
        return ResponseEntity.ok(inventarios);
    }

    @GetMapping("/em-andamento")
    @Operation(summary = "Buscar inventários em andamento", description = "Retorna todos os inventários em andamento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventários encontrados com sucesso",
                    content = @Content(schema = @Schema(implementation = List.class))),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<List<InventarioDTO>> buscarEmAndamento() {
        List<InventarioDTO> inventarios = inventarioService.buscarEmAndamento();
        return ResponseEntity.ok(inventarios);
    }

    @PatchMapping("/{id}/finalizar")
    @Operation(summary = "Finalizar inventário", description = "Marca um inventário como concluído")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventário finalizado com sucesso",
                    content = @Content(schema = @Schema(implementation = InventarioDTO.class))),
        @ApiResponse(responseCode = "404", description = "Inventário não encontrado"),
        @ApiResponse(responseCode = "401", description = "Não autorizado"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    public ResponseEntity<InventarioDTO> finalizarInventario(
            @Parameter(description = "ID do inventário") @PathVariable Long id) {
        InventarioDTO inventarioFinalizado = inventarioService.finalizarInventario(id);
        return ResponseEntity.ok(inventarioFinalizado);
    }
}
