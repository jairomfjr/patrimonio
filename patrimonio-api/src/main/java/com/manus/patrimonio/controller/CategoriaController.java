package com.manus.patrimonio.controller;

import com.manus.patrimonio.dto.CategoriaDTO;
import com.manus.patrimonio.service.CategoriaService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Operações relacionadas às categorias de bens patrimoniais")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Listar todas as categorias", description = "Retorna uma lista paginada de todas as categorias")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> listarTodas(
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        Page<CategoriaDTO> categorias = categoriaService.buscarTodas(pageable);
        return ResponseEntity.ok(categorias);
    }

    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria específica pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(
            @Parameter(description = "ID da categoria") @PathVariable Long id) {
        CategoriaDTO categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @Operation(summary = "Buscar categoria por nome", description = "Retorna uma categoria específica pelo seu nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping("/nome/{nome}")
    public ResponseEntity<CategoriaDTO> buscarPorNome(
            @Parameter(description = "Nome da categoria") @PathVariable String nome) {
        CategoriaDTO categoria = categoriaService.buscarPorNome(nome);
        return ResponseEntity.ok(categoria);
    }

    @Operation(summary = "Buscar categorias por texto", description = "Busca categorias que contenham o texto no nome ou descrição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/buscar")
    public ResponseEntity<Page<CategoriaDTO>> buscarPorTexto(
            @Parameter(description = "Termo de busca") @RequestParam String termo,
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        Page<CategoriaDTO> categorias = categoriaService.buscarPorTexto(termo, pageable);
        return ResponseEntity.ok(categorias);
    }

    @Operation(summary = "Listar categorias ordenadas", description = "Retorna todas as categorias ordenadas por nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    })
    @GetMapping("/ordenadas")
    public ResponseEntity<List<CategoriaDTO>> listarOrdenadas() {
        List<CategoriaDTO> categorias = categoriaService.buscarTodasOrdenadas();
        return ResponseEntity.ok(categorias);
    }

    @Operation(summary = "Criar nova categoria", description = "Cria uma nova categoria de bens patrimoniais")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou categoria já existe")
    })
    @PostMapping
    public ResponseEntity<CategoriaDTO> criar(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaCriada = categoriaService.criar(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCriada);
    }

    @Operation(summary = "Atualizar categoria", description = "Atualiza uma categoria existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizar(
            @Parameter(description = "ID da categoria") @PathVariable Long id,
            @Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaAtualizada = categoriaService.atualizar(id, categoriaDTO);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @Operation(summary = "Excluir categoria", description = "Exclui uma categoria (apenas se não houver bens associados)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
        @ApiResponse(responseCode = "400", description = "Categoria possui bens associados")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @Parameter(description = "ID da categoria") @PathVariable Long id) {
        categoriaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Contar bens por categoria", description = "Retorna a quantidade de bens por categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contagem realizada com sucesso")
    })
    @GetMapping("/estatisticas/bens")
    public ResponseEntity<List<Object[]>> contarBensPorCategoria() {
        List<Object[]> estatisticas = categoriaService.contarBensPorCategoria();
        return ResponseEntity.ok(estatisticas);
    }

    @Operation(summary = "Listar categorias com bens", description = "Retorna categorias que possuem bens associados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/com-bens")
    public ResponseEntity<List<CategoriaDTO>> listarCategoriasComBens() {
        List<CategoriaDTO> categorias = categoriaService.buscarCategoriasComBens();
        return ResponseEntity.ok(categorias);
    }

    @Operation(summary = "Listar categorias sem bens", description = "Retorna categorias que não possuem bens associados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/sem-bens")
    public ResponseEntity<List<CategoriaDTO>> listarCategoriasSemBens() {
        List<CategoriaDTO> categorias = categoriaService.buscarCategoriasSemBens();
        return ResponseEntity.ok(categorias);
    }
}

