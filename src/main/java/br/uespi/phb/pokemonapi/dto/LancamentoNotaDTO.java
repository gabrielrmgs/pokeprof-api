package br.uespi.phb.pokemonapi.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LancamentoNotaDTO(
        @JsonProperty("alunoId") Long alunoId,
        @JsonProperty("atividadeId") Long atividadeId,
        @JsonProperty("valor") BigDecimal valor) {
}