package com.manus.patrimonio.controller;

import com.manus.patrimonio.dto.ConfiguracaoDTO;
import com.manus.patrimonio.service.ConfiguracaoService;
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
@RequestMapping("/configuracoes")
@Tag(name = "Configurações", description = "API para gerenciamento de configurações do sistema")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoService configuracaoService;

    @PostMapping
    @Operation(summary = "Criar configuração", description = "Cria uma nova configuração")
    public ResponseEntity<ConfiguracaoDTO> criar(@Valid @RequestBody ConfiguracaoDTO configuracaoDTO) {
        ConfiguracaoDTO configuracaoCriada = configuracaoService.criar(configuracaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(configuracaoCriada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar configuração por ID", description = "Retorna uma configuração específica")
    public ResponseEntity<ConfiguracaoDTO> buscarPorId(@PathVariable Long id) {
        ConfiguracaoDTO configuracao = configuracaoService.buscarPorId(id);
        return ResponseEntity.ok(configuracao);
    }

    @GetMapping("/chave/{chave}")
    @Operation(summary = "Buscar configuração por chave", description = "Retorna uma configuração por chave")
    public ResponseEntity<ConfiguracaoDTO> buscarPorChave(@PathVariable String chave) {
        ConfiguracaoDTO configuracao = configuracaoService.buscarPorChave(chave);
        return ResponseEntity.ok(configuracao);
    }

    @GetMapping
    @Operation(summary = "Listar configurações", description = "Retorna lista paginada de configurações")
    public ResponseEntity<Page<ConfiguracaoDTO>> listar(Pageable pageable) {
        Page<ConfiguracaoDTO> configuracoes = configuracaoService.buscarTodas(pageable);
        return ResponseEntity.ok(configuracoes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar configuração", description = "Atualiza uma configuração existente")
    public ResponseEntity<ConfiguracaoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ConfiguracaoDTO configuracaoDTO) {
        ConfiguracaoDTO configuracaoAtualizada = configuracaoService.atualizar(id, configuracaoDTO);
        return ResponseEntity.ok(configuracaoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir configuração", description = "Exclui uma configuração")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        configuracaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/valor")
    @Operation(summary = "Atualizar valor", description = "Atualiza apenas o valor de uma configuração")
    public ResponseEntity<ConfiguracaoDTO> atualizarValor(@PathVariable Long id, @RequestParam String novoValor) {
        ConfiguracaoDTO configuracaoAtualizada = configuracaoService.atualizarValorPorId(id, novoValor);
        return ResponseEntity.ok(configuracaoAtualizada);
    }

    @PatchMapping("/chave/{chave}/valor")
    @Operation(summary = "Atualizar valor por chave", description = "Atualiza o valor de uma configuração por chave")
    public ResponseEntity<ConfiguracaoDTO> atualizarValorPorChave(@PathVariable String chave, @RequestParam String novoValor) {
        ConfiguracaoDTO configuracaoAtualizada = configuracaoService.atualizarValor(chave, novoValor);
        return ResponseEntity.ok(configuracaoAtualizada);
    }

    @GetMapping("/buscar/chave")
    @Operation(summary = "Buscar por chave", description = "Busca configurações por chave")
    public ResponseEntity<Page<ConfiguracaoDTO>> buscarPorChave(@RequestParam String chave, Pageable pageable) {
        Page<ConfiguracaoDTO> configuracoes = configuracaoService.buscarPorChave(chave, pageable);
        return ResponseEntity.ok(configuracoes);
    }

    @GetMapping("/buscar/tipo")
    @Operation(summary = "Buscar por tipo", description = "Busca configurações por tipo")
    public ResponseEntity<Page<ConfiguracaoDTO>> buscarPorTipo(@RequestParam String tipo, Pageable pageable) {
        Page<ConfiguracaoDTO> configuracoes = configuracaoService.buscarPorTipo(tipo, pageable);
        return ResponseEntity.ok(configuracoes);
    }

    @GetMapping("/buscar/editavel")
    @Operation(summary = "Buscar por editável", description = "Busca configurações por editável")
    public ResponseEntity<Page<ConfiguracaoDTO>> buscarPorEditavel(@RequestParam Boolean editavel, Pageable pageable) {
        Page<ConfiguracaoDTO> configuracoes = configuracaoService.buscarPorEditavel(editavel, pageable);
        return ResponseEntity.ok(configuracoes);
    }

    @GetMapping("/buscar/descricao")
    @Operation(summary = "Buscar por descrição", description = "Busca configurações por descrição")
    public ResponseEntity<Page<ConfiguracaoDTO>> buscarPorDescricao(@RequestParam String descricao, Pageable pageable) {
        Page<ConfiguracaoDTO> configuracoes = configuracaoService.buscarPorDescricao(descricao, pageable);
        return ResponseEntity.ok(configuracoes);
    }

    @GetMapping("/estatisticas/tipo/{tipo}")
    @Operation(summary = "Contar por tipo", description = "Conta configurações por tipo")
    public ResponseEntity<Long> contarPorTipo(@PathVariable String tipo) {
        Long quantidade = configuracaoService.contarConfiguracoesPorTipo(tipo);
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/editaveis")
    @Operation(summary = "Contar editáveis", description = "Conta configurações editáveis")
    public ResponseEntity<Long> contarEditaveis() {
        Long quantidade = configuracaoService.contarConfiguracoesEditaveis();
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/estatisticas/nao-editaveis")
    @Operation(summary = "Contar não editáveis", description = "Conta configurações não editáveis")
    public ResponseEntity<Long> contarNaoEditaveis() {
        Long quantidade = configuracaoService.contarConfiguracoesNaoEditaveis();
        return ResponseEntity.ok(quantidade);
    }

    @GetMapping("/sistema")
    @Operation(summary = "Buscar configurações do sistema", description = "Busca configurações do sistema")
    public ResponseEntity<List<ConfiguracaoDTO>> buscarConfiguracoesDoSistema() {
        List<ConfiguracaoDTO> configuracoes = configuracaoService.buscarConfiguracoesDoSistema();
        return ResponseEntity.ok(configuracoes);
    }

    @GetMapping("/editaveis")
    @Operation(summary = "Buscar configurações editáveis", description = "Busca configurações editáveis")
    public ResponseEntity<List<ConfiguracaoDTO>> buscarConfiguracoesEditaveis() {
        List<ConfiguracaoDTO> configuracoes = configuracaoService.buscarConfiguracoesEditaveis();
        return ResponseEntity.ok(configuracoes);
    }

    @GetMapping("/valor/{chave}")
    @Operation(summary = "Obter valor", description = "Obtém o valor de uma configuração por chave")
    public ResponseEntity<String> obterValor(@PathVariable String chave) {
        String valor = configuracaoService.getValor(chave);
        return ResponseEntity.ok(valor);
    }

    @GetMapping("/valor/{chave}/padrao/{valorPadrao}")
    @Operation(summary = "Obter valor com padrão", description = "Obtém o valor de uma configuração com valor padrão")
    public ResponseEntity<String> obterValorComPadrao(@PathVariable String chave, @PathVariable String valorPadrao) {
        String valor = configuracaoService.getValor(chave, valorPadrao);
        return ResponseEntity.ok(valor);
    }

    @GetMapping("/valor/{chave}/integer")
    @Operation(summary = "Obter valor como inteiro", description = "Obtém o valor de uma configuração como inteiro")
    public ResponseEntity<Integer> obterValorComoInteger(@PathVariable String chave) {
        Integer valor = configuracaoService.getValorAsInteger(chave);
        return ResponseEntity.ok(valor);
    }

    @GetMapping("/valor/{chave}/boolean")
    @Operation(summary = "Obter valor como booleano", description = "Obtém o valor de uma configuração como booleano")
    public ResponseEntity<Boolean> obterValorComoBoolean(@PathVariable String chave) {
        Boolean valor = configuracaoService.getValorAsBoolean(chave);
        return ResponseEntity.ok(valor);
    }

    @GetMapping("/valor/{chave}/double")
    @Operation(summary = "Obter valor como decimal", description = "Obtém o valor de uma configuração como decimal")
    public ResponseEntity<Double> obterValorComoDouble(@PathVariable String chave) {
        Double valor = configuracaoService.getValorAsDouble(chave);
        return ResponseEntity.ok(valor);
    }
}
