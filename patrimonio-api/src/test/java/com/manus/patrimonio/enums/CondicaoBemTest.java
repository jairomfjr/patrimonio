package com.manus.patrimonio.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do Enum CondicaoBem")
class CondicaoBemTest {

    @Test
    @DisplayName("Deve ter todos os valores esperados")
    void deveTerTodosOsValoresEsperados() {
        CondicaoBem[] valores = CondicaoBem.values();
        
        assertEquals(6, valores.length);
        
        assertTrue(contemValor(valores, "EXCELENTE"));
        assertTrue(contemValor(valores, "BOM"));
        assertTrue(contemValor(valores, "REGULAR"));
        assertTrue(contemValor(valores, "RUIM"));
        assertTrue(contemValor(valores, "CRITICO"));
        assertTrue(contemValor(valores, "INUTILIZAVEL"));
    }

    @Test
    @DisplayName("Deve retornar descrição correta para cada valor")
    void deveRetornarDescricaoCorretaParaCadaValor() {
        assertEquals("Excelente", CondicaoBem.EXCELENTE.getDescricao());
        assertEquals("Bom", CondicaoBem.BOM.getDescricao());
        assertEquals("Regular", CondicaoBem.REGULAR.getDescricao());
        assertEquals("Ruim", CondicaoBem.RUIM.getDescricao());
        assertEquals("Crítico", CondicaoBem.CRITICO.getDescricao());
        assertEquals("Inutilizável", CondicaoBem.INUTILIZAVEL.getDescricao());
    }

    @Test
    @DisplayName("Deve retornar explicação correta para cada valor")
    void deveRetornarExplicacaoCorretaParaCadaValor() {
        assertEquals("Como novo, sem desgaste", CondicaoBem.EXCELENTE.getExplicacao());
        assertEquals("Pequenos sinais de uso, funcionando perfeitamente", CondicaoBem.BOM.getExplicacao());
        assertEquals("Sinais de uso visíveis, funcionando adequadamente", CondicaoBem.REGULAR.getExplicacao());
        assertEquals("Desgaste significativo, funcionamento limitado", CondicaoBem.RUIM.getExplicacao());
        assertEquals("Muito desgastado, funcionamento precário", CondicaoBem.CRITICO.getExplicacao());
        assertEquals("Não funciona, sem possibilidade de reparo", CondicaoBem.INUTILIZAVEL.getExplicacao());
    }

    @Test
    @DisplayName("Deve identificar condição operacional corretamente")
    void deveIdentificarCondicaoOperacionalCorretamente() {
        assertTrue(CondicaoBem.EXCELENTE.isOperacional());
        assertTrue(CondicaoBem.BOM.isOperacional());
        assertTrue(CondicaoBem.REGULAR.isOperacional());
        assertFalse(CondicaoBem.RUIM.isOperacional());
        assertFalse(CondicaoBem.CRITICO.isOperacional());
        assertFalse(CondicaoBem.INUTILIZAVEL.isOperacional());
    }

    @Test
    @DisplayName("Deve identificar condição que precisa de manutenção corretamente")
    void deveIdentificarCondicaoQuePrecisaDeManutencaoCorretamente() {
        assertFalse(CondicaoBem.EXCELENTE.isManutencaoNecessaria());
        assertFalse(CondicaoBem.BOM.isManutencaoNecessaria());
        assertFalse(CondicaoBem.REGULAR.isManutencaoNecessaria());
        assertTrue(CondicaoBem.RUIM.isManutencaoNecessaria());
        assertTrue(CondicaoBem.CRITICO.isManutencaoNecessaria());
        assertFalse(CondicaoBem.INUTILIZAVEL.isManutencaoNecessaria());
    }

    @Test
    @DisplayName("Deve identificar condição que recomenda baixa corretamente")
    void deveIdentificarCondicaoQueRecomendaBaixaCorretamente() {
        assertFalse(CondicaoBem.EXCELENTE.isBaixaRecomendada());
        assertFalse(CondicaoBem.BOM.isBaixaRecomendada());
        assertFalse(CondicaoBem.REGULAR.isBaixaRecomendada());
        assertFalse(CondicaoBem.RUIM.isBaixaRecomendada());
        assertFalse(CondicaoBem.CRITICO.isBaixaRecomendada());
        assertTrue(CondicaoBem.INUTILIZAVEL.isBaixaRecomendada());
    }

    @Test
    @DisplayName("Deve converter string para enum corretamente")
    void deveConverterStringParaEnumCorretamente() {
        assertEquals(CondicaoBem.EXCELENTE, CondicaoBem.valueOf("EXCELENTE"));
        assertEquals(CondicaoBem.BOM, CondicaoBem.valueOf("BOM"));
        assertEquals(CondicaoBem.REGULAR, CondicaoBem.valueOf("REGULAR"));
        assertEquals(CondicaoBem.RUIM, CondicaoBem.valueOf("RUIM"));
        assertEquals(CondicaoBem.CRITICO, CondicaoBem.valueOf("CRITICO"));
        assertEquals(CondicaoBem.INUTILIZAVEL, CondicaoBem.valueOf("INUTILIZAVEL"));
    }

