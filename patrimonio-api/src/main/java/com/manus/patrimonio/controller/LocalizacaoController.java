package com.manus.patrimonio.controller;

import com.manus.patrimonio.dto.LocalizacaoDTO;
import com.manus.patrimonio.service.LocalizacaoService;
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
@RequestMapping("/api/localizacoes")
@Tag(name = "Localizações", description = "Operações relacionadas às localizações dos bens patrimoniais")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @Operation(summary = "Listar todas as localizações", description = "Retorna uma lista paginada de todas as localizações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de localizações retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<Page<LocalizacaoDTO>> listarTodas(
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        Page<LocalizacaoDTO> localizacoes = localizacaoService.buscarTodas(pageable);
        return ResponseEntity.ok(localizacoes);
    }

    @Operation(summary = "Buscar localização por ID", description = "Retorna uma localização específica pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Localização encontrada"),
        @ApiResponse(responseCode = "404", description = "Localização não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LocalizacaoDTO> buscarPorId(
            @Parameter(description = "ID da localização") @PathVariable Long id) {
        LocalizacaoDTO localizacao = localizacaoService.buscarPorId(id);
        return ResponseEntity.ok(localizacao);
    }

    @Operation(summary = "Buscar localização por nome", description = "Retorna uma localização específica pelo seu nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Localização encontrada"),
        @ApiResponse(responseCode = "404", description = "Localização não encontrada")
    })
    @GetMapping("/nome/{nome}")
    public ResponseEntity<LocalizacaoDTO> buscarPorNome(
            @Parameter(description = "Nome da localização") @PathVariable String nome) {
        LocalizacaoDTO localizacao = localizacaoService.buscarPorNome(nome);
        return ResponseEntity.ok(localizacao);
    }

    @Operation(summary = "Buscar localizações por texto", description = "Busca localizações que contenham o texto no nome, endereço, responsável ou descrição")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/buscar")
    public ResponseEntity<Page<LocalizacaoDTO>> buscarPorTexto(
            @Parameter(description = "Termo de busca") @RequestParam String termo,
            @PageableDefault(size = 20, sort = "nome") Pageable pageable) {
        Page<LocalizacaoDTO> localizacoes = localizacaoService.buscarPorTexto(termo, pageable);
        return ResponseEntity.ok(localizacoes);
    }

    @Operation(summary = "Listar localizações ordenadas", description = "Retorna todas as localizações ordenadas por nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de localizações retornada com sucesso")
    })
    @GetMapping("/ordenadas")
    public ResponseEntity<List<LocalizacaoDTO>> listarOrdenadas() {
        List<LocalizacaoDTO> localizacoes = localizacaoService.buscarTodasOrdenadas();
        return ResponseEntity.ok(localizacoes);
    }

    @Operation(summary = "Buscar localizações por responsável", description = "Busca localizações pelo nome do responsável")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/responsavel/{responsavel}")
    public ResponseEntity<List<LocalizacaoDTO>> buscarPorResponsavel(
            @Parameter(description = "Nome do responsável") @PathVariable String responsavel) {
        List<LocalizacaoDTO> localizacoes = localizacaoService.buscarPorResponsavel(responsavel);
        return ResponseEntity.ok(localizacoes);
    }

    @Operation(summary = "Buscar localizações por endereço", description = "Busca localizações pelo endereço")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    })
    @GetMapping("/endereco")
    public ResponseEntity<List<LocalizacaoDTO>> buscarPorEndereco(
            @Parameter(description = "Endereço") @RequestParam String endereco) {
        List<LocalizacaoDTO> localizacoes = localizacaoService.buscarPorEndereco(endereco);
        return ResponseEntity.ok(localizacoes);
    }

    @Operation(summary = "Criar nova localização", description = "Cria uma nova localização para bens patrimoniais")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Localização criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou localização já existe")
    })
    @PostMapping
    public ResponseEntity<LocalizacaoDTO> criar(@Valid @RequestBody LocalizacaoDTO localizacaoDTO) {
        LocalizacaoDTO localizacaoCriada = localizacaoService.criar(localizacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(localizacaoCriada);
    }

    @Operation(summary = "Atualizar localização", description = "Atualiza uma localização existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Localização atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Localização não encontrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LocalizacaoDTO> atualizar(
            @Parameter(description = "ID da localização") @PathVariable Long id,
            @Valid @RequestBody LocalizacaoDTO localizacaoDTO) {
        LocalizacaoDTO localizacaoAtualizada = localizacaoService.atualizar(id, localizacaoDTO);
        return ResponseEntity.ok(localizacaoAtualizada);
    }

    @Operation(summary = "Excluir localização", description = "Exclui uma localização (apenas se não houver bens atualmente nela)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Localização excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Localização não encontrada"),
        @ApiResponse(responseCode = "400", description = "Localização possui bens atualmente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @Parameter(description = "ID da localização") @PathVariable Long id) {
        localizacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Contar bens por localização", description = "Retorna a quantidade de bens por localização")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contagem realizada com sucesso")
    })
    @GetMapping("/estatisticas/bens")
    public ResponseEntity<List<Object[]>> contarBensPorLocalizacao() {
        List<Object[]> estatisticas = localizacaoService.contarBensPorLocalizacao();
        return ResponseEntity.ok(estatisticas);
    }

    @Operation(summary = "Listar localizações com bens", description = "Retorna localizações que possuem bens atualmente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/com-bens")
    public ResponseEntity<List<LocalizacaoDTO>> listarLocalizacoesComBens() {
        List<LocalizacaoDTO> localizacoes = localizacaoService.buscarLocalizacoesComBens();
        return ResponseEntity.ok(localizacoes);
    }

    @Operation(summary = "Listar localizações sem bens", description = "Retorna localizações que não possuem bens atualmente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping("/sem-bens")
    public ResponseEntity<List<LocalizacaoDTO>> listarLocalizacoesSemBens() {
        List<LocalizacaoDTO> localizacoes = localizacaoService.buscarLocalizacoesSemBens();
        return ResponseEntity.ok(localizacoes);
    }
}

