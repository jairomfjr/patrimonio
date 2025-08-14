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
    date = "2025-08-14T14:51:34-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
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
        bemDTO.setAnoFabricacao( bem.getAnoFabricacao() );
        bemDTO.setCentroCusto( bem.getCentroCusto() );
        bemDTO.setCondicao( bem.getCondicao() );
        bemDTO.setDataAquisicao( bem.getDataAquisicao() );
        bemDTO.setDataProximaManutencao( bem.getDataProximaManutencao() );
        bemDTO.setDataUltimaManutencao( bem.getDataUltimaManutencao() );
        bemDTO.setDescricao( bem.getDescricao() );
        bemDTO.setFabricante( bem.getFabricante() );
        bemDTO.setFornecedor( bem.getFornecedor() );
        bemDTO.setGarantiaAte( bem.getGarantiaAte() );
        bemDTO.setId( bem.getId() );
        bemDTO.setModelo( bem.getModelo() );
        bemDTO.setNumeroSerie( bem.getNumeroSerie() );
        bemDTO.setObservacoes( bem.getObservacoes() );
        bemDTO.setStatus( bem.getStatus() );
        bemDTO.setTaxaDepreciacao( bem.getTaxaDepreciacao() );
        bemDTO.setValorAquisicao( bem.getValorAquisicao() );
        bemDTO.setValorAtual( bem.getValorAtual() );
        bemDTO.setVidaUtilAnos( bem.getVidaUtilAnos() );

        return bemDTO;
    }

    @Override
    public Bem bemDTOToBem(BemDTO bemDTO) {
        if ( bemDTO == null ) {
            return null;
        }

        Bem bem = new Bem();

        bem.setId( bemDTO.getId() );
        bem.setAnoFabricacao( bemDTO.getAnoFabricacao() );
        bem.setCentroCusto( bemDTO.getCentroCusto() );
        bem.setCondicao( bemDTO.getCondicao() );
        bem.setDataAquisicao( bemDTO.getDataAquisicao() );
        bem.setDataProximaManutencao( bemDTO.getDataProximaManutencao() );
        bem.setDataUltimaManutencao( bemDTO.getDataUltimaManutencao() );
        bem.setDescricao( bemDTO.getDescricao() );
        bem.setFabricante( bemDTO.getFabricante() );
        bem.setFornecedor( bemDTO.getFornecedor() );
        bem.setGarantiaAte( bemDTO.getGarantiaAte() );
        bem.setModelo( bemDTO.getModelo() );
        bem.setNome( bemDTO.getNome() );
        bem.setNumeroSerie( bemDTO.getNumeroSerie() );
        bem.setObservacoes( bemDTO.getObservacoes() );
        bem.setStatus( bemDTO.getStatus() );
        bem.setTaxaDepreciacao( bemDTO.getTaxaDepreciacao() );
        bem.setValorAquisicao( bemDTO.getValorAquisicao() );
        bem.setValorAtual( bemDTO.getValorAtual() );
        bem.setVidaUtilAnos( bemDTO.getVidaUtilAnos() );

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

        categoriaDTO.setDataAtualizacao( categoria.getDataAtualizacao() );
        categoriaDTO.setDataCriacao( categoria.getDataCriacao() );
        categoriaDTO.setDescricao( categoria.getDescricao() );
        categoriaDTO.setId( categoria.getId() );
        categoriaDTO.setNome( categoria.getNome() );

        return categoriaDTO;
    }

    @Override
    public Categoria categoriaDTOToCategoria(CategoriaDTO categoriaDTO) {
        if ( categoriaDTO == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setDataAtualizacao( categoriaDTO.getDataAtualizacao() );
        categoria.setDataCriacao( categoriaDTO.getDataCriacao() );
        categoria.setId( categoriaDTO.getId() );
        categoria.setDescricao( categoriaDTO.getDescricao() );
        categoria.setNome( categoriaDTO.getNome() );

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

        localizacaoDTO.setContato( localizacao.getContato() );
        localizacaoDTO.setDataAtualizacao( localizacao.getDataAtualizacao() );
        localizacaoDTO.setDataCriacao( localizacao.getDataCriacao() );
        localizacaoDTO.setDescricao( localizacao.getDescricao() );
        localizacaoDTO.setEndereco( localizacao.getEndereco() );
        localizacaoDTO.setId( localizacao.getId() );
        localizacaoDTO.setNome( localizacao.getNome() );
        localizacaoDTO.setResponsavel( localizacao.getResponsavel() );

        return localizacaoDTO;
    }

    @Override
    public Localizacao localizacaoDTOToLocalizacao(LocalizacaoDTO localizacaoDTO) {
        if ( localizacaoDTO == null ) {
            return null;
        }

        Localizacao localizacao = new Localizacao();

        localizacao.setDataAtualizacao( localizacaoDTO.getDataAtualizacao() );
        localizacao.setDataCriacao( localizacaoDTO.getDataCriacao() );
        localizacao.setId( localizacaoDTO.getId() );
        localizacao.setContato( localizacaoDTO.getContato() );
        localizacao.setDescricao( localizacaoDTO.getDescricao() );
        localizacao.setEndereco( localizacaoDTO.getEndereco() );
        localizacao.setNome( localizacaoDTO.getNome() );
        localizacao.setResponsavel( localizacaoDTO.getResponsavel() );

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
        movimentacaoDTO.setDataAtualizacao( movimentacao.getDataAtualizacao() );
        movimentacaoDTO.setDataCriacao( movimentacao.getDataCriacao() );
        movimentacaoDTO.setDataMovimentacao( movimentacao.getDataMovimentacao() );
        movimentacaoDTO.setId( movimentacao.getId() );
        movimentacaoDTO.setObservacoes( movimentacao.getObservacoes() );
        movimentacaoDTO.setResponsavelMovimentacao( movimentacao.getResponsavelMovimentacao() );
        movimentacaoDTO.setTipoMovimentacao( movimentacao.getTipoMovimentacao() );

        return movimentacaoDTO;
    }

    @Override
    public Movimentacao movimentacaoDTOToMovimentacao(MovimentacaoDTO movimentacaoDTO) {
        if ( movimentacaoDTO == null ) {
            return null;
        }

        Movimentacao movimentacao = new Movimentacao();

        movimentacao.setDataAtualizacao( movimentacaoDTO.getDataAtualizacao() );
        movimentacao.setDataCriacao( movimentacaoDTO.getDataCriacao() );
        movimentacao.setId( movimentacaoDTO.getId() );
        movimentacao.setDataMovimentacao( movimentacaoDTO.getDataMovimentacao() );
        movimentacao.setObservacoes( movimentacaoDTO.getObservacoes() );
        movimentacao.setResponsavelMovimentacao( movimentacaoDTO.getResponsavelMovimentacao() );
        movimentacao.setTipoMovimentacao( movimentacaoDTO.getTipoMovimentacao() );

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

        inventarioDTO.setDataAtualizacao( inventario.getDataAtualizacao() );
        inventarioDTO.setDataCriacao( inventario.getDataCriacao() );
        inventarioDTO.setDataFim( inventario.getDataFim() );
        inventarioDTO.setDataInicio( inventario.getDataInicio() );
        inventarioDTO.setId( inventario.getId() );
        inventarioDTO.setNome( inventario.getNome() );
        inventarioDTO.setObservacoes( inventario.getObservacoes() );
        inventarioDTO.setResponsavel( inventario.getResponsavel() );
        inventarioDTO.setStatus( inventario.getStatus() );

        return inventarioDTO;
    }

    @Override
    public Inventario inventarioDTOToInventario(InventarioDTO inventarioDTO) {
        if ( inventarioDTO == null ) {
            return null;
        }

        Inventario inventario = new Inventario();

        inventario.setDataAtualizacao( inventarioDTO.getDataAtualizacao() );
        inventario.setDataCriacao( inventarioDTO.getDataCriacao() );
        inventario.setId( inventarioDTO.getId() );
        inventario.setDataFim( inventarioDTO.getDataFim() );
        inventario.setDataInicio( inventarioDTO.getDataInicio() );
        inventario.setNome( inventarioDTO.getNome() );
        inventario.setObservacoes( inventarioDTO.getObservacoes() );
        inventario.setResponsavel( inventarioDTO.getResponsavel() );
        inventario.setStatus( inventarioDTO.getStatus() );

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
        manutencaoDTO.setCausaRaiz( manutencao.getCausaRaiz() );
        manutencaoDTO.setCusto( manutencao.getCusto() );
        manutencaoDTO.setDataAgendamento( manutencao.getDataAgendamento() );
        manutencaoDTO.setDataFim( manutencao.getDataFim() );
        manutencaoDTO.setDataInicio( manutencao.getDataInicio() );
        manutencaoDTO.setDescricao( manutencao.getDescricao() );
        manutencaoDTO.setFornecedor( manutencao.getFornecedor() );
        manutencaoDTO.setId( manutencao.getId() );
        manutencaoDTO.setObservacoes( manutencao.getObservacoes() );
        manutencaoDTO.setPrioridade( manutencao.getPrioridade() );
        manutencaoDTO.setRecomendacoes( manutencao.getRecomendacoes() );
        manutencaoDTO.setResponsavel( manutencao.getResponsavel() );
        manutencaoDTO.setSolucaoAplicada( manutencao.getSolucaoAplicada() );
        manutencaoDTO.setStatus( manutencao.getStatus() );
        manutencaoDTO.setTempoEstimadoHoras( manutencao.getTempoEstimadoHoras() );
        manutencaoDTO.setTipoManutencao( manutencao.getTipoManutencao() );

        return manutencaoDTO;
    }

    @Override
    public Manutencao manutencaoDTOToManutencao(ManutencaoDTO manutencaoDTO) {
        if ( manutencaoDTO == null ) {
            return null;
        }

        Manutencao manutencao = new Manutencao();

        manutencao.setId( manutencaoDTO.getId() );
        manutencao.setCausaRaiz( manutencaoDTO.getCausaRaiz() );
        manutencao.setCusto( manutencaoDTO.getCusto() );
        manutencao.setDataAgendamento( manutencaoDTO.getDataAgendamento() );
        manutencao.setDataFim( manutencaoDTO.getDataFim() );
        manutencao.setDataInicio( manutencaoDTO.getDataInicio() );
        manutencao.setDescricao( manutencaoDTO.getDescricao() );
        manutencao.setFornecedor( manutencaoDTO.getFornecedor() );
        manutencao.setObservacoes( manutencaoDTO.getObservacoes() );
        manutencao.setPrioridade( manutencaoDTO.getPrioridade() );
        manutencao.setRecomendacoes( manutencaoDTO.getRecomendacoes() );
        manutencao.setResponsavel( manutencaoDTO.getResponsavel() );
        manutencao.setSolucaoAplicada( manutencaoDTO.getSolucaoAplicada() );
        manutencao.setStatus( manutencaoDTO.getStatus() );
        manutencao.setTempoEstimadoHoras( manutencaoDTO.getTempoEstimadoHoras() );
        manutencao.setTipoManutencao( manutencaoDTO.getTipoManutencao() );

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
        baixaDTO.setAprovadoPor( baixa.getAprovadoPor() );
        baixaDTO.setComprador( baixa.getComprador() );
        baixaDTO.setDataAprovacao( baixa.getDataAprovacao() );
        baixaDTO.setDataBaixa( baixa.getDataBaixa() );
        baixaDTO.setDataVenda( baixa.getDataVenda() );
        baixaDTO.setDescricao( baixa.getDescricao() );
        baixaDTO.setDestinoFinal( baixa.getDestinoFinal() );
        baixaDTO.setDocumentacaoAnexada( baixa.getDocumentacaoAnexada() );
        baixaDTO.setGanhoFinanceiro( baixa.getGanhoFinanceiro() );
        baixaDTO.setId( baixa.getId() );
        baixaDTO.setJustificativaTecnica( baixa.getJustificativaTecnica() );
        baixaDTO.setMotivo( baixa.getMotivo() );
        baixaDTO.setObservacoes( baixa.getObservacoes() );
        baixaDTO.setPerdaFinanceira( baixa.getPerdaFinanceira() );
        baixaDTO.setProcessoAdministrativo( baixa.getProcessoAdministrativo() );
        baixaDTO.setResponsavel( baixa.getResponsavel() );
        baixaDTO.setValorResidual( baixa.getValorResidual() );
        baixaDTO.setValorVenda( baixa.getValorVenda() );

        return baixaDTO;
    }

    @Override
    public Baixa baixaDTOToBaixa(BaixaDTO baixaDTO) {
        if ( baixaDTO == null ) {
            return null;
        }

        Baixa baixa = new Baixa();

        baixa.setId( baixaDTO.getId() );
        baixa.setAprovadoPor( baixaDTO.getAprovadoPor() );
        baixa.setComprador( baixaDTO.getComprador() );
        baixa.setDataAprovacao( baixaDTO.getDataAprovacao() );
        baixa.setDataBaixa( baixaDTO.getDataBaixa() );
        baixa.setDataVenda( baixaDTO.getDataVenda() );
        baixa.setDescricao( baixaDTO.getDescricao() );
        baixa.setDestinoFinal( baixaDTO.getDestinoFinal() );
        baixa.setDocumentacaoAnexada( baixaDTO.getDocumentacaoAnexada() );
        baixa.setJustificativaTecnica( baixaDTO.getJustificativaTecnica() );
        baixa.setMotivo( baixaDTO.getMotivo() );
        baixa.setObservacoes( baixaDTO.getObservacoes() );
        baixa.setProcessoAdministrativo( baixaDTO.getProcessoAdministrativo() );
        baixa.setResponsavel( baixaDTO.getResponsavel() );
        baixa.setValorResidual( baixaDTO.getValorResidual() );
        baixa.setValorVenda( baixaDTO.getValorVenda() );

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

        usuarioDTO.setAtivo( usuario.getAtivo() );
        usuarioDTO.setBloqueado( usuario.isBloqueado() );
        usuarioDTO.setCargo( usuario.getCargo() );
        usuarioDTO.setCep( usuario.getCep() );
        usuarioDTO.setCidade( usuario.getCidade() );
        usuarioDTO.setCpf( usuario.getCpf() );
        usuarioDTO.setDataBloqueio( usuario.getDataBloqueio() );
        usuarioDTO.setDataExpiracaoSenha( usuario.getDataExpiracaoSenha() );
        usuarioDTO.setDataNascimento( usuario.getDataNascimento() );
        usuarioDTO.setDataUltimoLogin( usuario.getDataUltimoLogin() );
        usuarioDTO.setDepartamento( usuario.getDepartamento() );
        usuarioDTO.setEmail( usuario.getEmail() );
        usuarioDTO.setEndereco( usuario.getEndereco() );
        usuarioDTO.setEstado( usuario.getEstado() );
        usuarioDTO.setId( usuario.getId() );
        usuarioDTO.setMatricula( usuario.getMatricula() );
        usuarioDTO.setNomeCompleto( usuario.getNomeCompleto() );
        usuarioDTO.setPais( usuario.getPais() );
        usuarioDTO.setSenhaExpirada( usuario.isSenhaExpirada() );
        usuarioDTO.setTelefone( usuario.getTelefone() );
        usuarioDTO.setTentativasLoginFalha( usuario.getTentativasLoginFalha() );
        usuarioDTO.setUsername( usuario.getUsername() );

        return usuarioDTO;
    }

    @Override
    public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( usuarioDTO.getId() );
        usuario.setAtivo( usuarioDTO.getAtivo() );
        usuario.setCargo( usuarioDTO.getCargo() );
        usuario.setCep( usuarioDTO.getCep() );
        usuario.setCpf( usuarioDTO.getCpf() );
        usuario.setDataBloqueio( usuarioDTO.getDataBloqueio() );
        usuario.setDataExpiracaoSenha( usuarioDTO.getDataExpiracaoSenha() );
        usuario.setDataNascimento( usuarioDTO.getDataNascimento() );
        usuario.setDataUltimoLogin( usuarioDTO.getDataUltimoLogin() );
        usuario.setDepartamento( usuarioDTO.getDepartamento() );
        usuario.setEmail( usuarioDTO.getEmail() );
        usuario.setEndereco( usuarioDTO.getEndereco() );
        usuario.setEstado( usuarioDTO.getEstado() );
        usuario.setMatricula( usuarioDTO.getMatricula() );
        usuario.setNomeCompleto( usuarioDTO.getNomeCompleto() );
        usuario.setPais( usuarioDTO.getPais() );
        usuario.setTelefone( usuarioDTO.getTelefone() );
        usuario.setTentativasLoginFalha( usuarioDTO.getTentativasLoginFalha() );
        usuario.setUsername( usuarioDTO.getUsername() );

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

        perfilDTO.setAtivo( perfil.getAtivo() );
        perfilDTO.setDescricao( perfil.getDescricao() );
        perfilDTO.setId( perfil.getId() );
        perfilDTO.setNivelAcesso( perfil.getNivelAcesso() );
        perfilDTO.setNome( perfil.getNome() );
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
        perfil.setAtivo( perfilDTO.getAtivo() );
        perfil.setDescricao( perfilDTO.getDescricao() );
        perfil.setNivelAcesso( perfilDTO.getNivelAcesso() );
        perfil.setNome( perfilDTO.getNome() );
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
        notificacaoDTO.setAcaoRequerida( notificacao.getAcaoRequerida() );
        notificacaoDTO.setCategoria( notificacao.getCategoria() );
        notificacaoDTO.setDataEnvio( notificacao.getDataEnvio() );
        notificacaoDTO.setDataLeitura( notificacao.getDataLeitura() );
        notificacaoDTO.setDataUltimaTentativa( notificacao.getDataUltimaTentativa() );
        notificacaoDTO.setEntidadeRelacionada( notificacao.getEntidadeRelacionada() );
        notificacaoDTO.setEnviadaPorEmail( notificacao.getEnviadaPorEmail() );
        notificacaoDTO.setEnviadaPorPush( notificacao.getEnviadaPorPush() );
        notificacaoDTO.setEnviadaPorSms( notificacao.getEnviadaPorSms() );
        notificacaoDTO.setErroEnvio( notificacao.getErroEnvio() );
        notificacaoDTO.setId( notificacao.getId() );
        notificacaoDTO.setIdEntidadeRelacionada( notificacao.getIdEntidadeRelacionada() );
        notificacaoDTO.setLida( notificacao.getLida() );
        notificacaoDTO.setMensagem( notificacao.getMensagem() );
        notificacaoDTO.setPrioridade( notificacao.getPrioridade() );
        notificacaoDTO.setTentativasEnvio( notificacao.getTentativasEnvio() );
        notificacaoDTO.setTipo( notificacao.getTipo() );
        notificacaoDTO.setTitulo( notificacao.getTitulo() );
        notificacaoDTO.setUrlAcao( notificacao.getUrlAcao() );

        return notificacaoDTO;
    }

    @Override
    public Notificacao notificacaoDTOToNotificacao(NotificacaoDTO notificacaoDTO) {
        if ( notificacaoDTO == null ) {
            return null;
        }

        Notificacao notificacao = new Notificacao();

        notificacao.setId( notificacaoDTO.getId() );
        notificacao.setAcaoRequerida( notificacaoDTO.getAcaoRequerida() );
        notificacao.setCategoria( notificacaoDTO.getCategoria() );
        notificacao.setDataEnvio( notificacaoDTO.getDataEnvio() );
        notificacao.setDataLeitura( notificacaoDTO.getDataLeitura() );
        notificacao.setDataUltimaTentativa( notificacaoDTO.getDataUltimaTentativa() );
        notificacao.setEntidadeRelacionada( notificacaoDTO.getEntidadeRelacionada() );
        notificacao.setEnviadaPorEmail( notificacaoDTO.getEnviadaPorEmail() );
        notificacao.setEnviadaPorPush( notificacaoDTO.getEnviadaPorPush() );
        notificacao.setEnviadaPorSms( notificacaoDTO.getEnviadaPorSms() );
        notificacao.setErroEnvio( notificacaoDTO.getErroEnvio() );
        notificacao.setIdEntidadeRelacionada( notificacaoDTO.getIdEntidadeRelacionada() );
        notificacao.setLida( notificacaoDTO.getLida() );
        notificacao.setMensagem( notificacaoDTO.getMensagem() );
        notificacao.setPrioridade( notificacaoDTO.getPrioridade() );
        notificacao.setTentativasEnvio( notificacaoDTO.getTentativasEnvio() );
        notificacao.setTipo( notificacaoDTO.getTipo() );
        notificacao.setTitulo( notificacaoDTO.getTitulo() );
        notificacao.setUrlAcao( notificacaoDTO.getUrlAcao() );

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

        configuracaoDTO.setChave( configuracao.getChave() );
        configuracaoDTO.setDataAtualizacao( configuracao.getDataAtualizacao() );
        configuracaoDTO.setDataCriacao( configuracao.getDataCriacao() );
        configuracaoDTO.setDescricao( configuracao.getDescricao() );
        configuracaoDTO.setEditavel( configuracao.getEditavel() );
        configuracaoDTO.setId( configuracao.getId() );
        configuracaoDTO.setTipo( configuracao.getTipo() );
        configuracaoDTO.setValor( configuracao.getValor() );
        configuracaoDTO.setVersao( configuracao.getVersao() );

        return configuracaoDTO;
    }

    @Override
    public Configuracao configuracaoDTOToConfiguracao(ConfiguracaoDTO configuracaoDTO) {
        if ( configuracaoDTO == null ) {
            return null;
        }

        Configuracao configuracao = new Configuracao();

        configuracao.setDataAtualizacao( configuracaoDTO.getDataAtualizacao() );
        configuracao.setDataCriacao( configuracaoDTO.getDataCriacao() );
        configuracao.setId( configuracaoDTO.getId() );
        configuracao.setVersao( configuracaoDTO.getVersao() );
        configuracao.setChave( configuracaoDTO.getChave() );
        configuracao.setDescricao( configuracaoDTO.getDescricao() );
        configuracao.setEditavel( configuracaoDTO.getEditavel() );
        configuracao.setTipo( configuracaoDTO.getTipo() );
        configuracao.setValor( configuracaoDTO.getValor() );

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
        auditoriaDTO.setAcao( auditoria.getAcao() );
        auditoriaDTO.setDadosAnteriores( auditoria.getDadosAnteriores() );
        auditoriaDTO.setDadosNovos( auditoria.getDadosNovos() );
        auditoriaDTO.setDataAcao( auditoria.getDataAcao() );
        auditoriaDTO.setEntidade( auditoria.getEntidade() );
        auditoriaDTO.setEntidadeId( auditoria.getEntidadeId() );
        auditoriaDTO.setId( auditoria.getId() );
        auditoriaDTO.setIpAddress( auditoria.getIpAddress() );
        auditoriaDTO.setUserAgent( auditoria.getUserAgent() );

        return auditoriaDTO;
    }

    @Override
    public Auditoria auditoriaDTOToAuditoria(AuditoriaDTO auditoriaDTO) {
        if ( auditoriaDTO == null ) {
            return null;
        }

        Auditoria auditoria = new Auditoria();

        auditoria.setId( auditoriaDTO.getId() );
        auditoria.setAcao( auditoriaDTO.getAcao() );
        auditoria.setDadosAnteriores( auditoriaDTO.getDadosAnteriores() );
        auditoria.setDadosNovos( auditoriaDTO.getDadosNovos() );
        auditoria.setDataAcao( auditoriaDTO.getDataAcao() );
        auditoria.setEntidade( auditoriaDTO.getEntidade() );
        auditoria.setEntidadeId( auditoriaDTO.getEntidadeId() );
        auditoria.setIpAddress( auditoriaDTO.getIpAddress() );
        auditoria.setUserAgent( auditoriaDTO.getUserAgent() );

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
