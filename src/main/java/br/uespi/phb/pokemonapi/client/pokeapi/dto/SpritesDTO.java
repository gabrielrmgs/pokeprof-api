package br.uespi.phb.pokemonapi.client.pokeapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpritesDTO(
        @JsonProperty("front_default") String frontDefault) {
}