    @Test
    @DisplayName("Deve lançar exceção para valor inválido")
    void deveLancarExcecaoParaValorInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            CondicaoBem.valueOf("INVALIDO");
        });
    }

    @Test
    @DisplayName("Deve retornar nome correto para cada valor")
    void deveRetornarNomeCorretoParaCadaValor() {
        assertEquals("EXCELENTE", CondicaoBem.EXCELENTE.name());
        assertEquals("BOM", CondicaoBem.BOM.name());
        assertEquals("REGULAR", CondicaoBem.REGULAR.name());
        assertEquals("RUIM", CondicaoBem.RUIM.name());
        assertEquals("CRITICO", CondicaoBem.CRITICO.name());
        assertEquals("INUTILIZAVEL", CondicaoBem.INUTILIZAVEL.name());
    }

    @Test
    @DisplayName("Deve retornar ordinal correto para cada valor")
    void deveRetornarOrdinalCorretoParaCadaValor() {
        assertEquals(0, CondicaoBem.EXCELENTE.ordinal());
        assertEquals(1, CondicaoBem.BOM.ordinal());
        assertEquals(2, CondicaoBem.REGULAR.ordinal());
        assertEquals(3, CondicaoBem.RUIM.ordinal());
        assertEquals(4, CondicaoBem.CRITICO.ordinal());
        assertEquals(5, CondicaoBem.INUTILIZAVEL.ordinal());
    }

    @Test
    @DisplayName("Deve implementar toString corretamente")
    void deveImplementarToStringCorretamente() {
        assertEquals("EXCELENTE", CondicaoBem.EXCELENTE.toString());
        assertEquals("BOM", CondicaoBem.BOM.toString());
        assertEquals("REGULAR", CondicaoBem.REGULAR.toString());
        assertEquals("RUIM", CondicaoBem.RUIM.toString());
        assertEquals("CRITICO", CondicaoBem.CRITICO.toString());
        assertEquals("INUTILIZAVEL", CondicaoBem.INUTILIZAVEL.toString());
    }

    @Test
    @DisplayName("Deve comparar enums corretamente")
    void deveCompararEnumsCorretamente() {
        assertTrue(CondicaoBem.EXCELENTE.compareTo(CondicaoBem.BOM) < 0);
        assertTrue(CondicaoBem.BOM.compareTo(CondicaoBem.EXCELENTE) > 0);
        assertEquals(0, CondicaoBem.EXCELENTE.compareTo(CondicaoBem.EXCELENTE));
    }

    @Test
    @DisplayName("Deve ordenar enums por qualidade decrescente")
    void deveOrdenarEnumsPorQualidadeDecrescente() {
        CondicaoBem[] valoresOrdenados = {
            CondicaoBem.EXCELENTE,
            CondicaoBem.BOM,
            CondicaoBem.REGULAR,
            CondicaoBem.RUIM,
            CondicaoBem.CRITICO,
            CondicaoBem.INUTILIZAVEL
        };
        
        CondicaoBem[] valores = CondicaoBem.values();
        
        for (int i = 0; i < valoresOrdenados.length; i++) {
            assertEquals(valoresOrdenados[i], valores[i]);
        }
    }

    @Test
    @DisplayName("Deve identificar condições que permitem uso normal")
    void deveIdentificarCondicoesQuePermitemUsoNormal() {
        assertTrue(CondicaoBem.EXCELENTE.isOperacional());
        assertTrue(CondicaoBem.BOM.isOperacional());
        assertTrue(CondicaoBem.REGULAR.isOperacional());
    }

    @Test
    @DisplayName("Deve identificar condições que precisam de atenção")
    void deveIdentificarCondicoesQuePrecisamDeAtencao() {
        assertTrue(CondicaoBem.RUIM.isManutencaoNecessaria());
        assertTrue(CondicaoBem.CRITICO.isManutencaoNecessaria());
    }

    @Test
    @DisplayName("Deve identificar condições que não permitem uso")
    void deveIdentificarCondicoesQueNaoPermitemUso() {
        assertFalse(CondicaoBem.INUTILIZAVEL.isOperacional());
        assertFalse(CondicaoBem.INUTILIZAVEL.isManutencaoNecessaria());
        assertTrue(CondicaoBem.INUTILIZAVEL.isBaixaRecomendada());
    }

    // Método auxiliar para verificar se um array contém um valor específico
    private boolean contemValor(CondicaoBem[] valores, String nomeValor) {
        for (CondicaoBem valor : valores) {
            if (valor.name().equals(nomeValor)) {
                return true;
            }
        }
        return false;
    }
}
