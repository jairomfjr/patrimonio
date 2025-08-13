package com.manus.patrimonio.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do Enum StatusBem")
class StatusBemTest {

    @Test
    @DisplayName("Deve ter todos os valores esperados")
    void deveTerTodosOsValoresEsperados() {
        StatusBem[] valores = StatusBem.values();
        
        assertEquals(10, valores.length);
        
        assertTrue(contemValor(valores, "ATIVO"));
        assertTrue(contemValor(valores, "INATIVO"));
        assertTrue(contemValor(valores, "EM_MANUTENCAO"));
        assertTrue(contemValor(valores, "EM_TRANSITO"));
        assertTrue(contemValor(valores, "RESERVA"));
        assertTrue(contemValor(valores, "BAIXADO"));
        assertTrue(contemValor(valores, "EXTRAVIADO"));
        assertTrue(contemValor(valores, "ROUBADO"));
        assertTrue(contemValor(valores, "DANIFICADO"));
        assertTrue(contemValor(valores, "OBSOLETO"));
    }

    @Test
    @DisplayName("Deve retornar descrição correta para cada valor")
    void deveRetornarDescricaoCorretaParaCadaValor() {
        assertEquals("Ativo", StatusBem.ATIVO.getDescricao());
        assertEquals("Inativo", StatusBem.INATIVO.getDescricao());
        assertEquals("Em Manutenção", StatusBem.EM_MANUTENCAO.getDescricao());
        assertEquals("Em Trânsito", StatusBem.EM_TRANSITO.getDescricao());
        assertEquals("Em Reserva", StatusBem.RESERVA.getDescricao());
        assertEquals("Baixado", StatusBem.BAIXADO.getDescricao());
        assertEquals("Extraviado", StatusBem.EXTRAVIADO.getDescricao());
        assertEquals("Roubado", StatusBem.ROUBADO.getDescricao());
        assertEquals("Danificado", StatusBem.DANIFICADO.getDescricao());
        assertEquals("Obsoleto", StatusBem.OBSOLETO.getDescricao());
    }

    @Test
    @DisplayName("Deve retornar explicação correta para cada valor")
    void deveRetornarExplicacaoCorretaParaCadaValor() {
        assertEquals("Bem em uso normal", StatusBem.ATIVO.getExplicacao());
        assertEquals("Bem temporariamente fora de uso", StatusBem.INATIVO.getExplicacao());
        assertEquals("Bem em processo de manutenção", StatusBem.EM_MANUTENCAO.getExplicacao());
        assertEquals("Bem sendo transferido entre localizações", StatusBem.EM_TRANSITO.getExplicacao());
        assertEquals("Bem disponível para uso futuro", StatusBem.RESERVA.getExplicacao());
        assertEquals("Bem removido do patrimônio", StatusBem.BAIXADO.getExplicacao());
        assertEquals("Bem não localizado", StatusBem.EXTRAVIADO.getExplicacao());
        assertEquals("Bem furtado ou roubado", StatusBem.ROUBADO.getExplicacao());
        assertEquals("Bem com danos que impedem uso", StatusBem.DANIFICADO.getExplicacao());
        assertEquals("Bem tecnologicamente ultrapassado", StatusBem.OBSOLETO.getExplicacao());
    }

    @Test
    @DisplayName("Deve identificar status operacional corretamente")
    void deveIdentificarStatusOperacionalCorretamente() {
        assertTrue(StatusBem.ATIVO.isOperacional());
        assertTrue(StatusBem.RESERVA.isOperacional());
        assertFalse(StatusBem.INATIVO.isOperacional());
        assertFalse(StatusBem.EM_MANUTENCAO.isOperacional());
        assertFalse(StatusBem.EM_TRANSITO.isOperacional());
        assertFalse(StatusBem.BAIXADO.isOperacional());
        assertFalse(StatusBem.EXTRAVIADO.isOperacional());
        assertFalse(StatusBem.ROUBADO.isOperacional());
        assertFalse(StatusBem.DANIFICADO.isOperacional());
        assertFalse(StatusBem.OBSOLETO.isOperacional());
    }

    @Test
    @DisplayName("Deve identificar status de manutenção corretamente")
    void deveIdentificarStatusDeManutencaoCorretamente() {
        assertTrue(StatusBem.EM_MANUTENCAO.isManutencao());
        assertFalse(StatusBem.ATIVO.isManutencao());
        assertFalse(StatusBem.INATIVO.isManutencao());
        assertFalse(StatusBem.EM_TRANSITO.isManutencao());
        assertFalse(StatusBem.RESERVA.isManutencao());
        assertFalse(StatusBem.BAIXADO.isManutencao());
        assertFalse(StatusBem.EXTRAVIADO.isManutencao());
        assertFalse(StatusBem.ROUBADO.isManutencao());
        assertFalse(StatusBem.DANIFICADO.isManutencao());
        assertFalse(StatusBem.OBSOLETO.isManutencao());
    }

