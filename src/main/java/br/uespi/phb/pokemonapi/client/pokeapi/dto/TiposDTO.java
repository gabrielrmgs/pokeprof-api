package br.uespi.phb.pokemonapi.client.pokeapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TiposDTO(int slot, @JsonProperty("type") TipoDTO tipo) {

}
