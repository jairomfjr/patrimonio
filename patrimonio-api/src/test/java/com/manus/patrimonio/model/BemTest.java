package com.manus.patrimonio.model;

import com.manus.patrimonio.enums.CondicaoBem;
import com.manus.patrimonio.enums.StatusBem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Entidade Bem")
class BemTest {

    private Bem bem;
    private Categoria categoria;
    private Localizacao localizacao;

    @BeforeEach
    void setUp() {
        categoria = new Categoria("Equipamentos", "Equipamentos de informática");
        localizacao = new Localizacao("Sala 101", "Rua A, 123", "João Silva", "joao@empresa.com", "Sala de TI");
        
        bem = new Bem("Computador Dell", "BEM001", "Computador Dell para desenvolvimento", categoria, localizacao, 
                     LocalDate.now(), new BigDecimal("5000.00"));
    }

    @Test
    @DisplayName("Deve criar bem com valores padrão")
    void deveCriarBemComValoresPadrao() {
        assertNotNull(bem);
        assertEquals("Computador Dell", bem.getNome());
        assertEquals("BEM001", bem.getNumeroTombamento());
        assertEquals("Computador Dell para desenvolvimento", bem.getDescricao());
        assertEquals(categoria, bem.getCategoria());
        assertEquals(localizacao, bem.getLocalizacaoAtual());
        assertEquals(StatusBem.ATIVO, bem.getStatus());
        assertEquals(CondicaoBem.BOM, bem.getCondicao());
        assertTrue(bem.getAtivo());
    }

    @Test
    @DisplayName("Deve definir e retornar número de tombamento corretamente")
    void deveDefinirERetornarNumeroTombamentoCorretamente() {
        String numeroTombamento = "BEM002";
        bem.setNumeroTombamento(numeroTombamento);
        assertEquals(numeroTombamento, bem.getNumeroTombamento());
    }

    @Test
    @DisplayName("Deve definir e retornar descrição corretamente")
    void deveDefinirERetornarDescricaoCorretamente() {
        String descricao = "Notebook Lenovo";
        bem.setDescricao(descricao);
        assertEquals(descricao, bem.getDescricao());
    }

    @Test
    @DisplayName("Deve definir e retornar categoria corretamente")
    void deveDefinirERetornarCategoriaCorretamente() {
        Categoria novaCategoria = new Categoria("Móveis", "Móveis de escritório");
        bem.setCategoria(novaCategoria);
        assertEquals(novaCategoria, bem.getCategoria());
    }

    @Test
    @DisplayName("Deve definir e retornar localização atual corretamente")
    void deveDefinirERetornarLocalizacaoAtualCorretamente() {
        Localizacao novaLocalizacao = new Localizacao("Sala 202", "Rua B, 456", "Maria Santos", "maria@empresa.com", "Sala de RH");
        bem.setLocalizacaoAtual(novaLocalizacao);
        assertEquals(novaLocalizacao, bem.getLocalizacaoAtual());
    }

    @Test
    @DisplayName("Deve definir e retornar status corretamente")
    void deveDefinirERetornarStatusCorretamente() {
        StatusBem novoStatus = StatusBem.EM_MANUTENCAO;
        bem.setStatus(novoStatus);
        assertEquals(novoStatus, bem.getStatus());
    }

    @Test
    @DisplayName("Deve definir e retornar condição corretamente")
    void deveDefinirERetornarCondicaoCorretamente() {
        CondicaoBem novaCondicao = CondicaoBem.RUIM;
        bem.setCondicao(novaCondicao);
        assertEquals(novaCondicao, bem.getCondicao());
    }

    @Test
    @DisplayName("Deve definir e retornar data de aquisição corretamente")
    void deveDefinirERetornarDataAquisicaoCorretamente() {
        LocalDate dataAquisicao = LocalDate.of(2023, 1, 15);
        bem.setDataAquisicao(dataAquisicao);
        assertEquals(dataAquisicao, bem.getDataAquisicao());
    }

    @Test
    @DisplayName("Deve definir e retornar valor de aquisição corretamente")
    void deveDefinirERetornarValorAquisicaoCorretamente() {
        BigDecimal valorAquisicao = new BigDecimal("7500.00");
        bem.setValorAquisicao(valorAquisicao);
        assertEquals(valorAquisicao, bem.getValorAquisicao());
    }

    @Test
    @DisplayName("Deve definir e retornar valor atual corretamente")
    void deveDefinirERetornarValorAtualCorretamente() {
        BigDecimal valorAtual = new BigDecimal("6000.00");
        bem.setValorAtual(valorAtual);
        assertEquals(valorAtual, bem.getValorAtual());
    }

