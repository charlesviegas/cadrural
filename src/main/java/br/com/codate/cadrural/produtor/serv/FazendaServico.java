package br.com.codate.cadrural.produtor.serv;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.codate.cadrural.produtor.ent.Fazenda;
import br.com.codate.cadrural.produtor.neg.FazendaNegocio;

@Path("fazenda")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FazendaServico {

    @Inject
    private FazendaNegocio produtorNegocio;

    @GET
    public List<Fazenda> buscarFazenda(@QueryParam("idProdutor") Long idProdutor) {
	return produtorNegocio.buscarFazenda(idProdutor);
    }

    @POST
    public void inserirFazenda(Fazenda fazenda) {
	produtorNegocio.inserirFazenda(fazenda);
    }

    @PUT
    public void alterarFazenda(Fazenda fazenda) {
	produtorNegocio.alterarFazenda(fazenda);
    }

}
