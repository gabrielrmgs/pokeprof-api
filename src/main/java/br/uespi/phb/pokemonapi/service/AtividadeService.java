package br.uespi.phb.pokemonapi.service;

import br.uespi.phb.pokemonapi.dto.AtividadeDTO;
import br.uespi.phb.pokemonapi.model.Atividade;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AtividadeService {

    @Transactional
    public Atividade criarAtividade(AtividadeDTO dto) {

        Atividade atividade = new Atividade();
        atividade.setTitulo(dto.titulo());
        atividade.setDescricao(dto.descricao());
        System.out.println("Pontos aqui -----------> " + dto);
        atividade.setPontos(dto.pontos());

        atividade.persist();
        return atividade;
    }
}
