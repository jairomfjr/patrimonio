package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.BemDTO;
import com.manus.patrimonio.enums.CondicaoBem;
import com.manus.patrimonio.enums.StatusBem;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.exception.RegraDeNegocioException;
import com.manus.patrimonio.mapper.PatrimonioMapper;
import com.manus.patrimonio.model.Bem;
import com.manus.patrimonio.model.Categoria;
import com.manus.patrimonio.model.Localizacao;
import com.manus.patrimonio.repository.BemRepository;
import com.manus.patrimonio.repository.CategoriaRepository;
import com.manus.patrimonio.repository.LocalizacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do BemService")
class BemServiceTest {

    @Mock
    private BemRepository bemRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private LocalizacaoRepository localizacaoRepository;

    @Mock
    private PatrimonioMapper patrimonioMapper;

    @InjectMocks
    private BemService bemService;

    private BemDTO bemDTO;
    private Bem bem;
    private Categoria categoria;
    private Localizacao localizacao;

    @BeforeEach
    void setUp() {
        categoria = new Categoria("Equipamentos", "Equipamentos de informática");
        categoria.setId(1L);
        
        localizacao = new Localizacao("Sala 101", "Rua A, 123", "João Silva", "joao@empresa.com", "Sala de TI");
        localizacao.setId(1L);
        
        bem = new Bem("Computador Dell", "BEM001", "Computador Dell para desenvolvimento", categoria, localizacao, 
                     LocalDate.now(), new BigDecimal("5000.00"));
        bem.setId(1L);
        bem.setStatus(StatusBem.ATIVO);
        bem.setCondicao(CondicaoBem.BOM);
        bem.setAtivo(true);
        
        bemDTO = new BemDTO();
        bemDTO.setId(1L);
        bemDTO.setNome("Computador Dell");
        bemDTO.setDescricao("Computador Dell para desenvolvimento");
        bemDTO.setNumeroSerie("SN123456789");
        bemDTO.setDataAquisicao(LocalDate.now());
        bemDTO.setValorAquisicao(new BigDecimal("5000.00"));
        bemDTO.setStatus(StatusBem.ATIVO);
        bemDTO.setCondicao(CondicaoBem.BOM);
        bemDTO.setCategoriaId(1L);
        bemDTO.setLocalizacaoAtualId(1L);
    }