    @Test
    @DisplayName("Deve identificar status com problema corretamente")
    void deveIdentificarStatusComProblemaCorretamente() {
        assertTrue(StatusBem.EXTRAVIADO.isProblema());
        assertTrue(StatusBem.ROUBADO.isProblema());
        assertTrue(StatusBem.DANIFICADO.isProblema());
        assertFalse(StatusBem.ATIVO.isProblema());
        assertFalse(StatusBem.INATIVO.isProblema());
        assertFalse(StatusBem.EM_MANUTENCAO.isProblema());
        assertFalse(StatusBem.EM_TRANSITO.isProblema());
        assertFalse(StatusBem.RESERVA.isProblema());
        assertFalse(StatusBem.BAIXADO.isProblema());
        assertFalse(StatusBem.OBSOLETO.isProblema());
    }

    @Test
    @DisplayName("Deve converter string para enum corretamente")
    void deveConverterStringParaEnumCorretamente() {
        assertEquals(StatusBem.ATIVO, StatusBem.valueOf("ATIVO"));
        assertEquals(StatusBem.INATIVO, StatusBem.valueOf("INATIVO"));
        assertEquals(StatusBem.EM_MANUTENCAO, StatusBem.valueOf("EM_MANUTENCAO"));
        assertEquals(StatusBem.EM_TRANSITO, StatusBem.valueOf("EM_TRANSITO"));
        assertEquals(StatusBem.RESERVA, StatusBem.valueOf("RESERVA"));
        assertEquals(StatusBem.BAIXADO, StatusBem.valueOf("BAIXADO"));
        assertEquals(StatusBem.EXTRAVIADO, StatusBem.valueOf("EXTRAVIADO"));
        assertEquals(StatusBem.ROUBADO, StatusBem.valueOf("ROUBADO"));
        assertEquals(StatusBem.DANIFICADO, StatusBem.valueOf("DANIFICADO"));
        assertEquals(StatusBem.OBSOLETO, StatusBem.valueOf("OBSOLETO"));
    }

    @Test
    @DisplayName("Deve lançar exceção para valor inválido")
    void deveLancarExcecaoParaValorInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            StatusBem.valueOf("INVALIDO");
        });
    }

    @Test
    @DisplayName("Deve retornar nome correto para cada valor")
    void deveRetornarNomeCorretoParaCadaValor() {
        assertEquals("ATIVO", StatusBem.ATIVO.name());
        assertEquals("INATIVO", StatusBem.INATIVO.name());
        assertEquals("EM_MANUTENCAO", StatusBem.EM_MANUTENCAO.name());
        assertEquals("EM_TRANSITO", StatusBem.EM_TRANSITO.name());
        assertEquals("RESERVA", StatusBem.RESERVA.name());
        assertEquals("BAIXADO", StatusBem.BAIXADO.name());
        assertEquals("EXTRAVIADO", StatusBem.EXTRAVIADO.name());
        assertEquals("ROUBADO", StatusBem.ROUBADO.name());
        assertEquals("DANIFICADO", StatusBem.DANIFICADO.name());
        assertEquals("OBSOLETO", StatusBem.OBSOLETO.name());
    }

    @Test
    @DisplayName("Deve retornar ordinal correto para cada valor")
    void deveRetornarOrdinalCorretoParaCadaValor() {
        assertEquals(0, StatusBem.ATIVO.ordinal());
        assertEquals(1, StatusBem.INATIVO.ordinal());
        assertEquals(2, StatusBem.EM_MANUTENCAO.ordinal());
        assertEquals(3, StatusBem.EM_TRANSITO.ordinal());
        assertEquals(4, StatusBem.RESERVA.ordinal());
        assertEquals(5, StatusBem.BAIXADO.ordinal());
        assertEquals(6, StatusBem.EXTRAVIADO.ordinal());
        assertEquals(7, StatusBem.ROUBADO.ordinal());
        assertEquals(8, StatusBem.DANIFICADO.ordinal());
        assertEquals(9, StatusBem.OBSOLETO.ordinal());
    }

    @Test
    @DisplayName("Deve implementar toString corretamente")
    void deveImplementarToStringCorretamente() {
        assertEquals("ATIVO", StatusBem.ATIVO.toString());
        assertEquals("INATIVO", StatusBem.INATIVO.toString());
        assertEquals("EM_MANUTENCAO", StatusBem.EM_MANUTENCAO.toString());
        assertEquals("EM_TRANSITO", StatusBem.EM_TRANSITO.toString());
        assertEquals("RESERVA", StatusBem.RESERVA.toString());
        assertEquals("BAIXADO", StatusBem.BAIXADO.toString());
        assertEquals("EXTRAVIADO", StatusBem.EXTRAVIADO.toString());
        assertEquals("ROUBADO", StatusBem.ROUBADO.toString());
        assertEquals("DANIFICADO", StatusBem.DANIFICADO.toString());
        assertEquals("OBSOLETO", StatusBem.OBSOLETO.toString());
    }

    @Test
    @DisplayName("Deve comparar enums corretamente")
    void deveCompararEnumsCorretamente() {
        assertTrue(StatusBem.ATIVO.compareTo(StatusBem.INATIVO) < 0);
        assertTrue(StatusBem.INATIVO.compareTo(StatusBem.ATIVO) > 0);
        assertEquals(0, StatusBem.ATIVO.compareTo(StatusBem.ATIVO));
    }

    // Método auxiliar para verificar se um array contém um valor específico
    private boolean contemValor(StatusBem[] valores, String nomeValor) {
        for (StatusBem valor : valores) {
            if (valor.name().equals(nomeValor)) {
                return true;
            }
        }
        return false;
    }
}
