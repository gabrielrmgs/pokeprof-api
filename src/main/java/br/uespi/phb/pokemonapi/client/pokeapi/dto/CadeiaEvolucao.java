package br.uespi.phb.pokemonapi.client.pokeapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CadeiaEvolucao(@JsonProperty("chain") Cadeia cadeia) {
}