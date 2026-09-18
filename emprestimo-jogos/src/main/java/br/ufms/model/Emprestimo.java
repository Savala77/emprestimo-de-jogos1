package br.ufms.model;

import java.time.Duration;
import java.time.LocalDate;

public class Emprestimo {

    private Usuario proprietario;
    private Usuario cliente;
    private Jogo jogo;
    private StatusEmprestimo status;
    private LocalDate dataEmprestimo;
    private LocalDate dataEntrega;
    private Duration prazoevolucao;

    public Emprestimo() {
    }

    public Emprestimo(Usuario proprietario, Usuario cliente, Jogo jogo, StatusEmprestimo status, LocalDate dataEmprestimo, LocalDate dataEntrega, Duration prazoevolucao) {
        this.proprietario = proprietario;
        this.cliente = cliente;
        this.jogo = jogo;
        this.status = status;
        this.dataEmprestimo = dataEmprestimo;
        this.dataEntrega = dataEntrega;
        this.prazoevolucao = prazoevolucao;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Duration getPrazoevolucao() {
        return prazoevolucao;
    }

    public void setPrazoevolucao(Duration prazoevolucao) {
        this.prazoevolucao = prazoevolucao;
    }
}
