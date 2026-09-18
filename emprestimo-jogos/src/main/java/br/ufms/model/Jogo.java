package br.ufms.model;

import java.util.List;
import java.util.UUID;

public class Jogo {

    private UUID id;
    private String titulo;
    private Integer classificacaoIndicativa;
    private List<Categoria> categoria;
    private List<EstadoJogo> status;
    private String descricao;

    public Jogo() {
        this.id = UUID.randomUUID();
    }

    public Jogo(String titulo, Integer classificacaoIndicativa, List<Categoria> categoria, List<EstadoJogo> status, String descricao) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.categoria = categoria;
        this.status = status;
        this.descricao = descricao;
    }

    public UUID getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(Integer classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }

    public List<EstadoJogo> getStatus() {
        return status;
    }

    public void setStatus(List<EstadoJogo> status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

