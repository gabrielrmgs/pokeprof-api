package br.uespi.phb.pokemonapi.client.pokeapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PokemonDTO(
                Integer id,
                @JsonProperty("name") String nome,
                @JsonProperty("species") EspecieDTO especies,
                @JsonProperty("types") List<TiposDTO> tipos,
                SpritesDTO sprites) {
}