    @Test
    @DisplayName("Deve definir e retornar marca corretamente")
    void deveDefinirERetornarMarcaCorretamente() {
        String marca = "Dell";
        bem.setMarca(marca);
        assertEquals(marca, bem.getMarca());
    }

    @Test
    @DisplayName("Deve definir e retornar modelo corretamente")
    void deveDefinirERetornarModeloCorretamente() {
        String modelo = "Latitude 5520";
        bem.setModelo(modelo);
        assertEquals(modelo, bem.getModelo());
    }

    @Test
    @DisplayName("Deve definir e retornar número de série corretamente")
    void deveDefinirERetornarNumeroSerieCorretamente() {
        String numeroSerie = "SN123456789";
        bem.setNumeroSerie(numeroSerie);
        assertEquals(numeroSerie, bem.getNumeroSerie());
    }

    @Test
    @DisplayName("Deve definir e retornar fornecedor corretamente")
    void deveDefinirERetornarFornecedorCorretamente() {
        String fornecedor = "Dell Brasil";
        bem.setFornecedor(fornecedor);
        assertEquals(fornecedor, bem.getFornecedor());
    }

    @Test
    @DisplayName("Deve definir e retornar fabricante corretamente")
    void deveDefinirERetornarFabricanteCorretamente() {
        String fabricante = "Dell Inc.";
        bem.setFabricante(fabricante);
        assertEquals(fabricante, bem.getFabricante());
    }

    @Test
    @DisplayName("Deve definir e retornar ano de fabricação corretamente")
    void deveDefinirERetornarAnoFabricacaoCorretamente() {
        Integer anoFabricacao = 2022;
        bem.setAnoFabricacao(anoFabricacao);
        assertEquals(anoFabricacao, bem.getAnoFabricacao());
    }

    @Test
    @DisplayName("Deve definir e retornar garantia até corretamente")
    void deveDefinirERetornarGarantiaAteCorretamente() {
        LocalDate garantiaAte = LocalDate.of(2025, 1, 15);
        bem.setGarantiaAte(garantiaAte);
        assertEquals(garantiaAte, bem.getGarantiaAte());
    }

    @Test
    @DisplayName("Deve definir e retornar localização física corretamente")
    void deveDefinirERetornarLocalizacaoFisicaCorretamente() {
        String localizacaoFisica = "Prateleira A, Gaveta 3";
        bem.setLocalizacaoFisica(localizacaoFisica);
        assertEquals(localizacaoFisica, bem.getLocalizacaoFisica());
    }

    @Test
    @DisplayName("Deve definir e retornar responsável corretamente")
    void deveDefinirERetornarResponsavelCorretamente() {
        String responsavel = "Carlos Oliveira";
        bem.setResponsavel(responsavel);
        assertEquals(responsavel, bem.getResponsavel());
    }

    @Test
    @DisplayName("Deve definir e retornar departamento corretamente")
    void deveDefinirERetornarDepartamentoCorretamente() {
        String departamento = "TI";
        bem.setDepartamento(departamento);
        assertEquals(departamento, bem.getDepartamento());
    }

    @Test
    @DisplayName("Deve definir e retornar centro de custo corretamente")
    void deveDefinirERetornarCentroCustoCorretamente() {
        String centroCusto = "CC001";
        bem.setCentroCusto(centroCusto);
        assertEquals(centroCusto, bem.getCentroCusto());
    }

    @Test
    @DisplayName("Deve definir e retornar vida útil em anos corretamente")
    void deveDefinirERetornarVidaUtilAnosCorretamente() {
        Integer vidaUtilAnos = 5;
        bem.setVidaUtilAnos(vidaUtilAnos);
        assertEquals(vidaUtilAnos, bem.getVidaUtilAnos());
    }

    @Test
    @DisplayName("Deve definir e retornar taxa de depreciação corretamente")
    void deveDefinirERetornarTaxaDepreciacaoCorretamente() {
        BigDecimal taxaDepreciacao = new BigDecimal("20.00");
        bem.setTaxaDepreciacao(taxaDepreciacao);
        assertEquals(taxaDepreciacao, bem.getTaxaDepreciacao());
    }

    @Test
    @DisplayName("Deve definir e retornar data última manutenção corretamente")
    void deveDefinirERetornarDataUltimaManutencaoCorretamente() {
        LocalDate dataUltimaManutencao = LocalDate.of(2023, 6, 15);
        bem.setDataUltimaManutencao(dataUltimaManutencao);
        assertEquals(dataUltimaManutencao, bem.getDataUltimaManutencao());
    }

