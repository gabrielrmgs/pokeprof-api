package br.uespi.phb.pokemonapi.client.pokeapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Cadeia(
                EspecieDTO species,
                @JsonProperty("evolves_to") List<EvolucaoDetalheDTO> listaDeEvolucao) {
}
