package br.uespi.phb.pokemonapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record NotaRespostaDTO(
        Long id,
        String atividadeTitulo,
        BigDecimal pontosAtribuidos,
        LocalDateTime dataCriacao) {
}