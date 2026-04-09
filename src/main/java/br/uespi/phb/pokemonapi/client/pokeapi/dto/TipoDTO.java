package br.uespi.phb.pokemonapi.client.pokeapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TipoDTO(@JsonProperty("name") String nome, String url) {

}
