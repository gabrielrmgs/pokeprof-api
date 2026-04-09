package br.uespi.phb.pokemonapi.client.pokeapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EvolutionChainUrlDTO(@JsonProperty("url") String urlCadeiaDeEvolucao) {

}
