package br.uespi.phb.pokemonapi.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AtividadeDTO(String titulo, String descricao, @JsonProperty("pontos") BigDecimal pontos) {
}
