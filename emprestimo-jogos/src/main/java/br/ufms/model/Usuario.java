package br.ufms.model;

import java.time.LocalDate;
import java.util.UUID;
import br.ufms.util.Validar;

public class Usuario {

    private final UUID id;
    private String nome;
    private String email;
    private String usuario;
    private String senha;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

    public Usuario(String nome, String email, String usuario, String senha, String cpf, String telefone, LocalDate dataNascimento) {
        this.id = UUID.randomUUID();
        setNome(nome);
        setEmail(email);
        setUsuario(usuario);
        setSenha(senha);
        setCpf(cpf);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Validar.nomePessoa(nome);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Validar.email(email);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = Validar.usuario(usuario);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = Validar.senha(senha);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = Validar.cpf(cpf);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = Validar.telefone(telefone);
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = Validar.dataNascimento(dataNascimento);
    }
}

