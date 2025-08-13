package com.manus.patrimonio.config;

import com.manus.patrimonio.enums.CondicaoBem;
import com.manus.patrimonio.enums.StatusBem;
import com.manus.patrimonio.enums.StatusInventario;
import com.manus.patrimonio.enums.TipoMovimentacao;
import com.manus.patrimonio.model.*;
import com.manus.patrimonio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DadosIniciais implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private BemRepository bemRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoriaRepository.count() == 0) {
            criarDadosIniciais();
        }
    }

    private void criarDadosIniciais() {
        // Criar Categorias
        Categoria informatica = new Categoria("Informática", "Equipamentos de informática e tecnologia");
        Categoria mobiliario = new Categoria("Mobiliário", "Móveis e equipamentos de escritório");
        Categoria veiculos = new Categoria("Veículos", "Veículos da empresa");
        Categoria equipamentos = new Categoria("Equipamentos", "Equipamentos diversos");

        categoriaRepository.save(informatica);
        categoriaRepository.save(mobiliario);
        categoriaRepository.save(veiculos);
        categoriaRepository.save(equipamentos);

        // Criar Localizações
        Localizacao matriz = new Localizacao("Matriz", "Rua Principal, 123 - Centro", 
                                            "João Silva", "(11) 99999-9999", "Sede principal da empresa");
        Localizacao filialNorte = new Localizacao("Filial Norte", "Av. Norte, 456 - Zona Norte", 
                                                 "Maria Santos", "(11) 88888-8888", "Filial da zona norte");
        Localizacao almoxarifado = new Localizacao("Almoxarifado", "Rua Industrial, 789 - Distrito Industrial", 
                                                  "Pedro Costa", "(11) 77777-7777", "Depósito central");

        localizacaoRepository.save(matriz);
        localizacaoRepository.save(filialNorte);
        localizacaoRepository.save(almoxarifado);

        // Criar Bens
        Bem notebook1 = new Bem("Notebook Dell Inspiron 15", "NB-001", "Notebook para uso administrativo", 
                               informatica, matriz, LocalDate.of(2024, 1, 15), new BigDecimal("2500.00"));
        notebook1.setStatus(StatusBem.ATIVO);
        notebook1.setCondicao(CondicaoBem.BOM);

        Bem mesa1 = new Bem("Mesa de Escritório", "MS-001", "Mesa executiva em madeira", 
                           mobiliario, matriz, LocalDate.of(2023, 12, 10), new BigDecimal("800.00"));
        mesa1.setStatus(StatusBem.ATIVO);
        mesa1.setCondicao(CondicaoBem.BOM);

        Bem impressora1 = new Bem("Impressora HP LaserJet", "IMP-001", "Impressora multifuncional", 
                                 informatica, almoxarifado, LocalDate.of(2024, 2, 20), new BigDecimal("1200.00"));
        impressora1.setStatus(StatusBem.EM_MANUTENCAO);
        impressora1.setCondicao(CondicaoBem.REGULAR);

        Bem cadeira1 = new Bem("Cadeira Ergonômica", "CD-001", "Cadeira de escritório com apoio lombar", 
                              mobiliario, filialNorte, LocalDate.of(2023, 11, 5), new BigDecimal("450.00"));
        cadeira1.setStatus(StatusBem.ATIVO);
        cadeira1.setCondicao(CondicaoBem.BOM);

        Bem monitor1 = new Bem("Monitor LG 24\"", "MON-001", "Monitor LED Full HD", 
                              informatica, matriz, LocalDate.of(2024, 3, 1), new BigDecimal("600.00"));
        monitor1.setStatus(StatusBem.ATIVO);
        monitor1.setCondicao(CondicaoBem.EXCELENTE);

        bemRepository.save(notebook1);
        bemRepository.save(mesa1);
        bemRepository.save(impressora1);
        bemRepository.save(cadeira1);
        bemRepository.save(monitor1);

        // Criar Movimentações
        Movimentacao mov1 = new Movimentacao(notebook1, LocalDateTime.of(2024, 1, 15, 10, 0), 
                                            TipoMovimentacao.ENTRADA, null, matriz, "João Silva");
        mov1.setObservacoes("Entrada inicial do bem");

        Movimentacao mov2 = new Movimentacao(impressora1, LocalDateTime.of(2024, 3, 10, 14, 30), 
                                            TipoMovimentacao.MANUTENCAO, matriz, almoxarifado, "Pedro Costa");
        mov2.setObservacoes("Enviado para manutenção preventiva");

        Movimentacao mov3 = new Movimentacao(cadeira1, LocalDateTime.of(2024, 2, 1, 9, 15), 
                                            TipoMovimentacao.TRANSFERENCIA, matriz, filialNorte, "Maria Santos");
        mov3.setObservacoes("Transferência para nova filial");

        movimentacaoRepository.save(mov1);
        movimentacaoRepository.save(mov2);
        movimentacaoRepository.save(mov3);

        // Criar Inventário
        Inventario inventario2024 = new Inventario("Inventário Anual 2024", LocalDate.of(2024, 1, 1), 
                                                  "João Silva", StatusInventario.EM_ANDAMENTO);
        inventario2024.setObservacoes("Inventário anual de todos os bens da empresa");
        inventario2024.adicionarBem(notebook1);
        inventario2024.adicionarBem(mesa1);
        inventario2024.adicionarBem(impressora1);
        inventario2024.adicionarBem(cadeira1);
        inventario2024.adicionarBem(monitor1);

        inventarioRepository.save(inventario2024);

        System.out.println("=== Dados iniciais criados com sucesso! ===");
        System.out.println("Categorias: " + categoriaRepository.count());
        System.out.println("Localizações: " + localizacaoRepository.count());
        System.out.println("Bens: " + bemRepository.count());
        System.out.println("Movimentações: " + movimentacaoRepository.count());
        System.out.println("Inventários: " + inventarioRepository.count());
        System.out.println("==========================================");
    }
}

