package br.uespi.phb.pokemonapi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Pokemon extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "pokemon_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "pokemon_seq_gen", sequenceName = "pokemon_seq")
    private Long id;
    private Long idPokeapi;
    private String nome;
    private String urlImagem;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prox_evolucao_id")
    private Pokemon proxEvolucao;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evolucao_ant_id")
    @JsonIgnore
    private Pokemon evolucaoAnterior;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPokemon tipo;
    private boolean ativo;

    public Pokemon() {
    }

    public Pokemon(Long id, Long idPokeapi, String nome, String urlImagem, Pokemon proxEvolucao, Pokemon evolucaoAnterior, TipoPokemon tipo) {
        this.id = id;
        this.idPokeapi = idPokeapi;
        this.nome = nome;
        this.urlImagem = urlImagem;
        this.proxEvolucao = proxEvolucao;
        this.evolucaoAnterior = evolucaoAnterior;
        this.tipo = tipo;
        this.ativo = true;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPokeapi() {
        return this.idPokeapi;
    }

    public void setIdPokeapi(Long idPokeapi) {
        this.idPokeapi = idPokeapi;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImagem() {
        return this.urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public Pokemon getProxEvolucao() {
        return this.proxEvolucao;
    }

    public void setProxEvolucao(Pokemon proxEvolucao) {
        this.proxEvolucao = proxEvolucao;
    }

    public Pokemon getEvolucaoAnterior() {
        return this.evolucaoAnterior;
    }

    public void setEvolucaoAnterior(Pokemon evolucaoAnterior) {
        this.evolucaoAnterior = evolucaoAnterior;
    }

    public TipoPokemon getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoPokemon tipo) {
        this.tipo = tipo;
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

    public Pokemon id(Long id) {
        setId(id);
        return this;
    }

    public Pokemon idPokeapi(Long idPokeapi) {
        setIdPokeapi(idPokeapi);
        return this;
    }

    public Pokemon nome(String nome) {
        setNome(nome);
        return this;
    }

    public Pokemon urlImagem(String urlImagem) {
        setUrlImagem(urlImagem);
        return this;
    }

    public Pokemon proxEvolucao(Pokemon proxEvolucao) {
        setProxEvolucao(proxEvolucao);
        return this;
    }

    public Pokemon evolucaoAnterior(Pokemon evolucaoAnterior) {
        setEvolucaoAnterior(evolucaoAnterior);
        return this;
    }

    public Pokemon tipo(TipoPokemon tipo) {
        setTipo(tipo);
        return this;
    }

    public Pokemon ativo(boolean ativo) {
        setAtivo(ativo);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pokemon)) {
            return false;
        }
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(id, pokemon.id) && Objects.equals(idPokeapi, pokemon.idPokeapi) && Objects.equals(nome, pokemon.nome) && Objects.equals(urlImagem, pokemon.urlImagem) && Objects.equals(proxEvolucao, pokemon.proxEvolucao) && Objects.equals(evolucaoAnterior, pokemon.evolucaoAnterior) && Objects.equals(tipo, pokemon.tipo) && ativo == pokemon.ativo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPokeapi, nome, urlImagem, proxEvolucao, evolucaoAnterior, tipo, ativo);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", idPokeapi='" + getIdPokeapi() + "'" +
            ", nome='" + getNome() + "'" +
            ", urlImagem='" + getUrlImagem() + "'" +
            ", proxEvolucao='" + getProxEvolucao() + "'" +
            ", evolucaoAnterior='" + getEvolucaoAnterior() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", ativo='" + isAtivo() + "'" +
            "}";
    }
    
}
