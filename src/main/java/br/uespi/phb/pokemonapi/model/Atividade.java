package br.uespi.phb.pokemonapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;

@Table
@Entity
public class Atividade extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "atividade_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "atividade_seq_gen", sequenceName = "atividade_seq")
    private Long id;
    @Column(length = 99, nullable = false)
    private String titulo;
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal pontos;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private boolean ativo;


    @PrePersist
    protected void aoSalvar() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void aoAtualizar() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    public Atividade() {
    }

    public Atividade(Long id, String titulo, BigDecimal pontos, String descricao, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {
        this.id = id;
        this.titulo = titulo;
        this.pontos = pontos;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.ativo = true;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public BigDecimal getPontos() {
        return this.pontos;
    }

    public void setPontos(BigDecimal pontos) {
        this.pontos = pontos;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return this.dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Atividade id(Long id) {
        setId(id);
        return this;
    }

    public Atividade titulo(String titulo) {
        setTitulo(titulo);
        return this;
    }

    public Atividade pontos(BigDecimal pontos) {
        setPontos(pontos);
        return this;
    }

    public Atividade descricao(String descricao) {
        setDescricao(descricao);
        return this;
    }

    public Atividade dataCriacao(LocalDateTime dataCriacao) {
        setDataCriacao(dataCriacao);
        return this;
    }

    public Atividade dataAtualizacao(LocalDateTime dataAtualizacao) {
        setDataAtualizacao(dataAtualizacao);
        return this;
    }

    public Atividade ativo(boolean ativo) {
        setAtivo(ativo);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Atividade)) {
            return false;
        }
        Atividade atividade = (Atividade) o;
        return Objects.equals(id, atividade.id) && Objects.equals(titulo, atividade.titulo) && Objects.equals(pontos, atividade.pontos) && Objects.equals(descricao, atividade.descricao) && Objects.equals(dataCriacao, atividade.dataCriacao) && Objects.equals(dataAtualizacao, atividade.dataAtualizacao) && ativo == atividade.ativo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, pontos, descricao, dataCriacao, dataAtualizacao, ativo);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", titulo='" + getTitulo() + "'" +
            ", pontos='" + getPontos() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", dataCriacao='" + getDataCriacao() + "'" +
            ", dataAtualizacao='" + getDataAtualizacao() + "'" +
            ", ativo='" + isAtivo() + "'" +
            "}";
    }   

}
