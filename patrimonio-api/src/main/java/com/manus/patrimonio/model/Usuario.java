package com.manus.patrimonio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario extends EntidadeBase {

    @NotBlank(message = "Username é obrigatório")
    @Size(min = 3, max = 50, message = "Username deve ter entre 3 e 50 caracteres")
    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 2, max = 150, message = "Nome completo deve ter entre 2 e 150 caracteres")
    @Column(name = "nome_completo", nullable = false, length = 150)
    private String nomeCompleto;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name = "data_ultimo_login")
    private LocalDateTime dataUltimoLogin;

    @Column(name = "data_expiracao_senha")
    private LocalDateTime dataExpiracaoSenha;

    @Column(name = "tentativas_login_falha")
    private Integer tentativasLoginFalha = 0;

    @Column(name = "data_bloqueio")
    private LocalDateTime dataBloqueio;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "matricula")
    private String matricula;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cep")
    private String cep;

    @Column(name = "pais")
    private String pais = "Brasil";

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_perfis",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private List<Perfil> perfis = new ArrayList<>();

    // Constructors
    public Usuario() {}

    public Usuario(String username, String email, String nomeCompleto, String senhaHash) {
        this.username = username;
        this.email = email;
        this.nomeCompleto = nomeCompleto;
        this.senhaHash = senhaHash;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataUltimoLogin() {
        return dataUltimoLogin;
    }

    public void setDataUltimoLogin(LocalDateTime dataUltimoLogin) {
        this.dataUltimoLogin = dataUltimoLogin;
    }

    public LocalDateTime getDataExpiracaoSenha() {
        return dataExpiracaoSenha;
    }

    public void setDataExpiracaoSenha(LocalDateTime dataExpiracaoSenha) {
        this.dataExpiracaoSenha = dataExpiracaoSenha;
    }

    public Integer getTentativasLoginFalha() {
        return tentativasLoginFalha;
    }

    public void setTentativasLoginFalha(Integer tentativasLoginFalha) {
        this.tentativasLoginFalha = tentativasLoginFalha;
    }

    public LocalDateTime getDataBloqueio() {
        return dataBloqueio;
    }

    public void setDataBloqueio(LocalDateTime dataBloqueio) {
        this.dataBloqueio = dataBloqueio;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    // Business methods
    public boolean isAtivo() {
        return ativo != null && ativo;
    }

    public boolean isBloqueado() {
        return dataBloqueio != null;
    }

    public boolean isSenhaExpirada() {
        return dataExpiracaoSenha != null && LocalDateTime.now().isAfter(dataExpiracaoSenha);
    }

    public boolean isAdmin() {
        return perfis.stream().anyMatch(perfil -> "ADMIN".equals(perfil.getNome()));
    }

    public boolean hasPerfil(String nomePerfil) {
        return perfis.stream().anyMatch(perfil -> nomePerfil.equals(perfil.getNome()));
    }

    public void adicionarPerfil(Perfil perfil) {
        if (!perfis.contains(perfil)) {
            perfis.add(perfil);
        }
    }

    public void removerPerfil(Perfil perfil) {
        perfis.remove(perfil);
    }

    public void registrarLoginSucesso() {
        this.dataUltimoLogin = LocalDateTime.now();
        this.tentativasLoginFalha = 0;
        this.dataBloqueio = null;
    }

    public void registrarLoginFalha() {
        this.tentativasLoginFalha++;
        if (this.tentativasLoginFalha >= 3) {
            this.dataBloqueio = LocalDateTime.now();
        }
    }

    public void desbloquear() {
        this.dataBloqueio = null;
        this.tentativasLoginFalha = 0;
    }

    public void ativar() {
        this.ativo = true;
        this.dataBloqueio = null;
        this.tentativasLoginFalha = 0;
    }

    public void desativar() {
        this.ativo = false;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + getId() +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}
