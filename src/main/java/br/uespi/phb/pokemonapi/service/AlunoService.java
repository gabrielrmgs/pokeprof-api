package br.uespi.phb.pokemonapi.service;

import br.uespi.phb.pokemonapi.model.*;
import io.quarkus.panache.common.Sort;
import br.uespi.phb.pokemonapi.client.pokeapi.PokeApiClient;
import br.uespi.phb.pokemonapi.client.pokeapi.dto.CadeiaEvolucao;
import br.uespi.phb.pokemonapi.client.pokeapi.dto.EspeciesPokemonDTO;
import br.uespi.phb.pokemonapi.client.pokeapi.dto.PokemonDTO;
import br.uespi.phb.pokemonapi.dto.AlunoDTO;
import br.uespi.phb.pokemonapi.dto.AlunoRankingDTO;
import br.uespi.phb.pokemonapi.dto.NotaRespostaDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AlunoService {

    @Inject
    @RestClient
    PokeApiClient pokeApiClient;

    private static final List<Long> BASES_TRES_EVOLUCOES = List.of(1L, 4L, 7L, 10L, 13L, 16L, 29L, 32L, 43L, 63L, 66L,
            69L, 74L, 92L, 147L);

    @Transactional
    public Aluno cadastrarAluno(AlunoDTO dto) {
        System.out.println(dto);
        if (!BASES_TRES_EVOLUCOES.contains(dto.pokemonId())) {
            throw new IllegalArgumentException("O professor só pode atribuir Pokémons base (estágio inicial).");
        }

        Pokemon pokemon = buscarOuCriarPokemonSelecionado(dto.pokemonId());

        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setPokemonSelecionado(pokemon);
        aluno.persist();
        aluno.setPontos(BigDecimal.ZERO);
        return aluno;
    }

    private Pokemon buscarOuCriarPokemonSelecionado(Long idBase) {

        Pokemon pokemon = Pokemon.findById(idBase);
        if (pokemon == null) {

            EspeciesPokemonDTO especies = pokeApiClient.getPokemonEspecies(idBase);
            String chainId = extrairIdDaUrl(especies.evolutionChainUrlDTO().urlCadeiaDeEvolucao());

            CadeiaEvolucao cadeiaEvolucao = pokeApiClient.getCadeiaEvolucao(chainId);

            String nomeBase = cadeiaEvolucao.cadeia().species().nome();
            String nomeEvo1 = cadeiaEvolucao.cadeia().listaDeEvolucao().get(0).especies().nome();
            String nomeEvo2 = cadeiaEvolucao.cadeia().listaDeEvolucao().get(0).listaDeEvolucao().get(0).especies()
                    .nome();

            Pokemon p3 = buscarESalvarPeloNome(nomeEvo2, null);
            Pokemon p2 = buscarESalvarPeloNome(nomeEvo1, p3);
            Pokemon p1 = buscarESalvarPeloNome(nomeBase, p2);
            p3.setEvolucaoAnterior(p2);
            p2.setEvolucaoAnterior(p1);
            p1.setEvolucaoAnterior(null);
            return p1;

        }

        return pokemon;

    }

    private Pokemon buscarESalvarPeloNome(String nome, Pokemon proximaEvolucao) {

        PokemonDTO dtoPokemonApi = pokeApiClient.getPokemonPorNome(nome);

        Pokemon p = new Pokemon();
        p.setIdPokeapi(Long.valueOf(dtoPokemonApi.id()));
        p.setNome(dtoPokemonApi.nome());
        p.setUrlImagem(dtoPokemonApi.sprites().frontDefault());
        p.setTipo(TipoPokemon.fromPokeapi(dtoPokemonApi.tipos().get(0).tipo().nome()));
        p.setProxEvolucao(proximaEvolucao);

        p.persist();
        return p;
    }

    private String extrairIdDaUrl(String url) {
        if (url == null || url.isEmpty()) {
            return "";
        }
        String urlLimpa = url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
        String[] partes = urlLimpa.split("/");
        return partes[partes.length - 1];
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = Aluno.list("ativo", true);
        alunos.forEach(this::preencherPontos);
        return alunos;
    }

    public BigDecimal getTotalPontos(Aluno aluno) {
        return Nota.find("aluno", aluno)
                .list()
                .stream()
                .map(n -> ((Nota) n).getPontosAtribuidos())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void preencherPontos(Aluno aluno) {
        aluno.setPontos(getTotalPontos(aluno));
    }

    public List<AlunoRankingDTO> getRanking(String pokemonNome, Long atividadeId) {

        List<Aluno> alunos = Aluno.list("ativo", true);

        List<AlunoRankingDTO> ranking = new ArrayList<>();

        for (Aluno aluno : alunos) {

            Pokemon pokemon = aluno.getPokemonSelecionado();

            if (pokemonNome != null && !pokemonNome.isBlank()) {
                if (!pokemon.getNome().equalsIgnoreCase(pokemonNome)) {
                    continue;
                }
            }

            List<Nota> notas;
            if (atividadeId != null) {
                notas = Nota.find("aluno = ?1 and atividade.id = ?2", aluno, atividadeId).list();
            } else {
                notas = Nota.find("aluno", aluno).list();
            }

            BigDecimal total = BigDecimal.ZERO;
            for (Nota nota : notas) {
                total = total.add(nota.getPontosAtribuidos());
            }

            String proxNome = null;
            if (pokemon.getProxEvolucao() != null) {
                proxNome = pokemon.getProxEvolucao().getNome();
            }

            AlunoRankingDTO dto = new AlunoRankingDTO(
                    aluno.getId(),
                    aluno.getNome(),
                    pokemon.getNome(),
                    pokemon.getIdPokeapi(),
                    pokemon.getUrlImagem(),
                    proxNome,
                    total);

            ranking.add(dto);
        }

        ranking.sort((a, b) -> b.totalPontos().compareTo(a.totalPontos()));

        return ranking;
    }

    public List<NotaRespostaDTO> getHistoricoNotas(Long alunoId) {
        Aluno aluno = Aluno.findById(alunoId);
        List<Nota> notas = Nota.find("aluno", Sort.by("dataCriacao").descending(), aluno).list();

        List<NotaRespostaDTO> resultado = new ArrayList<>();
        for (Nota nota : notas) {
            resultado.add(new NotaRespostaDTO(
                    nota.getId(),
                    nota.getAtividade().getTitulo(),
                    nota.getPontosAtribuidos(),
                    nota.getDataCriacao()));
        }
        return resultado;
    }
}
