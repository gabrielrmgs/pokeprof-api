package br.uespi.phb.pokemonapi.client.pokeapi;

import br.uespi.phb.pokemonapi.client.pokeapi.dto.CadeiaEvolucao;
import br.uespi.phb.pokemonapi.client.pokeapi.dto.EspeciesPokemonDTO;
import br.uespi.phb.pokemonapi.client.pokeapi.dto.PokemonDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(configKey = "poke-api")
public interface PokeApiClient {

    @GET
    @Path("/pokemon/{id}")
    PokemonDTO getPokemonById(@PathParam("id") int id);

    @GET
    @Path("/pokemon/{name}")
    PokemonDTO getPokemonPorNome(@PathParam("name") String name);

    @GET
    @Path("/pokemon-species/{id}")
    EspeciesPokemonDTO getPokemonEspecies(@PathParam("id") Long id);

    @GET
    @Path("/evolution-chain/{id}")
    CadeiaEvolucao getCadeiaEvolucao(@PathParam("id") String id);

}