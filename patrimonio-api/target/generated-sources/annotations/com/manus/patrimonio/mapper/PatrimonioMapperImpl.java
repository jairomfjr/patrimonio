package com.manus.patrimonio.mapper;

import com.manus.patrimonio.dto.AuditoriaDTO;
import com.manus.patrimonio.dto.BaixaDTO;
import com.manus.patrimonio.dto.BemDTO;
import com.manus.patrimonio.dto.CategoriaDTO;
import com.manus.patrimonio.dto.ConfiguracaoDTO;
import com.manus.patrimonio.dto.InventarioDTO;
import com.manus.patrimonio.dto.LocalizacaoDTO;
import com.manus.patrimonio.dto.ManutencaoDTO;
import com.manus.patrimonio.dto.MovimentacaoDTO;
import com.manus.patrimonio.dto.NotificacaoDTO;
import com.manus.patrimonio.dto.PerfilDTO;
import com.manus.patrimonio.dto.UsuarioDTO;
import com.manus.patrimonio.model.Auditoria;
import com.manus.patrimonio.model.Baixa;
import com.manus.patrimonio.model.Bem;
import com.manus.patrimonio.model.Categoria;
import com.manus.patrimonio.model.Configuracao;
import com.manus.patrimonio.model.Inventario;
import com.manus.patrimonio.model.Localizacao;
import com.manus.patrimonio.model.Manutencao;
import com.manus.patrimonio.model.Movimentacao;
import com.manus.patrimonio.model.Notificacao;
import com.manus.patrimonio.model.Perfil;
import com.manus.patrimonio.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-14T10:08:25-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Azul Systems, Inc.)"
)
@Component
public class PatrimonioMapperImpl implements PatrimonioMapper {

    @Override
    public BemDTO bemToBemDTO(Bem bem) {
        if ( bem == null ) {
            return null;
        }

        BemDTO bemDTO = new BemDTO();

        bemDTO.setNome( bem.getNome() );
        bemDTO.setCategoriaId( bemCategoriaId( bem ) );
        bemDTO.setCategoriaNome( bemCategoriaNome( bem ) );
        bemDTO.setLocalizacaoAtualId( bemLocalizacaoAtualId( bem ) );
        bemDTO.setLocalizacaoAtualNome( bemLocalizacaoAtualNome( bem ) );
        bemDTO.setId( bem.getId() );
        bemDTO.setDescricao( bem.getDescricao() );
        bemDTO.setNumeroSerie( bem.getNumeroSerie() );
        bemDTO.setDataAquisicao( bem.getDataAquisicao() );
        bemDTO.setValorAquisicao( bem.getValorAquisicao() );
        bemDTO.setValorAtual( bem.getValorAtual() );
        bemDTO.setStatus( bem.getStatus() );
        bemDTO.setCondicao( bem.getCondicao() );
        bemDTO.setObservacoes( bem.getObservacoes() );
        bemDTO.setFornecedor( bem.getFornecedor() );
        bemDTO.setGarantiaAte( bem.getGarantiaAte() );
        bemDTO.setDataUltimaManutencao( bem.getDataUltimaManutencao() );
        bemDTO.setDataProximaManutencao( bem.getDataProximaManutencao() );
        bemDTO.setFabricante( bem.getFabricante() );
        bemDTO.setModelo( bem.getModelo() );
        bemDTO.setAnoFabricacao( bem.getAnoFabricacao() );
        bemDTO.setCentroCusto( bem.getCentroCusto() );
        bemDTO.setVidaUtilAnos( bem.getVidaUtilAnos() );
        bemDTO.setTaxaDepreciacao( bem.getTaxaDepreciacao() );

        return bemDTO;
    }

    @Override
    public Bem bemDTOToBem(BemDTO bemDTO) {
        if ( bemDTO == null ) {
            return null;
        }

        Bem bem = new Bem();

        bem.setId( bemDTO.getId() );
        bem.setNome( bemDTO.getNome() );
        bem.setDescricao( bemDTO.getDescricao() );
        bem.setObservacoes( bemDTO.getObservacoes() );
        bem.setStatus( bemDTO.getStatus() );
        bem.setCondicao( bemDTO.getCondicao() );
        bem.setDataAquisicao( bemDTO.getDataAquisicao() );
        bem.setValorAquisicao( bemDTO.getValorAquisicao() );
        bem.setValorAtual( bemDTO.getValorAtual() );
        bem.setModelo( bemDTO.getModelo() );
        bem.setNumeroSerie( bemDTO.getNumeroSerie() );
        bem.setFornecedor( bemDTO.getFornecedor() );
        bem.setFabricante( bemDTO.getFabricante() );
        bem.setAnoFabricacao( bemDTO.getAnoFabricacao() );
        bem.setGarantiaAte( bemDTO.getGarantiaAte() );
        bem.setCentroCusto( bemDTO.getCentroCusto() );
        bem.setVidaUtilAnos( bemDTO.getVidaUtilAnos() );
        bem.setTaxaDepreciacao( bemDTO.getTaxaDepreciacao() );
        bem.setDataUltimaManutencao( bemDTO.getDataUltimaManutencao() );
        bem.setDataProximaManutencao( bemDTO.getDataProximaManutencao() );

        return bem;
    }