    @Test
    @DisplayName("Deve definir e retornar data próxima manutenção corretamente")
    void deveDefinirERetornarDataProximaManutencaoCorretamente() {
        LocalDate dataProximaManutencao = LocalDate.of(2023, 12, 15);
        bem.setDataProximaManutencao(dataProximaManutencao);
        assertEquals(dataProximaManutencao, bem.getDataProximaManutencao());
    }

    @Test
    @DisplayName("Deve definir e retornar observações de manutenção corretamente")
    void deveDefinirERetornarObservacoesManutencaoCorretamente() {
        String observacoesManutencao = "Trocar pasta térmica e limpar ventilador";
        bem.setObservacoesManutencao(observacoesManutencao);
        assertEquals(observacoesManutencao, bem.getObservacoesManutencao());
    }

    @Test
    @DisplayName("Deve definir e retornar ativo corretamente")
    void deveDefinirERetornarAtivoCorretamente() {
        Boolean ativo = false;
        bem.setAtivo(ativo);
        assertEquals(ativo, bem.getAtivo());
    }

    @Test
    @DisplayName("Deve verificar se bem está ativo")
    void deveVerificarSeBemEstaAtivo() {
        bem.setAtivo(true);
        assertTrue(bem.isAtivo());
        
        bem.setAtivo(false);
        assertFalse(bem.isAtivo());
    }

    @Test
    @DisplayName("Deve verificar se bem está inativo")
    void deveVerificarSeBemEstaInativo() {
        bem.setAtivo(true);
        assertFalse(bem.isInativo());
        
        bem.setAtivo(false);
        assertTrue(bem.isInativo());
    }

    @Test
    @DisplayName("Deve verificar se bem está disponível")
    void deveVerificarSeBemEstaDisponivel() {
        bem.setStatus(StatusBem.ATIVO);
        bem.setAtivo(true);
        assertTrue(bem.isDisponivel());
        
        bem.setStatus(StatusBem.EM_MANUTENCAO);
        assertFalse(bem.isDisponivel());
        
        bem.setAtivo(false);
        assertFalse(bem.isDisponivel());
    }

    @Test
    @DisplayName("Deve verificar se bem está em manutenção")
    void deveVerificarSeBemEstaEmManutencao() {
        bem.setStatus(StatusBem.EM_MANUTENCAO);
        assertTrue(bem.isEmManutencao());
        
        bem.setStatus(StatusBem.ATIVO);
        assertFalse(bem.isEmManutencao());
    }

    @Test
    @DisplayName("Deve verificar se bem está baixado")
    void deveVerificarSeBemEstaBaixado() {
        bem.setStatus(StatusBem.BAIXADO);
        assertTrue(bem.isBaixado());
        
        bem.setStatus(StatusBem.ATIVO);
        assertFalse(bem.isBaixado());
    }

    @Test
    @DisplayName("Deve verificar se bem está em inventário")
    void deveVerificarSeBemEstaEmInventario() {
        bem.setStatus(StatusBem.INATIVO);
        assertTrue(bem.isEmInventario());
        
        bem.setStatus(StatusBem.ATIVO);
        assertFalse(bem.isEmInventario());
    }

    @Test
    @DisplayName("Deve verificar se bem está em movimentação")
    void deveVerificarSeBemEstaEmMovimentacao() {
        bem.setStatus(StatusBem.EM_TRANSITO);
        assertTrue(bem.isEmMovimentacao());
        
        bem.setStatus(StatusBem.ATIVO);
        assertFalse(bem.isEmMovimentacao());
    }

    @Test
    @DisplayName("Deve verificar se bem está em reserva")
    void deveVerificarSeBemEstaEmReserva() {
        bem.setStatus(StatusBem.RESERVA);
        assertTrue(bem.isEmReserva());
        
        bem.setStatus(StatusBem.ATIVO);
        assertFalse(bem.isEmReserva());
    }

    @Test
    @DisplayName("Deve verificar se bem está emprestado")
    void deveVerificarSeBemEstaEmprestado() {
        bem.setStatus(StatusBem.ATIVO);
        assertTrue(bem.isEmprestado());
        
        bem.setStatus(StatusBem.EM_MANUTENCAO);
        assertFalse(bem.isEmprestado());
    }

    @Test
    @DisplayName("Deve verificar se bem está roubado")
    void deveVerificarSeBemEstaRoubado() {
        bem.setStatus(StatusBem.ROUBADO);
        assertTrue(bem.isRoubado());
        
        bem.setStatus(StatusBem.ATIVO);
        assertFalse(bem.isRoubado());
    }

    @Test
    @DisplayName("Deve verificar se bem está perdido")
    void deveVerificarSeBemEstaPerdido() {
        bem.setStatus(StatusBem.EXTRAVIADO);
        assertTrue(bem.isPerdido());
        
        bem.setStatus(StatusBem.ATIVO);
        assertFalse(bem.isPerdido());
    }

