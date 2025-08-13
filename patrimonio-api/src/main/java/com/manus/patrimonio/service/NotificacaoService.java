package com.manus.patrimonio.service;

import com.manus.patrimonio.dto.NotificacaoDTO;
import com.manus.patrimonio.exception.RecursoNaoEncontradoException;
import com.manus.patrimonio.exception.RegraDeNegocioException;
import com.manus.patrimonio.mapper.PatrimonioMapper;
import com.manus.patrimonio.model.Notificacao;
import com.manus.patrimonio.model.Usuario;
import com.manus.patrimonio.repository.NotificacaoRepository;
import com.manus.patrimonio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PatrimonioMapper patrimonioMapper;

    // CRUD Operations
    public NotificacaoDTO criar(NotificacaoDTO notificacaoDTO) {
        validarNotificacao(notificacaoDTO);
        
        Notificacao notificacao = patrimonioMapper.notificacaoDTOToNotificacao(notificacaoDTO);
        
        // Buscar o usuário
        Usuario usuario = usuarioRepository.findById(notificacaoDTO.getUsuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
        
        notificacao.setUsuario(usuario);
        notificacao.setDataEnvio(LocalDateTime.now());
        
        Notificacao notificacaoSalva = notificacaoRepository.save(notificacao);
        return patrimonioMapper.notificacaoToNotificacaoDTO(notificacaoSalva);
    }

    @Cacheable(value = "notificacoes", key = "#id")
    public NotificacaoDTO buscarPorId(Long id) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Notificação não encontrada"));
        
        return patrimonioMapper.notificacaoToNotificacaoDTO(notificacao);
    }

    @Cacheable(value = "notificacoes", key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<NotificacaoDTO> buscarTodas(Pageable pageable) {
        Page<Notificacao> notificacoes = notificacaoRepository.findAll(pageable);
        return notificacoes.map(patrimonioMapper::notificacaoToNotificacaoDTO);
    }

    @CacheEvict(value = "notificacoes", allEntries = true)
    public NotificacaoDTO atualizar(Long id, NotificacaoDTO notificacaoDTO) {
        Notificacao notificacaoExistente = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Notificação não encontrada"));
        
        validarNotificacao(notificacaoDTO);
        
        // Atualizar campos
        notificacaoExistente.setTitulo(notificacaoDTO.getTitulo());
        notificacaoExistente.setMensagem(notificacaoDTO.getMensagem());
        notificacaoExistente.setTipo(notificacaoDTO.getTipo());
        notificacaoExistente.setPrioridade(notificacaoDTO.getPrioridade());
        notificacaoExistente.setCategoria(notificacaoDTO.getCategoria());
        
        Notificacao notificacaoAtualizada = notificacaoRepository.save(notificacaoExistente);
        return patrimonioMapper.notificacaoToNotificacaoDTO(notificacaoAtualizada);
    }

    @CacheEvict(value = "notificacoes", allEntries = true)
    public void excluir(Long id) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Notificação não encontrada"));
        
        notificacaoRepository.delete(notificacao);
    }

    // Business Operations
    @CacheEvict(value = "notificacoes", allEntries = true)
    public NotificacaoDTO marcarComoLida(Long id) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Notificação não encontrada"));
        
        notificacao.setLida(true);
        notificacao.setDataLeitura(LocalDateTime.now());
        
        Notificacao notificacaoAtualizada = notificacaoRepository.save(notificacao);
        return patrimonioMapper.notificacaoToNotificacaoDTO(notificacaoAtualizada);
    }

    @CacheEvict(value = "notificacoes", allEntries = true)
    public NotificacaoDTO marcarComoNaoLida(Long id) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Notificação não encontrada"));
        
        notificacao.setLida(false);
        notificacao.setDataLeitura(null);
        
        Notificacao notificacaoAtualizada = notificacaoRepository.save(notificacao);
        return patrimonioMapper.notificacaoToNotificacaoDTO(notificacaoAtualizada);
    }

    @CacheEvict(value = "notificacoes", allEntries = true)
    public NotificacaoDTO alterarPrioridade(Long id, Integer novaPrioridade) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Notificação não encontrada"));
        
        if (novaPrioridade < 1 || novaPrioridade > 5) {
            throw new RegraDeNegocioException("Prioridade deve estar entre 1 e 5");
        }
        
        notificacao.setPrioridade(novaPrioridade);
        Notificacao notificacaoAtualizada = notificacaoRepository.save(notificacao);
        return patrimonioMapper.notificacaoToNotificacaoDTO(notificacaoAtualizada);
    }

    // Search Operations
    public Page<NotificacaoDTO> buscarPorUsuario(Long usuarioId, Pageable pageable) {
        Page<Notificacao> notificacoes = notificacaoRepository.findByUsuarioId(usuarioId, pageable);
        return notificacoes.map(patrimonioMapper::notificacaoToNotificacaoDTO);
    }

    public Page<NotificacaoDTO> buscarPorTipo(String tipo, Pageable pageable) {
        Page<Notificacao> notificacoes = notificacaoRepository.findByTipo(tipo, pageable);
        return notificacoes.map(patrimonioMapper::notificacaoToNotificacaoDTO);
    }

    public Page<NotificacaoDTO> buscarPorStatus(Boolean lida, Pageable pageable) {
        Page<Notificacao> notificacoes = notificacaoRepository.findByLida(lida, pageable);
        return notificacoes.map(patrimonioMapper::notificacaoToNotificacaoDTO);
    }

    public Page<NotificacaoDTO> buscarPorPrioridade(Integer prioridade, Pageable pageable) {
        List<Notificacao> notificacoes = notificacaoRepository.findByPrioridadeGreaterThanEqualOrderByPrioridadeDesc(prioridade);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), notificacoes.size());
        List<Notificacao> pageContent = notificacoes.subList(start, end);
        return new org.springframework.data.domain.PageImpl<>(pageContent, pageable, notificacoes.size())
                .map(patrimonioMapper::notificacaoToNotificacaoDTO);
    }

    public Page<NotificacaoDTO> buscarPorCategoria(String categoria, Pageable pageable) {
        List<Notificacao> notificacoes = notificacaoRepository.findByCategoriaContainingIgnoreCaseOrderByDataEnvioDesc(categoria);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), notificacoes.size());
        List<Notificacao> pageContent = notificacoes.subList(start, end);
        return new org.springframework.data.domain.PageImpl<>(pageContent, pageable, notificacoes.size())
                .map(patrimonioMapper::notificacaoToNotificacaoDTO);
    }

    public Page<NotificacaoDTO> buscarPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable) {
        List<Notificacao> notificacoes = notificacaoRepository.findByDataEnvioBetweenOrderByDataEnvioDesc(dataInicio, dataFim);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), notificacoes.size());
        List<Notificacao> pageContent = notificacoes.subList(start, end);
        return new org.springframework.data.domain.PageImpl<>(pageContent, pageable, notificacoes.size())
                .map(patrimonioMapper::notificacaoToNotificacaoDTO);
    }

    public Page<NotificacaoDTO> buscarPorTentativasEnvio(Integer tentativasMinimas, Pageable pageable) {
        List<Notificacao> notificacoes = notificacaoRepository.findNotificacoesPorTentativasEnvio(tentativasMinimas);
        // Converter para Page manualmente
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), notificacoes.size());
        List<Notificacao> pageContent = notificacoes.subList(start, end);
        return new org.springframework.data.domain.PageImpl<>(pageContent, pageable, notificacoes.size())
                .map(patrimonioMapper::notificacaoToNotificacaoDTO);
    }

    // Statistics and Reports
    public Long contarNotificacoesPorUsuario(Long usuarioId) {
        return notificacaoRepository.countByUsuarioId(usuarioId);
    }

    public Long contarNotificacoesNaoLidasPorUsuario(Long usuarioId) {
        List<Notificacao> notificacoes = notificacaoRepository.findNotificacoesNaoLidasPorUsuario(usuarioId);
        return (long) notificacoes.size();
    }

    public Long contarNotificacoesPorTipo(String tipo) {
        List<Notificacao> notificacoes = notificacaoRepository.findByTipoOrderByDataEnvioDesc(tipo);
        return (long) notificacoes.size();
    }

    public Long contarNotificacoesPorStatus(Boolean lida) {
        List<Notificacao> notificacoes = notificacaoRepository.findByLidaOrderByDataEnvioDesc(lida);
        return (long) notificacoes.size();
    }

    public Long contarNotificacoesPorPrioridade(Integer prioridade) {
        List<Notificacao> notificacoes = notificacaoRepository.findByPrioridadeGreaterThanEqualOrderByPrioridadeDesc(prioridade);
        return (long) notificacoes.size();
    }

    public Long contarNotificacoesPorCategoria(String categoria) {
        List<Notificacao> notificacoes = notificacaoRepository.findByCategoriaContainingIgnoreCaseOrderByDataEnvioDesc(categoria);
        return (long) notificacoes.size();
    }

    public List<NotificacaoDTO> buscarNotificacoesExpiradas() {
        // Implementar lógica para notificações expiradas baseada em data de expiração
        return patrimonioMapper.notificacoesToNotificacaoDTOs(List.of());
    }

    public List<NotificacaoDTO> buscarNotificacoesUrgentes() {
        List<Notificacao> notificacoes = notificacaoRepository.findNotificacoesUrgentes();
        return patrimonioMapper.notificacoesToNotificacaoDTOs(notificacoes);
    }

    public List<NotificacaoDTO> buscarNotificacoesPendentesEnvio() {
        // Implementar lógica para notificações pendentes de envio
        return patrimonioMapper.notificacoesToNotificacaoDTOs(List.of());
    }

    // Bulk Operations
    @CacheEvict(value = "notificacoes", allEntries = true)
    public void marcarTodasComoLidas(Long usuarioId) {
        List<Notificacao> notificacoes = notificacaoRepository.findNotificacoesNaoLidasPorUsuario(usuarioId);
        for (Notificacao notificacao : notificacoes) {
            notificacao.setLida(true);
            notificacao.setDataLeitura(LocalDateTime.now());
        }
        notificacaoRepository.saveAll(notificacoes);
    }

    @CacheEvict(value = "notificacoes", allEntries = true)
    public void excluirNotificacoesAntigas(Long usuarioId, Integer dias) {
        LocalDateTime dataLimite = LocalDateTime.now().minusDays(dias);
        // Implementar lógica para excluir notificações antigas
        // Por enquanto, não faz nada
    }

    // Validation
    private void validarNotificacao(NotificacaoDTO notificacaoDTO) {
        if (notificacaoDTO.getTitulo() == null || notificacaoDTO.getTitulo().trim().length() < 3) {
            throw new RegraDeNegocioException("Título deve ter pelo menos 3 caracteres");
        }
        
        if (notificacaoDTO.getTitulo().trim().length() > 200) {
            throw new RegraDeNegocioException("Título deve ter no máximo 200 caracteres");
        }
        
        if (notificacaoDTO.getMensagem() == null || notificacaoDTO.getMensagem().trim().length() < 5) {
            throw new RegraDeNegocioException("Mensagem deve ter pelo menos 5 caracteres");
        }
        
        if (notificacaoDTO.getMensagem().trim().length() > 2000) {
            throw new RegraDeNegocioException("Mensagem deve ter no máximo 2000 caracteres");
        }
        
        if (notificacaoDTO.getTipo() == null || notificacaoDTO.getTipo().trim().isEmpty()) {
            throw new RegraDeNegocioException("Tipo é obrigatório");
        }
        
        if (notificacaoDTO.getPrioridade() != null && (notificacaoDTO.getPrioridade() < 1 || notificacaoDTO.getPrioridade() > 5)) {
            throw new RegraDeNegocioException("Prioridade deve estar entre 1 e 5");
        }
    }
}
