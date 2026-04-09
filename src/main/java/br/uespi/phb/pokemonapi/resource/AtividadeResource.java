package br.uespi.phb.pokemonapi.resource;

import java.util.List;

import br.uespi.phb.pokemonapi.dto.AtividadeDTO;
import br.uespi.phb.pokemonapi.model.Atividade;
import br.uespi.phb.pokemonapi.service.AtividadeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ativades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AtividadeResource {

    private AtividadeService atividadeService;

    @Inject
    public AtividadeResource(AtividadeService atividadeService) {
        this.atividadeService = atividadeService;
    }

    @POST
    public Response criar(AtividadeDTO dto) {
        Atividade atv = atividadeService.criarAtividade(dto);
        return Response.status(Response.Status.CREATED).entity(atv).build();
    }

    @GET
    public List<Atividade> listar() {
        return Atividade.listAll();
    }

}
