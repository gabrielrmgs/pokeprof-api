package br.uespi.phb.pokemonapi.dto;

import java.math.BigDecimal;

public record AlunoRespostaDTO(
        Long id,
        String nome,
        String pokemonNome,
        Long pokemonId,
        String proximaEvolucaoNome,
        BigDecimal totalPontos,
        String pokemonAtualUrlImagem,
        boolean evoluiuNesteLancamento) {
}
