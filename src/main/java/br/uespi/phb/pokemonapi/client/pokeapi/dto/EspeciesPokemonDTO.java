package br.uespi.phb.pokemonapi.client.pokeapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EspeciesPokemonDTO(@JsonProperty("evolves_from_species") String evolucaoAnterior, @JsonProperty("evolution_chain") EvolutionChainUrlDTO evolutionChainUrlDTO) {

}