    @Override
    public List<BemDTO> bensToBemDTOs(List<Bem> bens) {
        if ( bens == null ) {
            return null;
        }

        List<BemDTO> list = new ArrayList<BemDTO>( bens.size() );
        for ( Bem bem : bens ) {
            list.add( bemToBemDTO( bem ) );
        }

        return list;
    }

    @Override
    public CategoriaDTO categoriaToCategoriaDTO(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setId( categoria.getId() );
        categoriaDTO.setNome( categoria.getNome() );
        categoriaDTO.setDescricao( categoria.getDescricao() );
        categoriaDTO.setDataCriacao( categoria.getDataCriacao() );
        categoriaDTO.setDataAtualizacao( categoria.getDataAtualizacao() );

        return categoriaDTO;
    }

    @Override
    public Categoria categoriaDTOToCategoria(CategoriaDTO categoriaDTO) {
        if ( categoriaDTO == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setId( categoriaDTO.getId() );
        categoria.setDataCriacao( categoriaDTO.getDataCriacao() );
        categoria.setDataAtualizacao( categoriaDTO.getDataAtualizacao() );
        categoria.setNome( categoriaDTO.getNome() );
        categoria.setDescricao( categoriaDTO.getDescricao() );

        return categoria;
    }

    @Override
    public List<CategoriaDTO> categoriasToCategoriaDTOs(List<Categoria> categorias) {
        if ( categorias == null ) {
            return null;
        }

        List<CategoriaDTO> list = new ArrayList<CategoriaDTO>( categorias.size() );
        for ( Categoria categoria : categorias ) {
            list.add( categoriaToCategoriaDTO( categoria ) );
        }

        return list;
    }

    @Override
    public LocalizacaoDTO localizacaoToLocalizacaoDTO(Localizacao localizacao) {
        if ( localizacao == null ) {
            return null;
        }

        LocalizacaoDTO localizacaoDTO = new LocalizacaoDTO();

        localizacaoDTO.setId( localizacao.getId() );
        localizacaoDTO.setNome( localizacao.getNome() );
        localizacaoDTO.setEndereco( localizacao.getEndereco() );
        localizacaoDTO.setResponsavel( localizacao.getResponsavel() );
        localizacaoDTO.setContato( localizacao.getContato() );
        localizacaoDTO.setDescricao( localizacao.getDescricao() );
        localizacaoDTO.setDataCriacao( localizacao.getDataCriacao() );
        localizacaoDTO.setDataAtualizacao( localizacao.getDataAtualizacao() );

        return localizacaoDTO;
    }

    @Override
    public Localizacao localizacaoDTOToLocalizacao(LocalizacaoDTO localizacaoDTO) {
        if ( localizacaoDTO == null ) {
            return null;
        }

        Localizacao localizacao = new Localizacao();

        localizacao.setId( localizacaoDTO.getId() );
        localizacao.setDataCriacao( localizacaoDTO.getDataCriacao() );
        localizacao.setDataAtualizacao( localizacaoDTO.getDataAtualizacao() );
        localizacao.setNome( localizacaoDTO.getNome() );
        localizacao.setEndereco( localizacaoDTO.getEndereco() );
        localizacao.setResponsavel( localizacaoDTO.getResponsavel() );
        localizacao.setContato( localizacaoDTO.getContato() );
        localizacao.setDescricao( localizacaoDTO.getDescricao() );

        return localizacao;
    }

    @Override
    public List<LocalizacaoDTO> localizacoesToLocalizacaoDTOs(List<Localizacao> localizacoes) {
        if ( localizacoes == null ) {
            return null;
        }

        List<LocalizacaoDTO> list = new ArrayList<LocalizacaoDTO>( localizacoes.size() );
        for ( Localizacao localizacao : localizacoes ) {
            list.add( localizacaoToLocalizacaoDTO( localizacao ) );
        }

        return list;
    }

    @Override
    public MovimentacaoDTO movimentacaoToMovimentacaoDTO(Movimentacao movimentacao) {
        if ( movimentacao == null ) {
            return null;
        }

        MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO();

        movimentacaoDTO.setBemId( movimentacaoBemId( movimentacao ) );
        movimentacaoDTO.setBemNome( movimentacaoBemDescricao( movimentacao ) );
        movimentacaoDTO.setLocalizacaoOrigemId( movimentacaoLocalizacaoOrigemId( movimentacao ) );
        movimentacaoDTO.setLocalizacaoOrigemNome( movimentacaoLocalizacaoOrigemNome( movimentacao ) );
        movimentacaoDTO.setLocalizacaoDestinoId( movimentacaoLocalizacaoDestinoId( movimentacao ) );
        movimentacaoDTO.setLocalizacaoDestinoNome( movimentacaoLocalizacaoDestinoNome( movimentacao ) );
        movimentacaoDTO.setId( movimentacao.getId() );
        movimentacaoDTO.setDataMovimentacao( movimentacao.getDataMovimentacao() );
        movimentacaoDTO.setTipoMovimentacao( movimentacao.getTipoMovimentacao() );
        movimentacaoDTO.setResponsavelMovimentacao( movimentacao.getResponsavelMovimentacao() );
        movimentacaoDTO.setObservacoes( movimentacao.getObservacoes() );
        movimentacaoDTO.setDataCriacao( movimentacao.getDataCriacao() );
        movimentacaoDTO.setDataAtualizacao( movimentacao.getDataAtualizacao() );

        return movimentacaoDTO;
    }

    @Override
    public Movimentacao movimentacaoDTOToMovimentacao(MovimentacaoDTO movimentacaoDTO) {
        if ( movimentacaoDTO == null ) {
            return null;
        }

        Movimentacao movimentacao = new Movimentacao();

        movimentacao.setId( movimentacaoDTO.getId() );
        movimentacao.setDataCriacao( movimentacaoDTO.getDataCriacao() );
        movimentacao.setDataAtualizacao( movimentacaoDTO.getDataAtualizacao() );
        movimentacao.setDataMovimentacao( movimentacaoDTO.getDataMovimentacao() );
        movimentacao.setTipoMovimentacao( movimentacaoDTO.getTipoMovimentacao() );
        movimentacao.setResponsavelMovimentacao( movimentacaoDTO.getResponsavelMovimentacao() );
        movimentacao.setObservacoes( movimentacaoDTO.getObservacoes() );

        return movimentacao;
    }

    @Override
    public List<MovimentacaoDTO> movimentacoesToMovimentacaoDTOs(List<Movimentacao> movimentacoes) {
        if ( movimentacoes == null ) {
            return null;
        }

        List<MovimentacaoDTO> list = new ArrayList<MovimentacaoDTO>( movimentacoes.size() );
        for ( Movimentacao movimentacao : movimentacoes ) {
            list.add( movimentacaoToMovimentacaoDTO( movimentacao ) );
        }

        return list;
    }

    @Override
    public InventarioDTO inventarioToInventarioDTO(Inventario inventario) {
        if ( inventario == null ) {
            return null;
        }

        InventarioDTO inventarioDTO = new InventarioDTO();

        inventarioDTO.setId( inventario.getId() );
        inventarioDTO.setNome( inventario.getNome() );
        inventarioDTO.setDataInicio( inventario.getDataInicio() );
        inventarioDTO.setDataFim( inventario.getDataFim() );
        inventarioDTO.setResponsavel( inventario.getResponsavel() );
        inventarioDTO.setStatus( inventario.getStatus() );
        inventarioDTO.setObservacoes( inventario.getObservacoes() );
        inventarioDTO.setDataCriacao( inventario.getDataCriacao() );
        inventarioDTO.setDataAtualizacao( inventario.getDataAtualizacao() );

        return inventarioDTO;
    }

    @Override
    public Inventario inventarioDTOToInventario(InventarioDTO inventarioDTO) {
        if ( inventarioDTO == null ) {
            return null;
        }

        Inventario inventario = new Inventario();

        inventario.setId( inventarioDTO.getId() );
        inventario.setDataCriacao( inventarioDTO.getDataCriacao() );
        inventario.setDataAtualizacao( inventarioDTO.getDataAtualizacao() );
        inventario.setNome( inventarioDTO.getNome() );
        inventario.setDataInicio( inventarioDTO.getDataInicio() );
        inventario.setDataFim( inventarioDTO.getDataFim() );
        inventario.setResponsavel( inventarioDTO.getResponsavel() );
        inventario.setStatus( inventarioDTO.getStatus() );
        inventario.setObservacoes( inventarioDTO.getObservacoes() );

        return inventario;
    }

    @Override
    public List<InventarioDTO> inventariosToInventarioDTOs(List<Inventario> inventarios) {
        if ( inventarios == null ) {
            return null;
        }

        List<InventarioDTO> list = new ArrayList<InventarioDTO>( inventarios.size() );
        for ( Inventario inventario : inventarios ) {
            list.add( inventarioToInventarioDTO( inventario ) );
        }

        return list;
    }

    @Override
    public ManutencaoDTO manutencaoToManutencaoDTO(Manutencao manutencao) {
        if ( manutencao == null ) {
            return null;
        }

        ManutencaoDTO manutencaoDTO = new ManutencaoDTO();

        manutencaoDTO.setBemId( manutencaoBemId( manutencao ) );
        manutencaoDTO.setBemNome( manutencaoBemDescricao( manutencao ) );
        manutencaoDTO.setId( manutencao.getId() );
        manutencaoDTO.setTipoManutencao( manutencao.getTipoManutencao() );
        manutencaoDTO.setDescricao( manutencao.getDescricao() );
        manutencaoDTO.setDataInicio( manutencao.getDataInicio() );
        manutencaoDTO.setDataFim( manutencao.getDataFim() );
        manutencaoDTO.setCusto( manutencao.getCusto() );
        manutencaoDTO.setFornecedor( manutencao.getFornecedor() );
        manutencaoDTO.setResponsavel( manutencao.getResponsavel() );
        manutencaoDTO.setStatus( manutencao.getStatus() );
        manutencaoDTO.setObservacoes( manutencao.getObservacoes() );
        manutencaoDTO.setDataAgendamento( manutencao.getDataAgendamento() );
        manutencaoDTO.setPrioridade( manutencao.getPrioridade() );
        manutencaoDTO.setTempoEstimadoHoras( manutencao.getTempoEstimadoHoras() );
        manutencaoDTO.setCausaRaiz( manutencao.getCausaRaiz() );
        manutencaoDTO.setSolucaoAplicada( manutencao.getSolucaoAplicada() );
        manutencaoDTO.setRecomendacoes( manutencao.getRecomendacoes() );

        return manutencaoDTO;
    }

    @Override
    public Manutencao manutencaoDTOToManutencao(ManutencaoDTO manutencaoDTO) {
        if ( manutencaoDTO == null ) {
            return null;
        }

        Manutencao manutencao = new Manutencao();

        manutencao.setId( manutencaoDTO.getId() );
        manutencao.setTipoManutencao( manutencaoDTO.getTipoManutencao() );
        manutencao.setDescricao( manutencaoDTO.getDescricao() );
        manutencao.setDataInicio( manutencaoDTO.getDataInicio() );
        manutencao.setDataFim( manutencaoDTO.getDataFim() );
        manutencao.setCusto( manutencaoDTO.getCusto() );
        manutencao.setFornecedor( manutencaoDTO.getFornecedor() );
        manutencao.setResponsavel( manutencaoDTO.getResponsavel() );
        manutencao.setStatus( manutencaoDTO.getStatus() );
        manutencao.setObservacoes( manutencaoDTO.getObservacoes() );
        manutencao.setDataAgendamento( manutencaoDTO.getDataAgendamento() );
        manutencao.setPrioridade( manutencaoDTO.getPrioridade() );
        manutencao.setTempoEstimadoHoras( manutencaoDTO.getTempoEstimadoHoras() );
        manutencao.setCausaRaiz( manutencaoDTO.getCausaRaiz() );
        manutencao.setSolucaoAplicada( manutencaoDTO.getSolucaoAplicada() );
        manutencao.setRecomendacoes( manutencaoDTO.getRecomendacoes() );

        return manutencao;
    }

    @Override
    public List<ManutencaoDTO> manutencoesToManutencaoDTOs(List<Manutencao> manutencoes) {
        if ( manutencoes == null ) {
            return null;
        }

        List<ManutencaoDTO> list = new ArrayList<ManutencaoDTO>( manutencoes.size() );
        for ( Manutencao manutencao : manutencoes ) {
            list.add( manutencaoToManutencaoDTO( manutencao ) );
        }

        return list;
    }

    @Override
    public BaixaDTO baixaToBaixaDTO(Baixa baixa) {
        if ( baixa == null ) {
            return null;
        }

        BaixaDTO baixaDTO = new BaixaDTO();

        baixaDTO.setBemId( baixaBemId( baixa ) );
        baixaDTO.setBemNome( baixaBemDescricao( baixa ) );
        baixaDTO.setId( baixa.getId() );
        baixaDTO.setMotivo( baixa.getMotivo() );
        baixaDTO.setDescricao( baixa.getDescricao() );
        baixaDTO.setDataBaixa( baixa.getDataBaixa() );
        baixaDTO.setValorResidual( baixa.getValorResidual() );
        baixaDTO.setProcessoAdministrativo( baixa.getProcessoAdministrativo() );
        baixaDTO.setResponsavel( baixa.getResponsavel() );
        baixaDTO.setObservacoes( baixa.getObservacoes() );
        baixaDTO.setDataAprovacao( baixa.getDataAprovacao() );
        baixaDTO.setAprovadoPor( baixa.getAprovadoPor() );
        baixaDTO.setJustificativaTecnica( baixa.getJustificativaTecnica() );
        baixaDTO.setDocumentacaoAnexada( baixa.getDocumentacaoAnexada() );
        baixaDTO.setDestinoFinal( baixa.getDestinoFinal() );
        baixaDTO.setValorVenda( baixa.getValorVenda() );
        baixaDTO.setDataVenda( baixa.getDataVenda() );
        baixaDTO.setComprador( baixa.getComprador() );
        baixaDTO.setPerdaFinanceira( baixa.getPerdaFinanceira() );
        baixaDTO.setGanhoFinanceiro( baixa.getGanhoFinanceiro() );

        return baixaDTO;
    }

    @Override
    public Baixa baixaDTOToBaixa(BaixaDTO baixaDTO) {
        if ( baixaDTO == null ) {
            return null;
        }

        Baixa baixa = new Baixa();

        baixa.setId( baixaDTO.getId() );
        baixa.setMotivo( baixaDTO.getMotivo() );
        baixa.setDescricao( baixaDTO.getDescricao() );
        baixa.setDataBaixa( baixaDTO.getDataBaixa() );
        baixa.setValorResidual( baixaDTO.getValorResidual() );
        baixa.setProcessoAdministrativo( baixaDTO.getProcessoAdministrativo() );
        baixa.setResponsavel( baixaDTO.getResponsavel() );
        baixa.setObservacoes( baixaDTO.getObservacoes() );
        baixa.setDataAprovacao( baixaDTO.getDataAprovacao() );
        baixa.setAprovadoPor( baixaDTO.getAprovadoPor() );
        baixa.setJustificativaTecnica( baixaDTO.getJustificativaTecnica() );
        baixa.setDocumentacaoAnexada( baixaDTO.getDocumentacaoAnexada() );
        baixa.setDestinoFinal( baixaDTO.getDestinoFinal() );
        baixa.setValorVenda( baixaDTO.getValorVenda() );
        baixa.setDataVenda( baixaDTO.getDataVenda() );
        baixa.setComprador( baixaDTO.getComprador() );

        return baixa;
    }

    @Override
    public List<BaixaDTO> baixasToBaixaDTOs(List<Baixa> baixas) {
        if ( baixas == null ) {
            return null;
        }

        List<BaixaDTO> list = new ArrayList<BaixaDTO>( baixas.size() );
        for ( Baixa baixa : baixas ) {
            list.add( baixaToBaixaDTO( baixa ) );
        }

        return list;
    }

    @Override
    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( usuario.getId() );
        usuarioDTO.setUsername( usuario.getUsername() );
        usuarioDTO.setEmail( usuario.getEmail() );
        usuarioDTO.setNomeCompleto( usuario.getNomeCompleto() );
        usuarioDTO.setAtivo( usuario.getAtivo() );
        usuarioDTO.setDataUltimoLogin( usuario.getDataUltimoLogin() );
        usuarioDTO.setDataExpiracaoSenha( usuario.getDataExpiracaoSenha() );
        usuarioDTO.setTentativasLoginFalha( usuario.getTentativasLoginFalha() );
        usuarioDTO.setDataBloqueio( usuario.getDataBloqueio() );
        usuarioDTO.setTelefone( usuario.getTelefone() );
        usuarioDTO.setDepartamento( usuario.getDepartamento() );
        usuarioDTO.setCargo( usuario.getCargo() );
        usuarioDTO.setMatricula( usuario.getMatricula() );
        usuarioDTO.setCpf( usuario.getCpf() );
        usuarioDTO.setDataNascimento( usuario.getDataNascimento() );
        usuarioDTO.setEndereco( usuario.getEndereco() );
        usuarioDTO.setCidade( usuario.getCidade() );
        usuarioDTO.setEstado( usuario.getEstado() );
        usuarioDTO.setCep( usuario.getCep() );
        usuarioDTO.setPais( usuario.getPais() );
        usuarioDTO.setBloqueado( usuario.isBloqueado() );
        usuarioDTO.setSenhaExpirada( usuario.isSenhaExpirada() );

        return usuarioDTO;
    }

