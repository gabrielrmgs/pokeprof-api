package br.uespi.phb.pokemonapi.resource;

import java.math.BigDecimal;
import java.util.List;

import br.uespi.phb.pokemonapi.dto.AlunoDTO;
import br.uespi.phb.pokemonapi.dto.AlunoPontosDTO;
import br.uespi.phb.pokemonapi.dto.AlunoRankingDTO;
import br.uespi.phb.pokemonapi.dto.NotaRespostaDTO;
import br.uespi.phb.pokemonapi.model.Aluno;
import br.uespi.phb.pokemonapi.service.AlunoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoResource {

    private AlunoService alunoService;

    @Inject
    public AlunoResource(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @POST
    public Response criar(AlunoDTO dto) {
        try {
            Aluno aluno = alunoService.cadastrarAluno(dto);
            return Response.status(Response.Status.CREATED).entity(aluno).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response listarAlunosAtivos() {
        List<Aluno> alunos = alunoService.listarAlunos();
        return Response.ok().entity(alunos).build();
    }

    @GET
    @Path("/{id}/pontos")
    public Response totalPontosPorAluno(@PathParam("id") Long id) {
        Aluno aluno = Aluno.findById(id);
        BigDecimal totalPontosAluno = alunoService.getTotalPontos(aluno);
        return Response.ok().entity(new AlunoPontosDTO(id, totalPontosAluno)).build();
    }

    @GET
    @Path("/ranking")
    public Response ranking(
            @QueryParam("pokemonNome") String pokemonNome,
            @QueryParam("atividadeId") Long atividadeId) {
        return Response.ok(alunoService.getRanking(pokemonNome, atividadeId)).build();
    }

    @GET
    @Path("/{id}/notas")
    public Response historicoNotas(@PathParam("id") Long id) {
        List<NotaRespostaDTO> notas = alunoService.getHistoricoNotas(id);
        return Response.ok(notas).build();
    }

}