package com.manus.patrimonio.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Entidade Base")
class EntidadeBaseTest {

    private EntidadeBase entidade;

    @BeforeEach
    void setUp() {
        entidade = new EntidadeBase() {};
    }

    @Test
    @DisplayName("Deve criar entidade com valores padrão")
    void deveCriarEntidadeComValoresPadrao() {
        assertNotNull(entidade);
        assertNull(entidade.getId());
        assertNotNull(entidade.getDataCriacao());
        assertNotNull(entidade.getDataAtualizacao());
        assertEquals(1L, entidade.getVersao());
    }

    @Test
    @DisplayName("Deve definir e retornar ID corretamente")
    void deveDefinirERetornarIdCorretamente() {
        Long id = 1L;
        entidade.setId(id);
        assertEquals(id, entidade.getId());
    }

    @Test
    @DisplayName("Deve definir e retornar data de criação corretamente")
    void deveDefinirERetornarDataCriacaoCorretamente() {
        LocalDateTime dataCriacao = LocalDateTime.now();
        entidade.setDataCriacao(dataCriacao);
        assertEquals(dataCriacao, entidade.getDataCriacao());
    }

    @Test
    @DisplayName("Deve definir e retornar data de atualização corretamente")
    void deveDefinirERetornarDataAtualizacaoCorretamente() {
        LocalDateTime dataAtualizacao = LocalDateTime.now();
        entidade.setDataAtualizacao(dataAtualizacao);
        assertEquals(dataAtualizacao, entidade.getDataAtualizacao());
    }

    @Test
    @DisplayName("Deve definir e retornar versão corretamente")
    void deveDefinirERetornarVersaoCorretamente() {
        Long versao = 5L;
        entidade.setVersao(versao);
        assertEquals(versao, entidade.getVersao());
    }

    @Test
    @DisplayName("Deve identificar entidade como nova quando ID é null")
    void deveIdentificarEntidadeComoNovaQuandoIdENull() {
        assertTrue(entidade.isNovo());
        assertFalse(entidade.isExistente());
    }

    @Test
    @DisplayName("Deve identificar entidade como existente quando ID não é null")
    void deveIdentificarEntidadeComoExistenteQuandoIdNaoENull() {
        entidade.setId(1L);
        assertFalse(entidade.isNovo());
        assertTrue(entidade.isExistente());
    }

    @Test
    @DisplayName("Deve incrementar versão ao atualizar")
    void deveIncrementarVersaoAoAtualizar() {
        Long versaoInicial = 1L;
        assertEquals(versaoInicial, entidade.getVersao());
        
        entidade.onUpdate();
        assertEquals(versaoInicial + 1, entidade.getVersao());
    }

    @Test
    @DisplayName("Deve definir valores padrão ao criar")
    void deveDefinirValoresPadraoAoCriar() {
        EntidadeBase novaEntidade = new EntidadeBase() {};
        novaEntidade.onCreate();
        
        assertNotNull(novaEntidade.getDataCriacao());
        assertNotNull(novaEntidade.getDataAtualizacao());
        assertEquals(1L, novaEntidade.getVersao());
    }

    @Test
    @DisplayName("Deve implementar equals baseado no ID")
    void deveImplementarEqualsBaseadoNoId() {
        EntidadeBase entidade1 = new EntidadeBase() {};
        EntidadeBase entidade2 = new EntidadeBase() {};
        
        // Ambas são novas (ID null)
        assertEquals(entidade1, entidade2);
        
        // Definir IDs diferentes
        entidade1.setId(1L);
        entidade2.setId(2L);
        assertNotEquals(entidade1, entidade2);
        
        // Definir mesmo ID
        entidade2.setId(1L);
        assertEquals(entidade1, entidade2);
    }

    @Test
    @DisplayName("Deve implementar hashCode baseado no ID")
    void deveImplementarHashCodeBaseadoNoId() {
        EntidadeBase entidade1 = new EntidadeBase() {};
        EntidadeBase entidade2 = new EntidadeBase() {};
        
        // Ambas são novas (ID null)
        assertEquals(entidade1.hashCode(), entidade2.hashCode());
        
        // Definir IDs diferentes
        entidade1.setId(1L);
        entidade2.setId(2L);
        assertNotEquals(entidade1.hashCode(), entidade2.hashCode());
        
        // Definir mesmo ID
        entidade2.setId(1L);
        assertEquals(entidade1.hashCode(), entidade2.hashCode());
    }

    @Test
    @DisplayName("Deve implementar toString corretamente")
    void deveImplementarToStringCorretamente() {
        String toString = entidade.toString();
        assertTrue(toString.contains("EntidadeBase"));
        assertTrue(toString.contains("id="));
        assertTrue(toString.contains("dataCriacao="));
        assertTrue(toString.contains("dataAtualizacao="));
        assertTrue(toString.contains("versao="));
    }

    @Test
    @DisplayName("Deve comparar entidades com tipos diferentes")
    void deveCompararEntidadesComTiposDiferentes() {
        EntidadeBase entidade1 = new EntidadeBase() {};
        Object objeto = new Object();
        
        assertNotEquals(entidade1, objeto);
    }

    @Test
    @DisplayName("Deve comparar entidade com null")
    void deveCompararEntidadeComNull() {
        EntidadeBase entidade1 = new EntidadeBase() {};
        
        assertNotEquals(entidade1, null);
    }

    @Test
    @DisplayName("Deve comparar entidade consigo mesma")
    void deveCompararEntidadeConsigoMesma() {
        EntidadeBase entidade1 = new EntidadeBase() {};
        
        assertEquals(entidade1, entidade1);
    }
}