    @Test
    @DisplayName("Deve criar bem com sucesso")
    void deveCriarBemComSucesso() {
        // Arrange
        when(patrimonioMapper.bemDTOToBem(any(BemDTO.class))).thenReturn(bem);
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(localizacaoRepository.findById(1L)).thenReturn(Optional.of(localizacao));
        when(bemRepository.existsByNumeroSerie("SN123456789")).thenReturn(false);
        when(bemRepository.save(any(Bem.class))).thenReturn(bem);
        when(patrimonioMapper.bemToBemDTO(any(Bem.class))).thenReturn(bemDTO);

        // Act
        BemDTO resultado = bemService.criar(bemDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(bemDTO.getId(), resultado.getId());
        verify(bemRepository).save(any(Bem.class));
        verify(bemRepository).existsByNumeroSerie("SN123456789");
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar bem com número de série duplicado")
    void deveLancarExcecaoAoCriarBemComNumeroSerieDuplicado() {
        // Arrange
        when(bemRepository.existsByNumeroSerie("SN123456789")).thenReturn(true);

        // Act & Assert
        RegraDeNegocioException excecao = assertThrows(RegraDeNegocioException.class, 
            () -> bemService.criar(bemDTO));
        
        assertTrue(excecao.getMessage().contains("Número de série já existe"));
        verify(bemRepository, never()).save(any(Bem.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar bem com categoria inexistente")
    void deveLancarExcecaoAoCriarBemComCategoriaInexistente() {
        // Arrange
        when(bemRepository.existsByNumeroSerie("SN123456789")).thenReturn(false);
        when(categoriaRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RecursoNaoEncontradoException excecao = assertThrows(RecursoNaoEncontradoException.class, 
            () -> bemService.criar(bemDTO));
        
        assertTrue(excecao.getMessage().contains("Categoria não encontrada"));
        verify(bemRepository, never()).save(any(Bem.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar bem com localização inexistente")
    void deveLancarExcecaoAoCriarBemComLocalizacaoInexistente() {
        // Arrange
        when(bemRepository.existsByNumeroSerie("SN123456789")).thenReturn(false);
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(localizacaoRepository.findById(1L)).thenReturn(Optional.empty());
        when(patrimonioMapper.bemDTOToBem(any(BemDTO.class))).thenReturn(bem);

        // Act & Assert
        RecursoNaoEncontradoException excecao = assertThrows(RecursoNaoEncontradoException.class, 
            () -> bemService.criar(bemDTO));
        
        assertTrue(excecao.getMessage().contains("Localização não encontrada"));
        verify(bemRepository, never()).save(any(Bem.class));
    }

    @Test
    @DisplayName("Deve buscar bem por ID com sucesso")
    void deveBuscarBemPorIdComSucesso() {
        // Arrange
        when(bemRepository.findById(1L)).thenReturn(Optional.of(bem));
        when(patrimonioMapper.bemToBemDTO(bem)).thenReturn(bemDTO);

        // Act
        BemDTO resultado = bemService.buscarPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(bemDTO.getId(), resultado.getId());
        verify(bemRepository).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar bem por ID inexistente")
    void deveLancarExcecaoAoBuscarBemPorIdInexistente() {
        // Arrange
        when(bemRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RecursoNaoEncontradoException excecao = assertThrows(RecursoNaoEncontradoException.class, 
            () -> bemService.buscarPorId(1L));
        
        assertTrue(excecao.getMessage().contains("Bem não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar bem por número de série com sucesso")
    void deveBuscarBemPorNumeroSerieComSucesso() {
        // Arrange
        when(bemRepository.findByNumeroSerie("SN123456789")).thenReturn(Optional.of(bem));
        when(patrimonioMapper.bemToBemDTO(bem)).thenReturn(bemDTO);

        // Act
        BemDTO resultado = bemService.buscarPorNumeroSerie("SN123456789");

        // Assert
        assertNotNull(resultado);
        assertEquals(bemDTO.getId(), resultado.getId());
        verify(bemRepository).findByNumeroSerie("SN123456789");
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar bem por número de série inexistente")
    void deveLancarExcecaoAoBuscarBemPorNumeroSerieInexistente() {
        // Arrange
        when(bemRepository.findByNumeroSerie("SN123456789")).thenReturn(Optional.empty());

        // Act & Assert
        RecursoNaoEncontradoException excecao = assertThrows(RecursoNaoEncontradoException.class, 
            () -> bemService.buscarPorNumeroSerie("SN123456789"));
        
        assertTrue(excecao.getMessage().contains("Bem não encontrado"));
    }

    @Test
    @DisplayName("Deve buscar todos os bens com paginação")
    void deveBuscarTodosOsBensComPaginacao() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bem> bensPage = new PageImpl<>(List.of(bem));
        when(bemRepository.findAll(pageable)).thenReturn(bensPage);
        when(patrimonioMapper.bemToBemDTO(bem)).thenReturn(bemDTO);

        // Act
        Page<BemDTO> resultado = bemService.buscarTodos(pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(bemRepository).findAll(pageable);
    }

    @Test
    @DisplayName("Deve atualizar bem com sucesso")
    void deveAtualizarBemComSucesso() {
        // Arrange
        BemDTO bemDTOAtualizado = new BemDTO();
        bemDTOAtualizado.setId(1L);
        bemDTOAtualizado.setNome("Computador Dell Atualizado");
        bemDTOAtualizado.setNumeroSerie("SN123456789");
        bemDTOAtualizado.setCategoriaId(1L);
        bemDTOAtualizado.setLocalizacaoAtualId(1L);
        bemDTOAtualizado.setDataAquisicao(LocalDate.now());
        bemDTOAtualizado.setValorAquisicao(new BigDecimal("6000.00"));
        bemDTOAtualizado.setStatus(StatusBem.ATIVO);
        bemDTOAtualizado.setCondicao(CondicaoBem.BOM);

        when(bemRepository.findById(1L)).thenReturn(Optional.of(bem));
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(localizacaoRepository.findById(1L)).thenReturn(Optional.of(localizacao));
        when(bemRepository.existsByNumeroSerie("SN123456789")).thenReturn(false);
        when(bemRepository.save(any(Bem.class))).thenReturn(bem);
        when(patrimonioMapper.bemToBemDTO(any(Bem.class))).thenReturn(bemDTOAtualizado);

        // Act
        BemDTO resultado = bemService.atualizar(1L, bemDTOAtualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals("Computador Dell Atualizado", resultado.getNome());
        verify(bemRepository).save(any(Bem.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar bem inexistente")
    void deveLancarExcecaoAoAtualizarBemInexistente() {
        // Arrange
        when(bemRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RecursoNaoEncontradoException excecao = assertThrows(RecursoNaoEncontradoException.class, 
            () -> bemService.atualizar(1L, bemDTO));
        
        assertTrue(excecao.getMessage().contains("Bem não encontrado"));
        verify(bemRepository, never()).save(any(Bem.class));
    }

    @Test
    @DisplayName("Deve excluir bem com sucesso")
    void deveExcluirBemComSucesso() {
        // Arrange
        bem.setStatus(StatusBem.ATIVO);
        when(bemRepository.findById(1L)).thenReturn(Optional.of(bem));
        doNothing().when(bemRepository).delete(bem);

        // Act
        bemService.excluir(1L);

        // Assert
        verify(bemRepository).delete(bem);
    }

    @Test
    @DisplayName("Deve lançar exceção ao excluir bem inexistente")
    void deveLancarExcecaoAoExcluirBemInexistente() {
        // Arrange
        when(bemRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RecursoNaoEncontradoException excecao = assertThrows(RecursoNaoEncontradoException.class, 
            () -> bemService.excluir(1L));
        
        assertTrue(excecao.getMessage().contains("Bem não encontrado"));
        verify(bemRepository, never()).delete(any(Bem.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao excluir bem que não pode ser baixado")
    void deveLancarExcecaoAoExcluirBemQueNaoPodeSerBaixado() {
        // Arrange
        bem.setStatus(StatusBem.BAIXADO);
        when(bemRepository.findById(1L)).thenReturn(Optional.of(bem));

        // Act & Assert
        RegraDeNegocioException excecao = assertThrows(RegraDeNegocioException.class, 
            () -> bemService.excluir(1L));
        
        assertTrue(excecao.getMessage().contains("não pode ser excluído"));
        verify(bemRepository, never()).delete(any(Bem.class));
    }

    @Test
    @DisplayName("Deve ativar bem com sucesso")
    void deveAtivarBemComSucesso() {
        // Arrange
        bem.setAtivo(false);
        bem.setStatus(StatusBem.INATIVO);
        when(bemRepository.findById(1L)).thenReturn(Optional.of(bem));
        when(bemRepository.save(any(Bem.class))).thenReturn(bem);
        when(patrimonioMapper.bemToBemDTO(any(Bem.class))).thenReturn(bemDTO);

        // Act
        BemDTO resultado = bemService.ativar(1L);

        // Assert
        assertNotNull(resultado);
        verify(bemRepository).save(any(Bem.class));
    }

    @Test
    @DisplayName("Deve desativar bem com sucesso")
    void deveDesativarBemComSucesso() {
        // Arrange
        bem.setAtivo(true);
        bem.setStatus(StatusBem.ATIVO);
        when(bemRepository.findById(1L)).thenReturn(Optional.of(bem));
        when(bemRepository.save(any(Bem.class))).thenReturn(bem);
        when(patrimonioMapper.bemToBemDTO(any(Bem.class))).thenReturn(bemDTO);

        // Act
        BemDTO resultado = bemService.desativar(1L);

        // Assert
        assertNotNull(resultado);
        verify(bemRepository).save(any(Bem.class));
    }

    @Test
    @DisplayName("Deve alterar status do bem com sucesso")
    void deveAlterarStatusDoBemComSucesso() {
        // Arrange
        StatusBem novoStatus = StatusBem.EM_MANUTENCAO;
        when(bemRepository.findById(1L)).thenReturn(Optional.of(bem));
        when(bemRepository.save(any(Bem.class))).thenReturn(bem);
        when(patrimonioMapper.bemToBemDTO(any(Bem.class))).thenReturn(bemDTO);

        // Act
        BemDTO resultado = bemService.alterarStatus(1L, novoStatus);

        // Assert
        assertNotNull(resultado);
        verify(bemRepository).save(any(Bem.class));
    }

    @Test
    @DisplayName("Deve alterar condição do bem com sucesso")
    void deveAlterarCondicaoDoBemComSucesso() {
        // Arrange
        CondicaoBem novaCondicao = CondicaoBem.RUIM;
        when(bemRepository.findById(1L)).thenReturn(Optional.of(bem));
        when(bemRepository.save(any(Bem.class))).thenReturn(bem);
        when(patrimonioMapper.bemToBemDTO(any(Bem.class))).thenReturn(bemDTO);

        // Act
        BemDTO resultado = bemService.alterarCondicao(1L, novaCondicao);

        // Assert
        assertNotNull(resultado);
        verify(bemRepository).save(any(Bem.class));
    }

    @Test
    @DisplayName("Deve buscar bens por categoria")
    void deveBuscarBensPorCategoria() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bem> bensPage = new PageImpl<>(List.of(bem));
        when(bemRepository.findByCategoriaId(1L, pageable)).thenReturn(bensPage);
        when(patrimonioMapper.bemToBemDTO(bem)).thenReturn(bemDTO);

        // Act
        Page<BemDTO> resultado = bemService.buscarPorCategoria(1L, pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(bemRepository).findByCategoriaId(1L, pageable);
    }

    @Test
    @DisplayName("Deve buscar bens por localização")
    void deveBuscarBensPorLocalizacao() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bem> bensPage = new PageImpl<>(List.of(bem));
        when(bemRepository.findByLocalizacaoAtualId(1L, pageable)).thenReturn(bensPage);
        when(patrimonioMapper.bemToBemDTO(bem)).thenReturn(bemDTO);

        // Act
        Page<BemDTO> resultado = bemService.buscarPorLocalizacao(1L, pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(bemRepository).findByLocalizacaoAtualId(1L, pageable);
    }

    @Test
    @DisplayName("Deve buscar bens por status")
    void deveBuscarBensPorStatus() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bem> bensPage = new PageImpl<>(List.of(bem));
        when(bemRepository.findByStatus(StatusBem.ATIVO, pageable)).thenReturn(bensPage);
        when(patrimonioMapper.bemToBemDTO(bem)).thenReturn(bemDTO);

        // Act
        Page<BemDTO> resultado = bemService.buscarPorStatus(StatusBem.ATIVO, pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(bemRepository).findByStatus(StatusBem.ATIVO, pageable);
    }

    @Test
    @DisplayName("Deve buscar bens por condição")
    void deveBuscarBensPorCondicao() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bem> bensPage = new PageImpl<>(List.of(bem));
        when(bemRepository.findByCondicao(CondicaoBem.BOM, pageable)).thenReturn(bensPage);
        when(patrimonioMapper.bemToBemDTO(bem)).thenReturn(bemDTO);

        // Act
        Page<BemDTO> resultado = bemService.buscarPorCondicao(CondicaoBem.BOM, pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(bemRepository).findByCondicao(CondicaoBem.BOM, pageable);
    }

    @Test
    @DisplayName("Deve buscar bens por valor de aquisição")
    void deveBuscarBensPorValorAquisicao() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bem> bensPage = new PageImpl<>(List.of(bem));
        BigDecimal valorMinimo = new BigDecimal("1000.00");
        BigDecimal valorMaximo = new BigDecimal("10000.00");
        when(bemRepository.findByValorAquisicaoBetween(valorMinimo, valorMaximo, pageable)).thenReturn(bensPage);
        when(patrimonioMapper.bemToBemDTO(bem)).thenReturn(bemDTO);

        // Act
        Page<BemDTO> resultado = bemService.buscarPorValorAquisicao(valorMinimo, valorMaximo, pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(bemRepository).findByValorAquisicaoBetween(valorMinimo, valorMaximo, pageable);
    }

    @Test
    @DisplayName("Deve buscar bens por data de aquisição")
    void deveBuscarBensPorDataAquisicao() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bem> bensPage = new PageImpl<>(List.of(bem));
        LocalDate dataInicio = LocalDate.of(2023, 1, 1);
        LocalDate dataFim = LocalDate.of(2023, 12, 31);
        when(bemRepository.findByDataAquisicaoBetween(dataInicio, dataFim, pageable)).thenReturn(bensPage);
        when(patrimonioMapper.bemToBemDTO(bem)).thenReturn(bemDTO);

        // Act
        Page<BemDTO> resultado = bemService.buscarPorDataAquisicao(dataInicio, dataFim, pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(bemRepository).findByDataAquisicaoBetween(dataInicio, dataFim, pageable);
    }

    @Test
    @DisplayName("Deve buscar bens por texto")
    void deveBuscarBensPorTexto() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bem> bensPage = new PageImpl<>(List.of(bem));
        String termo = "computador";
        when(bemRepository.buscarPorTexto(termo, pageable)).thenReturn(bensPage);
        when(patrimonioMapper.bemToBemDTO(bem)).thenReturn(bemDTO);

        // Act
        Page<BemDTO> resultado = bemService.buscarPorTexto(termo, pageable);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(bemRepository).buscarPorTexto(termo, pageable);
    }
}
