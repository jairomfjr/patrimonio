package com.manus.patrimonio.mapper;

import com.manus.patrimonio.dto.*;
import com.manus.patrimonio.model.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatrimonioMapper {

    // Mapeamentos para Bem
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "categoriaId", source = "categoria.id")
    @Mapping(target = "categoriaNome", source = "categoria.nome")
    @Mapping(target = "localizacaoAtualId", source = "localizacaoAtual.id")
    @Mapping(target = "localizacaoAtualNome", source = "localizacaoAtual.nome")
    @Mapping(target = "movimentacoes", ignore = true)
    @Mapping(target = "manutencoes", ignore = true)
    @Mapping(target = "baixas", ignore = true)
    BemDTO bemToBemDTO(Bem bem);

    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "localizacaoAtual", ignore = true)
    @Mapping(target = "movimentacoes", ignore = true)
    @Mapping(target = "inventarios", ignore = true)
    @Mapping(target = "manutencoes", ignore = true)
    @Mapping(target = "baixas", ignore = true)
    Bem bemDTOToBem(BemDTO bemDTO);

    List<BemDTO> bensToBemDTOs(List<Bem> bens);

    // Mapeamentos para Categoria
    CategoriaDTO categoriaToCategoriaDTO(Categoria categoria);
    Categoria categoriaDTOToCategoria(CategoriaDTO categoriaDTO);
    List<CategoriaDTO> categoriasToCategoriaDTOs(List<Categoria> categorias);

    // Mapeamentos para Localizacao
    LocalizacaoDTO localizacaoToLocalizacaoDTO(Localizacao localizacao);
    Localizacao localizacaoDTOToLocalizacao(LocalizacaoDTO localizacaoDTO);
    List<LocalizacaoDTO> localizacoesToLocalizacaoDTOs(List<Localizacao> localizacoes);

    // Mapeamentos para Movimentacao
    @Mapping(target = "bemId", source = "bem.id")
    @Mapping(target = "bemNome", source = "bem.descricao")
    @Mapping(target = "localizacaoOrigemId", source = "localizacaoOrigem.id")
    @Mapping(target = "localizacaoOrigemNome", source = "localizacaoOrigem.nome")
    @Mapping(target = "localizacaoDestinoId", source = "localizacaoDestino.id")
    @Mapping(target = "localizacaoDestinoNome", source = "localizacaoDestino.nome")
    MovimentacaoDTO movimentacaoToMovimentacaoDTO(Movimentacao movimentacao);

    @Mapping(target = "bem", ignore = true)
    @Mapping(target = "localizacaoOrigem", ignore = true)
    @Mapping(target = "localizacaoDestino", ignore = true)
    Movimentacao movimentacaoDTOToMovimentacao(MovimentacaoDTO movimentacaoDTO);

    List<MovimentacaoDTO> movimentacoesToMovimentacaoDTOs(List<Movimentacao> movimentacoes);

    // Mapeamentos para Inventario
    @Mapping(target = "bensInventariadosIds", ignore = true)
    InventarioDTO inventarioToInventarioDTO(Inventario inventario);

    @Mapping(target = "bensInventariados", ignore = true)
    Inventario inventarioDTOToInventario(InventarioDTO inventarioDTO);

    List<InventarioDTO> inventariosToInventarioDTOs(List<Inventario> inventarios);

    // Mapeamentos para Manutencao
    @Mapping(target = "bemId", source = "bem.id")
    @Mapping(target = "bemNome", source = "bem.descricao")
    ManutencaoDTO manutencaoToManutencaoDTO(Manutencao manutencao);

    @Mapping(target = "bem", ignore = true)
    Manutencao manutencaoDTOToManutencao(ManutencaoDTO manutencaoDTO);

    List<ManutencaoDTO> manutencoesToManutencaoDTOs(List<Manutencao> manutencoes);

    // Mapeamentos para Baixa
    @Mapping(target = "bemId", source = "bem.id")
    @Mapping(target = "bemNome", source = "bem.descricao")
    BaixaDTO baixaToBaixaDTO(Baixa baixa);

    @Mapping(target = "bem", ignore = true)
    Baixa baixaDTOToBaixa(BaixaDTO baixaDTO);

    List<BaixaDTO> baixasToBaixaDTOs(List<Baixa> baixas);

    // Mapeamentos para Usuario
    @Mapping(target = "perfilIds", ignore = true)
    @Mapping(target = "perfilNomes", ignore = true)
    @Mapping(target = "senha", ignore = true)
    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    @Mapping(target = "perfis", ignore = true)
    @Mapping(target = "senhaHash", ignore = true)
    Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> usuariosToUsuarioDTOs(List<Usuario> usuarios);

    // Mapeamentos para Perfil
    PerfilDTO perfilToPerfilDTO(Perfil perfil);
    Perfil perfilDTOToPerfil(PerfilDTO perfilDTO);
    List<PerfilDTO> perfisToPerfilDTOs(List<Perfil> perfis);

    // Mapeamentos para Notificacao
    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "usuarioNome", source = "usuario.nomeCompleto")
    NotificacaoDTO notificacaoToNotificacaoDTO(Notificacao notificacao);

    @Mapping(target = "usuario", ignore = true)
    Notificacao notificacaoDTOToNotificacao(NotificacaoDTO notificacaoDTO);

    List<NotificacaoDTO> notificacoesToNotificacaoDTOs(List<Notificacao> notificacoes);

    // Mapeamentos para Configuracao
    ConfiguracaoDTO configuracaoToConfiguracaoDTO(Configuracao configuracao);
    Configuracao configuracaoDTOToConfiguracao(ConfiguracaoDTO configuracaoDTO);
    List<ConfiguracaoDTO> configuracoesToConfiguracaoDTOs(List<Configuracao> configuracoes);

    // Mapeamentos para Auditoria
    @Mapping(target = "usuarioId", source = "usuario.id")
    AuditoriaDTO auditoriaToAuditoriaDTO(Auditoria auditoria);

    @Mapping(target = "usuario", ignore = true)
    Auditoria auditoriaDTOToAuditoria(AuditoriaDTO auditoriaDTO);

    List<AuditoriaDTO> auditoriasToAuditoriaDTOs(List<Auditoria> auditorias);
}
