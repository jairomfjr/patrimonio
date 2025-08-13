package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.LocalizacaoDTO;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.exception.RegraDeNegocioException;
import com.manus.patrimonio.model.Localizacao;
import com.manus.patrimonio.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    /**
     * Busca todas as localizações com paginação
     */
    @Transactional(readOnly = true)
    public Page<LocalizacaoDTO> buscarTodas(Pageable pageable) {
        return localizacaoRepository.findAll(pageable)
                .map(this::converterParaDTO);
    }

    /**
     * Busca localização por ID
     */
    @Transactional(readOnly = true)
    public LocalizacaoDTO buscarPorId(Long id) {
        Localizacao localizacao = localizacaoRepository.findById(id)
                .orElseThrow(() -> RecursoNaoEncontradoException.porId("Localização", id));
        return converterParaDTO(localizacao);
    }

    /**
     * Busca localização por nome
     */
    @Transactional(readOnly = true)
    public LocalizacaoDTO buscarPorNome(String nome) {
        Localizacao localizacao = localizacaoRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> RecursoNaoEncontradoException.porCampo("Localização", "nome", nome));
        return converterParaDTO(localizacao);
    }

    /**
     * Busca localizações por texto
     */
    @Transactional(readOnly = true)
    public Page<LocalizacaoDTO> buscarPorTexto(String termo, Pageable pageable) {
        return localizacaoRepository.buscarPorTexto(termo, pageable)
                .map(this::converterParaDTO);
    }

    /**
     * Busca todas as localizações ordenadas por nome
     */
    @Transactional(readOnly = true)
    public List<LocalizacaoDTO> buscarTodasOrdenadas() {
        return localizacaoRepository.findAllByOrderByNomeAsc()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    /**
     * Busca localizações por responsável
     */
    @Transactional(readOnly = true)
    public List<LocalizacaoDTO> buscarPorResponsavel(String responsavel) {
        return localizacaoRepository.findByResponsavelContainingIgnoreCase(responsavel)
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    /**
     * Busca localizações por endereço
     */
    @Transactional(readOnly = true)
    public List<LocalizacaoDTO> buscarPorEndereco(String endereco) {
        return localizacaoRepository.findByEnderecoContainingIgnoreCase(endereco)
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    /**
     * Cria nova localização
     */
    public LocalizacaoDTO criar(LocalizacaoDTO localizacaoDTO) {
        validarNomeUnico(localizacaoDTO.getNome(), null);
        
        Localizacao localizacao = converterParaEntidade(localizacaoDTO);
        localizacao = localizacaoRepository.save(localizacao);
        return converterParaDTO(localizacao);
    }

    /**
     * Atualiza localização existente
     */
    public LocalizacaoDTO atualizar(Long id, LocalizacaoDTO localizacaoDTO) {
        Localizacao localizacao = localizacaoRepository.findById(id)
                .orElseThrow(() -> RecursoNaoEncontradoException.porId("Localização", id));

        validarNomeUnico(localizacaoDTO.getNome(), id);

        localizacao.setNome(localizacaoDTO.getNome());
        localizacao.setEndereco(localizacaoDTO.getEndereco());
        localizacao.setResponsavel(localizacaoDTO.getResponsavel());
        localizacao.setContato(localizacaoDTO.getContato());
        localizacao.setDescricao(localizacaoDTO.getDescricao());

        localizacao = localizacaoRepository.save(localizacao);
        return converterParaDTO(localizacao);
    }

    /**
     * Exclui localização
     */
    public void excluir(Long id) {
        Localizacao localizacao = localizacaoRepository.findById(id)
                .orElseThrow(() -> RecursoNaoEncontradoException.porId("Localização", id));

        if (!localizacao.getBensAtuais().isEmpty()) {
            throw RegraDeNegocioException.operacaoInvalida(
                "excluir localização", 
                "existem bens atualmente nesta localização"
            );
        }

        localizacaoRepository.delete(localizacao);
    }

    /**
     * Conta bens por localização
     */
    @Transactional(readOnly = true)
    public List<Object[]> contarBensPorLocalizacao() {
        return localizacaoRepository.contarBensPorLocalizacao();
    }

    /**
     * Busca localizações com bens
     */
    @Transactional(readOnly = true)
    public List<LocalizacaoDTO> buscarLocalizacoesComBens() {
        return localizacaoRepository.buscarLocalizacoesComBens()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    /**
     * Busca localizações sem bens
     */
    @Transactional(readOnly = true)
    public List<LocalizacaoDTO> buscarLocalizacoesSemBens() {
        return localizacaoRepository.buscarLocalizacoesSemBens()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // Métodos auxiliares

    private void validarNomeUnico(String nome, Long idExcluir) {
        if (localizacaoRepository.existsByNomeIgnoreCase(nome)) {
            Localizacao localizacaoExistente = localizacaoRepository.findByNomeIgnoreCase(nome).get();
            if (idExcluir == null || !localizacaoExistente.getId().equals(idExcluir)) {
                throw RegraDeNegocioException.duplicacao("localização", "nome", nome);
            }
        }
    }

    private LocalizacaoDTO converterParaDTO(Localizacao localizacao) {
        LocalizacaoDTO dto = new LocalizacaoDTO();
        dto.setId(localizacao.getId());
        dto.setNome(localizacao.getNome());
        dto.setEndereco(localizacao.getEndereco());
        dto.setResponsavel(localizacao.getResponsavel());
        dto.setContato(localizacao.getContato());
        dto.setDescricao(localizacao.getDescricao());
        dto.setDataCriacao(localizacao.getDataCriacao());
        dto.setDataAtualizacao(localizacao.getDataAtualizacao());
        dto.setQuantidadeBens((long) localizacao.getBensAtuais().size());
        return dto;
    }

    private Localizacao converterParaEntidade(LocalizacaoDTO dto) {
        Localizacao localizacao = new Localizacao();
        localizacao.setNome(dto.getNome());
        localizacao.setEndereco(dto.getEndereco());
        localizacao.setResponsavel(dto.getResponsavel());
        localizacao.setContato(dto.getContato());
        localizacao.setDescricao(dto.getDescricao());
        return localizacao;
    }
}

