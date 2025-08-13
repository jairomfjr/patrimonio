package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.ConfiguracaoDTO;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.exception.RegraDeNegocioException;
import com.manus.patrimonio.mapper.PatrimonioMapper;
import com.manus.patrimonio.model.Configuracao;
import com.manus.patrimonio.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConfiguracaoService {

    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @Autowired
    private PatrimonioMapper patrimonioMapper;

    // CRUD Operations
    public ConfiguracaoDTO criar(ConfiguracaoDTO configuracaoDTO) {
        validarConfiguracao(configuracaoDTO);
        
        // Verificar se chave já existe
        if (configuracaoRepository.existsByChave(configuracaoDTO.getChave())) {
            throw new RegraDeNegocioException("Chave de configuração já existe");
        }
        
        Configuracao configuracao = patrimonioMapper.configuracaoDTOToConfiguracao(configuracaoDTO);
        Configuracao configuracaoSalva = configuracaoRepository.save(configuracao);
        return patrimonioMapper.configuracaoToConfiguracaoDTO(configuracaoSalva);
    }

    @Cacheable(value = "configuracoes", key = "#id")
    public ConfiguracaoDTO buscarPorId(Long id) {
        Configuracao configuracao = configuracaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Configuração não encontrada"));
        
        return patrimonioMapper.configuracaoToConfiguracaoDTO(configuracao);
    }

    @Cacheable(value = "configuracoes", key = "#chave")
    public ConfiguracaoDTO buscarPorChave(String chave) {
        Configuracao configuracao = configuracaoRepository.findByChave(chave)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Configuração não encontrada"));
        
        return patrimonioMapper.configuracaoToConfiguracaoDTO(configuracao);
    }

    @Cacheable(value = "configuracoes", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<ConfiguracaoDTO> buscarTodas(Pageable pageable) {
        Page<Configuracao> configuracoes = configuracaoRepository.findAll(pageable);
        return configuracoes.map(patrimonioMapper::configuracaoToConfiguracaoDTO);
    }

    @CacheEvict(value = "configuracoes", allEntries = true)
    public ConfiguracaoDTO atualizar(Long id, ConfiguracaoDTO configuracaoDTO) {
        Configuracao configuracaoExistente = configuracaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Configuração não encontrada"));
        
        validarConfiguracao(configuracaoDTO);
        
        // Verificar se chave foi alterada e se já existe
        if (!configuracaoExistente.getChave().equals(configuracaoDTO.getChave()) &&
            configuracaoRepository.existsByChave(configuracaoDTO.getChave())) {
            throw new RegraDeNegocioException("Chave de configuração já existe");
        }
        
        // Não permitir alterar configurações do sistema
        if (!configuracaoExistente.isEditavel()) {
            throw new RegraDeNegocioException("Não é possível alterar configurações do sistema");
        }
        
        // Atualizar campos
        configuracaoExistente.setChave(configuracaoDTO.getChave());
        configuracaoExistente.setValor(configuracaoDTO.getValor());
        configuracaoExistente.setDescricao(configuracaoDTO.getDescricao());
        configuracaoExistente.setTipo(configuracaoDTO.getTipo());
        
        Configuracao configuracaoAtualizada = configuracaoRepository.save(configuracaoExistente);
        return patrimonioMapper.configuracaoToConfiguracaoDTO(configuracaoAtualizada);
    }

    @CacheEvict(value = "configuracoes", allEntries = true)
    public void excluir(Long id) {
        Configuracao configuracao = configuracaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Configuração não encontrada"));
        
        // Não permitir excluir configurações do sistema
        if (!configuracao.isEditavel()) {
            throw new RegraDeNegocioException("Não é possível excluir configurações do sistema");
        }
        
        configuracaoRepository.delete(configuracao);
    }

    // Business Operations
    @CacheEvict(value = "configuracoes", allEntries = true)
    public ConfiguracaoDTO atualizarValor(String chave, String novoValor) {
        Configuracao configuracao = configuracaoRepository.findByChave(chave)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Configuração não encontrada"));
        
        if (!configuracao.isEditavel()) {
            throw new RegraDeNegocioException("Não é possível alterar esta configuração");
        }
        
        configuracao.setValor(novoValor);
        Configuracao configuracaoAtualizada = configuracaoRepository.save(configuracao);
        return patrimonioMapper.configuracaoToConfiguracaoDTO(configuracaoAtualizada);
    }

    @CacheEvict(value = "configuracoes", allEntries = true)
    public ConfiguracaoDTO atualizarValorPorId(Long id, String novoValor) {
        Configuracao configuracao = configuracaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Configuração não encontrada"));
        
        if (!configuracao.isEditavel()) {
            throw new RegraDeNegocioException("Não é possível alterar esta configuração");
        }
        
        configuracao.setValor(novoValor);
        Configuracao configuracaoAtualizada = configuracaoRepository.save(configuracao);
        return patrimonioMapper.configuracaoToConfiguracaoDTO(configuracaoAtualizada);
    }

    // Search Operations
    public Page<ConfiguracaoDTO> buscarPorChave(String chave, Pageable pageable) {
        Page<Configuracao> configuracoes = configuracaoRepository.findByChaveContainingIgnoreCase(chave, pageable);
        return configuracoes.map(patrimonioMapper::configuracaoToConfiguracaoDTO);
    }

    public Page<ConfiguracaoDTO> buscarPorTipo(String tipo, Pageable pageable) {
        Page<Configuracao> configuracoes = configuracaoRepository.findByTipo(tipo, pageable);
        return configuracoes.map(patrimonioMapper::configuracaoToConfiguracaoDTO);
    }

    public Page<ConfiguracaoDTO> buscarPorEditavel(Boolean editavel, Pageable pageable) {
        Page<Configuracao> configuracoes = configuracaoRepository.findByEditavel(editavel, pageable);
        return configuracoes.map(patrimonioMapper::configuracaoToConfiguracaoDTO);
    }

    public Page<ConfiguracaoDTO> buscarPorDescricao(String descricao, Pageable pageable) {
        Page<Configuracao> configuracoes = configuracaoRepository.findByDescricaoContainingIgnoreCase(descricao, pageable);
        return configuracoes.map(patrimonioMapper::configuracaoToConfiguracaoDTO);
    }

    // Statistics and Reports
    public Long contarConfiguracoesPorTipo(String tipo) {
        return configuracaoRepository.countByTipo(tipo);
    }

    public Long contarConfiguracoesEditaveis() {
        return configuracaoRepository.countByEditavelTrue();
    }

    public Long contarConfiguracoesNaoEditaveis() {
        List<Configuracao> configuracoes = configuracaoRepository.findByEditavelOrderByChave(false);
        return (long) configuracoes.size();
    }

    public List<ConfiguracaoDTO> buscarConfiguracoesDoSistema() {
        List<Configuracao> configuracoes = configuracaoRepository.findByEditavelOrderByChave(false);
        return patrimonioMapper.configuracoesToConfiguracaoDTOs(configuracoes);
    }

    public List<ConfiguracaoDTO> buscarConfiguracoesEditaveis() {
        List<Configuracao> configuracoes = configuracaoRepository.findByEditavelOrderByChave(true);
        return patrimonioMapper.configuracoesToConfiguracaoDTOs(configuracoes);
    }

    // Utility Methods
    public String getValor(String chave) {
        Optional<Configuracao> configuracao = configuracaoRepository.findByChave(chave);
        return configuracao.map(Configuracao::getValor).orElse(null);
    }

    public String getValor(String chave, String valorPadrao) {
        Optional<Configuracao> configuracao = configuracaoRepository.findByChave(chave);
        return configuracao.map(Configuracao::getValor).orElse(valorPadrao);
    }

    public Integer getValorAsInteger(String chave) {
        Optional<Configuracao> configuracao = configuracaoRepository.findByChave(chave);
        if (configuracao.isPresent()) {
            try {
                return Integer.parseInt(configuracao.get().getValor());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public Integer getValorAsInteger(String chave, Integer valorPadrao) {
        Integer valor = getValorAsInteger(chave);
        return valor != null ? valor : valorPadrao;
    }

    public Boolean getValorAsBoolean(String chave) {
        Optional<Configuracao> configuracao = configuracaoRepository.findByChave(chave);
        if (configuracao.isPresent()) {
            return Boolean.parseBoolean(configuracao.get().getValor());
        }
        return null;
    }

    public Boolean getValorAsBoolean(String chave, Boolean valorPadrao) {
        Boolean valor = getValorAsBoolean(chave);
        return valor != null ? valor : valorPadrao;
    }

    public Double getValorAsDouble(String chave) {
        Optional<Configuracao> configuracao = configuracaoRepository.findByChave(chave);
        if (configuracao.isPresent()) {
            try {
                return Double.parseDouble(configuracao.get().getValor());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    public Double getValorAsDouble(String chave, Double valorPadrao) {
        Double valor = getValorAsDouble(chave);
        return valor != null ? valor : valorPadrao;
    }

    // Validation
    private void validarConfiguracao(ConfiguracaoDTO configuracaoDTO) {
        if (configuracaoDTO.getChave() == null || configuracaoDTO.getChave().trim().length() < 3) {
            throw new RegraDeNegocioException("Chave deve ter pelo menos 3 caracteres");
        }
        
        if (configuracaoDTO.getChave().trim().length() > 100) {
            throw new RegraDeNegocioException("Chave deve ter no máximo 100 caracteres");
        }
        
        if (configuracaoDTO.getTipo() == null || configuracaoDTO.getTipo().trim().length() < 2) {
            throw new RegraDeNegocioException("Tipo é obrigatório e deve ter pelo menos 2 caracteres");
        }
        
        if (configuracaoDTO.getTipo().trim().length() > 50) {
            throw new RegraDeNegocioException("Tipo deve ter no máximo 50 caracteres");
        }
        
        if (configuracaoDTO.getValor() != null && configuracaoDTO.getValor().trim().length() > 1000) {
            throw new RegraDeNegocioException("Valor deve ter no máximo 1000 caracteres");
        }
        
        if (configuracaoDTO.getDescricao() != null && configuracaoDTO.getDescricao().trim().length() > 500) {
            throw new RegraDeNegocioException("Descrição deve ter no máximo 500 caracteres");
        }
    }
}