    @Override
    public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( usuarioDTO.getId() );
        usuario.setUsername( usuarioDTO.getUsername() );
        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setNomeCompleto( usuarioDTO.getNomeCompleto() );
        usuario.setAtivo( usuarioDTO.getAtivo() );
        usuario.setDataUltimoLogin( usuarioDTO.getDataUltimoLogin() );
        usuario.setDataExpiracaoSenha( usuarioDTO.getDataExpiracaoSenha() );
        usuario.setTentativasLoginFalha( usuarioDTO.getTentativasLoginFalha() );
        usuario.setDataBloqueio( usuarioDTO.getDataBloqueio() );
        usuario.setTelefone( usuarioDTO.getTelefone() );
        usuario.setDepartamento( usuarioDTO.getDepartamento() );
        usuario.setCargo( usuarioDTO.getCargo() );
        usuario.setMatricula( usuarioDTO.getMatricula() );
        usuario.setCpf( usuarioDTO.getCpf() );
        usuario.setDataNascimento( usuarioDTO.getDataNascimento() );
        usuario.setEndereco( usuarioDTO.getEndereco() );
        usuario.setEstado( usuarioDTO.getEstado() );
        usuario.setCep( usuarioDTO.getCep() );
        usuario.setPais( usuarioDTO.getPais() );