    @Test
    @DisplayName("Deve verificar se bem está danificado")
    void deveVerificarSeBemEstaDanificado() {
        bem.setStatus(StatusBem.DANIFICADO);
        assertTrue(bem.isDanificado());
        
        bem.setStatus(StatusBem.ATIVO);
        assertFalse(bem.isDanificado());
    }

    @Test
    @DisplayName("Deve verificar se bem está obsoleto")
    void deveVerificarSeBemEstaObsoleto() {
        bem.setStatus(StatusBem.OBSOLETO);
        assertTrue(bem.isObsoleto());
        
        bem.setStatus(StatusBem.ATIVO);
        assertFalse(bem.isObsoleto());
    }

    @Test
    @DisplayName("Deve verificar se bem está em garantia")
    void deveVerificarSeBemEstaEmGarantia() {
        LocalDate garantiaAte = LocalDate.now().plusDays(30);
        bem.setGarantiaAte(garantiaAte);
        assertTrue(bem.isEmGarantia());
        
        LocalDate garantiaExpirada = LocalDate.now().minusDays(30);
        bem.setGarantiaAte(garantiaExpirada);
        assertFalse(bem.isEmGarantia());
    }

    @Test
    @DisplayName("Deve verificar se bem precisa de manutenção")
    void deveVerificarSeBemPrecisaDeManutencao() {
        LocalDate dataProximaManutencao = LocalDate.now().minusDays(5);
        bem.setDataProximaManutencao(dataProximaManutencao);
        assertTrue(bem.precisaManutencao());
        
        LocalDate dataProximaManutencaoFutura = LocalDate.now().plusDays(5);
        bem.setDataProximaManutencao(dataProximaManutencaoFutura);
        assertFalse(bem.precisaManutencao());
    }

    @Test
    @DisplayName("Deve verificar se bem pode ser movimentado")
    void deveVerificarSeBemPodeSerMovimentado() {
        bem.setStatus(StatusBem.ATIVO);
        bem.setAtivo(true);
        assertTrue(bem.podeSerMovimentado());
        
        bem.setStatus(StatusBem.EM_MANUTENCAO);
        assertFalse(bem.podeSerMovimentado());
        
        bem.setStatus(StatusBem.INATIVO);
        assertFalse(bem.podeSerMovimentado());
    }

    @Test
    @DisplayName("Deve verificar se bem pode ser baixado")
    void deveVerificarSeBemPodeSerBaixado() {
        bem.setStatus(StatusBem.ATIVO);
        assertTrue(bem.podeSerBaixado());
        
        bem.setStatus(StatusBem.EM_MANUTENCAO);
        assertTrue(bem.podeSerBaixado());
        
        bem.setStatus(StatusBem.BAIXADO);
        assertFalse(bem.podeSerBaixado());
    }

    @Test
    @DisplayName("Deve implementar equals baseado no número de tombamento")
    void deveImplementarEqualsBaseadoNoNumeroTombamento() {
        Bem bem1 = new Bem("Bem 1", "BEM001", "Descrição 1", categoria, localizacao, LocalDate.now(), BigDecimal.ONE);
        Bem bem2 = new Bem("Bem 2", "BEM002", "Descrição 2", categoria, localizacao, LocalDate.now(), BigDecimal.ONE);
        
        assertNotEquals(bem1, bem2);
        
        bem2.setNumeroTombamento("BEM001");
        assertEquals(bem1, bem2);
    }

    @Test
    @DisplayName("Deve implementar hashCode baseado no número de tombamento")
    void deveImplementarHashCodeBaseadoNoNumeroTombamento() {
        Bem bem1 = new Bem("Bem 1", "BEM001", "Descrição 1", categoria, localizacao, LocalDate.now(), BigDecimal.ONE);
        Bem bem2 = new Bem("Bem 2", "BEM002", "Descrição 2", categoria, localizacao, LocalDate.now(), BigDecimal.ONE);
        
        assertNotEquals(bem1.hashCode(), bem2.hashCode());
        
        bem2.setNumeroTombamento("BEM001");
        assertEquals(bem1.hashCode(), bem2.hashCode());
    }

    @Test
    @DisplayName("Deve implementar toString corretamente")
    void deveImplementarToStringCorretamente() {
        String toString = bem.toString();
        assertTrue(toString.contains("Bem"));
        assertTrue(toString.contains("nome="));
        assertTrue(toString.contains("numeroTombamento="));
        assertTrue(toString.contains("descricao="));
        assertTrue(toString.contains("status="));
        assertTrue(toString.contains("condicao="));
        assertTrue(toString.contains("ativo="));
    }
}
