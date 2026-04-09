package br.uespi.phb.pokemonapi.resource;

import br.uespi.phb.pokemonapi.dto.AlunoRespostaDTO;
import br.uespi.phb.pokemonapi.dto.LancamentoNotaDTO;
import br.uespi.phb.pokemonapi.model.Aluno;
import br.uespi.phb.pokemonapi.service.NotaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/notas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotaResource {

    private final NotaService notaService;

    public NotaResource(NotaService notaService) {
        this.notaService = notaService;
    }

    @POST
    public Response lancarNota(LancamentoNotaDTO dto) {
        AlunoRespostaDTO alunoAtualizado = notaService.lancarNota(
            dto.alunoId(), 
            dto.atividadeId(), 
            dto.valor()
        );

        return Response.status(Response.Status.CREATED)
                       .entity(alunoAtualizado)
                       .build();
    }
}