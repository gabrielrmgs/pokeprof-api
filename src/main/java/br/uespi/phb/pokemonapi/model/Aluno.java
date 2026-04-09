package br.uespi.phb.pokemonapi.model;

import java.math.BigDecimal;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Objects;

@Table
@Entity
public class Aluno extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "aluno_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "aluno_seq_gen", sequenceName = "aluno_seq" )
    private Long id;
    @Column(length = 99, nullable = false)
    private String nome;
    @Column(length = 60)
    private String emailInstitucional;
    @Column(length = 15)
    private String matricula;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_selecionado_id", nullable = false)
    private Pokemon pokemonSelecionado;
    @Transient
    private BigDecimal pontos;
    private boolean ativo;

    public Aluno() {
    }

    @PrePersist
    protected void aoSalvar() {
        this.ativo = true;
    }

    public Aluno(Long id, String nome, String emailInstitucional, String matricula, Pokemon pokemonSelecionado, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.emailInstitucional = emailInstitucional;
        this.matricula = matricula;
        this.pokemonSelecionado = pokemonSelecionado;
        this.ativo = ativo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmailInstitucional() {
        return this.emailInstitucional;
    }

    public void setEmailInstitucional(String emailInstitucional) {
        this.emailInstitucional = emailInstitucional;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Pokemon getPokemonSelecionado() {
        return this.pokemonSelecionado;
    }

    public void setPokemonSelecionado(Pokemon pokemonSelecionado) {
        this.pokemonSelecionado = pokemonSelecionado;
    }

    public BigDecimal getPontos() {
        return this.pontos;
    }

    public void setPontos(BigDecimal pontos) {
        this.pontos = pontos;
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

    public Aluno id(Long id) {
        setId(id);
        return this;
    }

    public Aluno nome(String nome) {
        setNome(nome);
        return this;
    }

    public Aluno emailInstitucional(String emailInstitucional) {
        setEmailInstitucional(emailInstitucional);
        return this;
    }

    public Aluno matricula(String matricula) {
        setMatricula(matricula);
        return this;
    }

    public Aluno pokemonSelecionado(Pokemon pokemonSelecionado) {
        setPokemonSelecionado(pokemonSelecionado);
        return this;
    }

    public Aluno ativo(boolean ativo) {
        setAtivo(ativo);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Aluno)) {
            return false;
        }
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id) && Objects.equals(nome, aluno.nome) && Objects.equals(emailInstitucional, aluno.emailInstitucional) && Objects.equals(matricula, aluno.matricula) && Objects.equals(pokemonSelecionado, aluno.pokemonSelecionado) && ativo == aluno.ativo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, emailInstitucional, matricula, pokemonSelecionado, ativo);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", emailInstitucional='" + getEmailInstitucional() + "'" +
            ", matricula='" + getMatricula() + "'" +
            ", pokemonSelecionado='" + getPokemonSelecionado() + "'" +
            ", ativo='" + isAtivo() + "'" +
            "}";
    }   

}
