package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.CategoriaDTO;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.exception.RegraDeNegocioException;
import com.manus.patrimonio.model.Categoria;
import com.manus.patrimonio.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Busca todas as categorias com paginação
     */
    @Transactional(readOnly = true)
    public Page<CategoriaDTO> buscarTodas(Pageable pageable) {
        return categoriaRepository.findAll(pageable)
                .map(this::converterParaDTO);
    }

    /**
     * Busca categoria por ID
     */
    @Transactional(readOnly = true)
    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> RecursoNaoEncontradoException.porId("Categoria", id));
        return converterParaDTO(categoria);
    }

    /**
     * Busca categoria por nome
     */
    @Transactional(readOnly = true)
    public CategoriaDTO buscarPorNome(String nome) {
        Categoria categoria = categoriaRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> RecursoNaoEncontradoException.porCampo("Categoria", "nome", nome));
        return converterParaDTO(categoria);
    }

    /**
     * Busca categorias por texto
     */
    @Transactional(readOnly = true)
    public Page<CategoriaDTO> buscarPorTexto(String termo, Pageable pageable) {
        return categoriaRepository.buscarPorNomeOuDescricao(termo, pageable)
                .map(this::converterParaDTO);
    }

    /**
     * Busca todas as categorias ordenadas por nome
     */
    @Transactional(readOnly = true)
    public List<CategoriaDTO> buscarTodasOrdenadas() {
        return categoriaRepository.findAllByOrderByNomeAsc()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    /**
     * Cria nova categoria
     */
    public CategoriaDTO criar(CategoriaDTO categoriaDTO) {
        validarNomeUnico(categoriaDTO.getNome(), null);
        
        Categoria categoria = converterParaEntidade(categoriaDTO);
        categoria = categoriaRepository.save(categoria);
        return converterParaDTO(categoria);
    }

    /**
     * Atualiza categoria existente
     */
    public CategoriaDTO atualizar(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> RecursoNaoEncontradoException.porId("Categoria", id));

        validarNomeUnico(categoriaDTO.getNome(), id);

        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());

        categoria = categoriaRepository.save(categoria);
        return converterParaDTO(categoria);
    }

    /**
     * Exclui categoria
     */
    public void excluir(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> RecursoNaoEncontradoException.porId("Categoria", id));

        if (!categoria.getBens().isEmpty()) {
            throw RegraDeNegocioException.operacaoInvalida(
                "excluir categoria", 
                "existem bens associados a esta categoria"
            );
        }

        categoriaRepository.delete(categoria);
    }

    /**
     * Conta bens por categoria
     */
    @Transactional(readOnly = true)
    public List<Object[]> contarBensPorCategoria() {
        return categoriaRepository.contarBensPorCategoria();
    }

    /**
     * Busca categorias com bens
     */
    @Transactional(readOnly = true)
    public List<CategoriaDTO> buscarCategoriasComBens() {
        return categoriaRepository.buscarCategoriasComBens()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    /**
     * Busca categorias sem bens
     */
    @Transactional(readOnly = true)
    public List<CategoriaDTO> buscarCategoriasSemBens() {
        return categoriaRepository.buscarCategoriasSemBens()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // Métodos auxiliares

    private void validarNomeUnico(String nome, Long idExcluir) {
        if (categoriaRepository.existsByNomeIgnoreCase(nome)) {
            Categoria categoriaExistente = categoriaRepository.findByNomeIgnoreCase(nome).get();
            if (idExcluir == null || !categoriaExistente.getId().equals(idExcluir)) {
                throw RegraDeNegocioException.duplicacao("categoria", "nome", nome);
            }
        }
    }

    private CategoriaDTO converterParaDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        dto.setDescricao(categoria.getDescricao());
        dto.setDataCriacao(categoria.getDataCriacao());
        dto.setDataAtualizacao(categoria.getDataAtualizacao());
        dto.setQuantidadeBens((long) categoria.getBens().size());
        return dto;
    }

    private Categoria converterParaEntidade(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        return categoria;
    }
}

