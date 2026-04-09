package br.uespi.phb.pokemonapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class Nota extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "nota_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "nota_seq_gen", sequenceName = "nota_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atividade_id")
    private Atividade atividade;
    @Column(precision = 5, scale = 2)
    private BigDecimal pontosAtribuidos;
    private boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public Nota() {
    }

    public Nota(Long id, Aluno aluno, Atividade atividade, BigDecimal pontosAtribuidos, LocalDateTime dataCriacao,
            LocalDateTime dataAtualizacao) {
        this.id = id;
        this.aluno = aluno;
        this.atividade = atividade;
        this.pontosAtribuidos = pontosAtribuidos;
        this.ativo = true;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    @PrePersist
    protected void aoSalvar() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void aoAtualizar() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return this.aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Atividade getAtividade() {
        return this.atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public BigDecimal getPontosAtribuidos() {
        return this.pontosAtribuidos;
    }

    public void setPontosAtribuidos(BigDecimal pontosAtribuidos) {
        this.pontosAtribuidos = pontosAtribuidos;
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

    public Nota id(Long id) {
        setId(id);
        return this;
    }

    public Nota aluno(Aluno aluno) {
        setAluno(aluno);
        return this;
    }

    public Nota atividade(Atividade atividade) {
        setAtividade(atividade);
        return this;
    }

    public Nota pontosAtribuidos(BigDecimal pontosAtribuidos) {
        setPontosAtribuidos(pontosAtribuidos);
        return this;
    }

    public Nota ativo(boolean ativo) {
        setAtivo(ativo);
        return this;
    }

    public Nota dataCriacao(LocalDateTime dataCriacao) {
        setDataCriacao(dataCriacao);
        return this;
    }

    public Nota dataAtualizacao(LocalDateTime dataAtualizacao) {
        setDataAtualizacao(dataAtualizacao);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Nota)) {
            return false;
        }
        Nota nota = (Nota) o;
        return Objects.equals(id, nota.id) && Objects.equals(aluno, nota.aluno)
                && Objects.equals(atividade, nota.atividade) && Objects.equals(pontosAtribuidos, nota.pontosAtribuidos)
                && ativo == nota.ativo && Objects.equals(dataCriacao, nota.dataCriacao)
                && Objects.equals(dataAtualizacao, nota.dataAtualizacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, aluno, atividade, pontosAtribuidos, ativo, dataCriacao, dataAtualizacao);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", aluno='" + getAluno() + "'" +
                ", atividade='" + getAtividade() + "'" +
                ", pontosAtribuidos='" + getPontosAtribuidos() + "'" +
                ", ativo='" + isAtivo() + "'" +
                ", dataCriacao='" + getDataCriacao() + "'" +
                ", dataAtualizacao='" + getDataAtualizacao() + "'" +
                "}";
    }

}
