package br.uespi.phb.pokemonapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AlunoDTO(String nome, @JsonProperty("pokemonId") Long pokemonId) {
}
