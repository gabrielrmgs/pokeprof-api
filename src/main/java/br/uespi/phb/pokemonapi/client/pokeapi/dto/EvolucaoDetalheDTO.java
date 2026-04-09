package br.uespi.phb.pokemonapi.client.pokeapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EvolucaoDetalheDTO(
        @JsonProperty("species") EspecieDTO especies,
        @JsonProperty("evolves_to") List<EvolucaoDetalheDTO> listaDeEvolucao) {
}
