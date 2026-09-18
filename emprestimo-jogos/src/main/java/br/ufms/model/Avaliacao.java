package br.ufms.model;

import java.util.UUID;

public class Avaliacao {

    private UUID id;
    private Usuario avaliador;
    private Jogo jogo;
    private NivelAvaliacao nivelAvaliacao;
    private String descricao;

    public Avaliacao() {
        this.id = UUID.randomUUID();
    }

    public Avaliacao(Usuario avaliador, Jogo jogo, NivelAvaliacao nivelAvaliacao, String descricao) {
        this.id = UUID.randomUUID();
        this.avaliador = avaliador;
        this.jogo = jogo;
        this.nivelAvaliacao = nivelAvaliacao;
        this.descricao = descricao;
    }

    public UUID getId() {
        return id;
    }

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public NivelAvaliacao getNivelAvaliacao() {
        return nivelAvaliacao;
    }

    public void setNivelAvaliacao(NivelAvaliacao nivelAvaliacao) {
        this.nivelAvaliacao = nivelAvaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
