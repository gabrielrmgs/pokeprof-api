package br.uespi.phb.pokemonapi.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

import br.uespi.phb.pokemonapi.dto.AlunoRespostaDTO;
import br.uespi.phb.pokemonapi.model.Aluno;
import br.uespi.phb.pokemonapi.model.Atividade;
import br.uespi.phb.pokemonapi.model.Nota;
import br.uespi.phb.pokemonapi.model.Pokemon;

@ApplicationScoped
public class NotaService {

    @Transactional
    public AlunoRespostaDTO lancarNota(Long alunoId, Long atividadeId, BigDecimal valor) {
        Aluno aluno = Aluno.findById(alunoId);
        Long idPokemonAntigo = aluno.getPokemonSelecionado().getId();
        Atividade atividade = Atividade.findById(atividadeId);

        Nota nota = new Nota();
        nota.setAluno(aluno);
        nota.setAtividade(atividade);
        nota.setPontosAtribuidos(valor);
        nota.persist();

        BigDecimal totalPontos = Nota.find("aluno", aluno)
                .list()
                .stream()
                .map(n -> ((Nota) n).getPontosAtribuidos())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        verificarEvolucao(aluno, totalPontos);
        aluno.setPontos(totalPontos);

        String proxNome = null;
        if (aluno.getPokemonSelecionado().getProxEvolucao() != null) {
            proxNome = aluno.getPokemonSelecionado().getProxEvolucao().getNome();
        }

        Long idPokemonDepois = aluno.getPokemonSelecionado().getId();

        return new AlunoRespostaDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getPokemonSelecionado().getNome(),
                aluno.getPokemonSelecionado().getIdPokeapi(),
                proxNome,
                totalPontos,
                aluno.getPokemonSelecionado().getUrlImagem(),
                !idPokemonAntigo.equals(idPokemonDepois));
    }

    private void verificarEvolucao(Aluno aluno, BigDecimal total) {
        BigDecimal metaEvo2 = new BigDecimal("100.0");
        BigDecimal metaEvo3 = new BigDecimal("200.0");

        Pokemon atual = aluno.getPokemonSelecionado();

        if (atual.getProxEvolucao() == null)
            return;

        if (total.compareTo(metaEvo3) >= 0) {
            evoluirParaEstagioFinal(aluno);
        } else if (total.compareTo(metaEvo2) >= 0) {
            Pokemon proxima = atual.getProxEvolucao();
            if (proxima.getProxEvolucao() != null) {
                aluno.setPokemonSelecionado(proxima);
            }
        }
    }

    private void evoluirParaEstagioFinal(Aluno aluno) {
        Pokemon pokemon = aluno.getPokemonSelecionado();
        while (pokemon.getProxEvolucao() != null) {
            pokemon = pokemon.getProxEvolucao();
        }
        aluno.setPokemonSelecionado(pokemon);
    }
}