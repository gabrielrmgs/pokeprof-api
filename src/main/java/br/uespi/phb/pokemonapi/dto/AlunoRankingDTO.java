package br.uespi.phb.pokemonapi.dto;

import java.math.BigDecimal;

public record AlunoRankingDTO(
    Long id,
    String nome,
    String pokemonNome,
    Long pokemonIdPokeapi,
    String pokemonUrlImagem,
    String proximaEvolucaoNome,
    BigDecimal totalPontos
) {}