        return usuario;
    }

    @Override
    public List<UsuarioDTO> usuariosToUsuarioDTOs(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<UsuarioDTO> list = new ArrayList<UsuarioDTO>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( usuarioToUsuarioDTO( usuario ) );
        }

        return list;
    }

    @Override
    public PerfilDTO perfilToPerfilDTO(Perfil perfil) {
        if ( perfil == null ) {
            return null;
        }

        PerfilDTO perfilDTO = new PerfilDTO();

        perfilDTO.setId( perfil.getId() );
        perfilDTO.setNome( perfil.getNome() );
        perfilDTO.setDescricao( perfil.getDescricao() );
        perfilDTO.setAtivo( perfil.getAtivo() );
        perfilDTO.setNivelAcesso( perfil.getNivelAcesso() );
        List<String> list = perfil.getPermissoes();
        if ( list != null ) {
            perfilDTO.setPermissoes( new ArrayList<String>( list ) );
        }

        return perfilDTO;
    }

    @Override
    public Perfil perfilDTOToPerfil(PerfilDTO perfilDTO) {
        if ( perfilDTO == null ) {
            return null;
        }

        Perfil perfil = new Perfil();

        perfil.setId( perfilDTO.getId() );
        perfil.setNome( perfilDTO.getNome() );
        perfil.setDescricao( perfilDTO.getDescricao() );
        perfil.setAtivo( perfilDTO.getAtivo() );
        perfil.setNivelAcesso( perfilDTO.getNivelAcesso() );
        List<String> list = perfilDTO.getPermissoes();
        if ( list != null ) {
            perfil.setPermissoes( new ArrayList<String>( list ) );
        }

        return perfil;
    }

    @Override
    public List<PerfilDTO> perfisToPerfilDTOs(List<Perfil> perfis) {
        if ( perfis == null ) {
            return null;
        }

        List<PerfilDTO> list = new ArrayList<PerfilDTO>( perfis.size() );
        for ( Perfil perfil : perfis ) {
            list.add( perfilToPerfilDTO( perfil ) );
        }

        return list;
    }

    @Override
    public NotificacaoDTO notificacaoToNotificacaoDTO(Notificacao notificacao) {
        if ( notificacao == null ) {
            return null;
        }

        NotificacaoDTO notificacaoDTO = new NotificacaoDTO();

        notificacaoDTO.setUsuarioId( notificacaoUsuarioId( notificacao ) );
        notificacaoDTO.setUsuarioNome( notificacaoUsuarioNomeCompleto( notificacao ) );
        notificacaoDTO.setId( notificacao.getId() );
        notificacaoDTO.setTitulo( notificacao.getTitulo() );
        notificacaoDTO.setMensagem( notificacao.getMensagem() );
        notificacaoDTO.setTipo( notificacao.getTipo() );
        notificacaoDTO.setLida( notificacao.getLida() );
        notificacaoDTO.setDataEnvio( notificacao.getDataEnvio() );
        notificacaoDTO.setDataLeitura( notificacao.getDataLeitura() );
        notificacaoDTO.setPrioridade( notificacao.getPrioridade() );
        notificacaoDTO.setCategoria( notificacao.getCategoria() );
        notificacaoDTO.setEntidadeRelacionada( notificacao.getEntidadeRelacionada() );
        notificacaoDTO.setIdEntidadeRelacionada( notificacao.getIdEntidadeRelacionada() );
        notificacaoDTO.setAcaoRequerida( notificacao.getAcaoRequerida() );
        notificacaoDTO.setUrlAcao( notificacao.getUrlAcao() );
        notificacaoDTO.setEnviadaPorEmail( notificacao.getEnviadaPorEmail() );
        notificacaoDTO.setEnviadaPorPush( notificacao.getEnviadaPorPush() );
        notificacaoDTO.setEnviadaPorSms( notificacao.getEnviadaPorSms() );
        notificacaoDTO.setTentativasEnvio( notificacao.getTentativasEnvio() );
        notificacaoDTO.setDataUltimaTentativa( notificacao.getDataUltimaTentativa() );
        notificacaoDTO.setErroEnvio( notificacao.getErroEnvio() );

        return notificacaoDTO;
    }

    @Override
    public Notificacao notificacaoDTOToNotificacao(NotificacaoDTO notificacaoDTO) {
        if ( notificacaoDTO == null ) {
            return null;
        }

        Notificacao notificacao = new Notificacao();

        notificacao.setId( notificacaoDTO.getId() );
        notificacao.setTitulo( notificacaoDTO.getTitulo() );
        notificacao.setMensagem( notificacaoDTO.getMensagem() );
        notificacao.setTipo( notificacaoDTO.getTipo() );
        notificacao.setLida( notificacaoDTO.getLida() );
        notificacao.setDataEnvio( notificacaoDTO.getDataEnvio() );
        notificacao.setDataLeitura( notificacaoDTO.getDataLeitura() );
        notificacao.setPrioridade( notificacaoDTO.getPrioridade() );
        notificacao.setCategoria( notificacaoDTO.getCategoria() );
        notificacao.setEntidadeRelacionada( notificacaoDTO.getEntidadeRelacionada() );
        notificacao.setIdEntidadeRelacionada( notificacaoDTO.getIdEntidadeRelacionada() );
        notificacao.setAcaoRequerida( notificacaoDTO.getAcaoRequerida() );
        notificacao.setUrlAcao( notificacaoDTO.getUrlAcao() );
        notificacao.setEnviadaPorEmail( notificacaoDTO.getEnviadaPorEmail() );
        notificacao.setEnviadaPorPush( notificacaoDTO.getEnviadaPorPush() );
        notificacao.setEnviadaPorSms( notificacaoDTO.getEnviadaPorSms() );
        notificacao.setTentativasEnvio( notificacaoDTO.getTentativasEnvio() );
        notificacao.setDataUltimaTentativa( notificacaoDTO.getDataUltimaTentativa() );
        notificacao.setErroEnvio( notificacaoDTO.getErroEnvio() );

        return notificacao;
    }

    @Override
    public List<NotificacaoDTO> notificacoesToNotificacaoDTOs(List<Notificacao> notificacoes) {
        if ( notificacoes == null ) {
            return null;
        }

        List<NotificacaoDTO> list = new ArrayList<NotificacaoDTO>( notificacoes.size() );
        for ( Notificacao notificacao : notificacoes ) {
            list.add( notificacaoToNotificacaoDTO( notificacao ) );
        }

        return list;
    }

    @Override
    public ConfiguracaoDTO configuracaoToConfiguracaoDTO(Configuracao configuracao) {
        if ( configuracao == null ) {
            return null;
        }

        ConfiguracaoDTO configuracaoDTO = new ConfiguracaoDTO();

        configuracaoDTO.setId( configuracao.getId() );
        configuracaoDTO.setChave( configuracao.getChave() );
        configuracaoDTO.setValor( configuracao.getValor() );
        configuracaoDTO.setDescricao( configuracao.getDescricao() );
        configuracaoDTO.setTipo( configuracao.getTipo() );
        configuracaoDTO.setEditavel( configuracao.getEditavel() );
        configuracaoDTO.setDataCriacao( configuracao.getDataCriacao() );
        configuracaoDTO.setDataAtualizacao( configuracao.getDataAtualizacao() );
        configuracaoDTO.setVersao( configuracao.getVersao() );

        return configuracaoDTO;
    }

    @Override
    public Configuracao configuracaoDTOToConfiguracao(ConfiguracaoDTO configuracaoDTO) {
        if ( configuracaoDTO == null ) {
            return null;
        }

        Configuracao configuracao = new Configuracao();

        configuracao.setId( configuracaoDTO.getId() );
        configuracao.setDataCriacao( configuracaoDTO.getDataCriacao() );
        configuracao.setDataAtualizacao( configuracaoDTO.getDataAtualizacao() );
        configuracao.setVersao( configuracaoDTO.getVersao() );
        configuracao.setChave( configuracaoDTO.getChave() );
        configuracao.setValor( configuracaoDTO.getValor() );
        configuracao.setDescricao( configuracaoDTO.getDescricao() );
        configuracao.setTipo( configuracaoDTO.getTipo() );
        configuracao.setEditavel( configuracaoDTO.getEditavel() );

        return configuracao;
    }

    @Override
    public List<ConfiguracaoDTO> configuracoesToConfiguracaoDTOs(List<Configuracao> configuracoes) {
        if ( configuracoes == null ) {
            return null;
        }

        List<ConfiguracaoDTO> list = new ArrayList<ConfiguracaoDTO>( configuracoes.size() );
        for ( Configuracao configuracao : configuracoes ) {
            list.add( configuracaoToConfiguracaoDTO( configuracao ) );
        }

        return list;
    }

    @Override
    public AuditoriaDTO auditoriaToAuditoriaDTO(Auditoria auditoria) {
        if ( auditoria == null ) {
            return null;
        }

        AuditoriaDTO auditoriaDTO = new AuditoriaDTO();

        auditoriaDTO.setUsuarioId( auditoriaUsuarioId( auditoria ) );
        auditoriaDTO.setId( auditoria.getId() );
        auditoriaDTO.setEntidade( auditoria.getEntidade() );
        auditoriaDTO.setEntidadeId( auditoria.getEntidadeId() );
        auditoriaDTO.setAcao( auditoria.getAcao() );
        auditoriaDTO.setDadosAnteriores( auditoria.getDadosAnteriores() );
        auditoriaDTO.setDadosNovos( auditoria.getDadosNovos() );
        auditoriaDTO.setIpAddress( auditoria.getIpAddress() );
        auditoriaDTO.setUserAgent( auditoria.getUserAgent() );
        auditoriaDTO.setDataAcao( auditoria.getDataAcao() );

        return auditoriaDTO;
    }

    @Override
    public Auditoria auditoriaDTOToAuditoria(AuditoriaDTO auditoriaDTO) {
        if ( auditoriaDTO == null ) {
            return null;
        }

        Auditoria auditoria = new Auditoria();

        auditoria.setId( auditoriaDTO.getId() );
        auditoria.setEntidade( auditoriaDTO.getEntidade() );
        auditoria.setEntidadeId( auditoriaDTO.getEntidadeId() );
        auditoria.setAcao( auditoriaDTO.getAcao() );
        auditoria.setDadosAnteriores( auditoriaDTO.getDadosAnteriores() );
        auditoria.setDadosNovos( auditoriaDTO.getDadosNovos() );
        auditoria.setIpAddress( auditoriaDTO.getIpAddress() );
        auditoria.setUserAgent( auditoriaDTO.getUserAgent() );
        auditoria.setDataAcao( auditoriaDTO.getDataAcao() );

        return auditoria;
    }

    @Override
    public List<AuditoriaDTO> auditoriasToAuditoriaDTOs(List<Auditoria> auditorias) {
        if ( auditorias == null ) {
            return null;
        }

        List<AuditoriaDTO> list = new ArrayList<AuditoriaDTO>( auditorias.size() );
        for ( Auditoria auditoria : auditorias ) {
            list.add( auditoriaToAuditoriaDTO( auditoria ) );
        }

        return list;
    }

    private Long bemCategoriaId(Bem bem) {
        if ( bem == null ) {
            return null;
        }
        Categoria categoria = bem.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        Long id = categoria.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String bemCategoriaNome(Bem bem) {
        if ( bem == null ) {
            return null;
        }
        Categoria categoria = bem.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        String nome = categoria.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }

    private Long bemLocalizacaoAtualId(Bem bem) {
        if ( bem == null ) {
            return null;
        }
        Localizacao localizacaoAtual = bem.getLocalizacaoAtual();
        if ( localizacaoAtual == null ) {
            return null;
        }
        Long id = localizacaoAtual.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String bemLocalizacaoAtualNome(Bem bem) {
        if ( bem == null ) {
            return null;
        }
        Localizacao localizacaoAtual = bem.getLocalizacaoAtual();
        if ( localizacaoAtual == null ) {
            return null;
        }
        String nome = localizacaoAtual.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }

    private Long movimentacaoBemId(Movimentacao movimentacao) {
        if ( movimentacao == null ) {
            return null;
        }
        Bem bem = movimentacao.getBem();
        if ( bem == null ) {
            return null;
        }
        Long id = bem.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String movimentacaoBemDescricao(Movimentacao movimentacao) {
        if ( movimentacao == null ) {
            return null;
        }
        Bem bem = movimentacao.getBem();
        if ( bem == null ) {
            return null;
        }
        String descricao = bem.getDescricao();
        if ( descricao == null ) {
            return null;
        }
        return descricao;
    }

    private Long movimentacaoLocalizacaoOrigemId(Movimentacao movimentacao) {
        if ( movimentacao == null ) {
            return null;
        }
        Localizacao localizacaoOrigem = movimentacao.getLocalizacaoOrigem();
        if ( localizacaoOrigem == null ) {
            return null;
        }
        Long id = localizacaoOrigem.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String movimentacaoLocalizacaoOrigemNome(Movimentacao movimentacao) {
        if ( movimentacao == null ) {
            return null;
        }
        Localizacao localizacaoOrigem = movimentacao.getLocalizacaoOrigem();
        if ( localizacaoOrigem == null ) {
            return null;
        }
        String nome = localizacaoOrigem.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }

    private Long movimentacaoLocalizacaoDestinoId(Movimentacao movimentacao) {
        if ( movimentacao == null ) {
            return null;
        }
        Localizacao localizacaoDestino = movimentacao.getLocalizacaoDestino();
        if ( localizacaoDestino == null ) {
            return null;
        }
        Long id = localizacaoDestino.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String movimentacaoLocalizacaoDestinoNome(Movimentacao movimentacao) {
        if ( movimentacao == null ) {
            return null;
        }
        Localizacao localizacaoDestino = movimentacao.getLocalizacaoDestino();
        if ( localizacaoDestino == null ) {
            return null;
        }
        String nome = localizacaoDestino.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }

    private Long manutencaoBemId(Manutencao manutencao) {
        if ( manutencao == null ) {
            return null;
        }
        Bem bem = manutencao.getBem();
        if ( bem == null ) {
            return null;
        }
        Long id = bem.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String manutencaoBemDescricao(Manutencao manutencao) {
        if ( manutencao == null ) {
            return null;
        }
        Bem bem = manutencao.getBem();
        if ( bem == null ) {
            return null;
        }
        String descricao = bem.getDescricao();
        if ( descricao == null ) {
            return null;
        }
        return descricao;
    }

    private Long baixaBemId(Baixa baixa) {
        if ( baixa == null ) {
            return null;
        }
        Bem bem = baixa.getBem();
        if ( bem == null ) {
            return null;
        }
        Long id = bem.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String baixaBemDescricao(Baixa baixa) {
        if ( baixa == null ) {
            return null;
        }
        Bem bem = baixa.getBem();
        if ( bem == null ) {
            return null;
        }
        String descricao = bem.getDescricao();
        if ( descricao == null ) {
            return null;
        }
        return descricao;
    }

    private Long notificacaoUsuarioId(Notificacao notificacao) {
        if ( notificacao == null ) {
            return null;
        }
        Usuario usuario = notificacao.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Long id = usuario.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String notificacaoUsuarioNomeCompleto(Notificacao notificacao) {
        if ( notificacao == null ) {
            return null;
        }
        Usuario usuario = notificacao.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        String nomeCompleto = usuario.getNomeCompleto();
        if ( nomeCompleto == null ) {
            return null;
        }
        return nomeCompleto;
    }

    private Long auditoriaUsuarioId(Auditoria auditoria) {
        if ( auditoria == null ) {
            return null;
        }
        Usuario usuario = auditoria.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Long id = usuario.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
