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
import br.com.codate.cadrural.produtor.ent.Produtor;
import br.com.codate.cadrural.produtor.exc.CpfCnpjJaExisteException;
import br.com.codate.cadrural.produtor.neg.ProdutorNegocio;

@Path("produtor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutorServico {

    @Inject
    private ProdutorNegocio produtorNegocio;

    @GET
    @Path("produtor")
    public List<Produtor> buscarProdutor() {
	return produtorNegocio.buscarProdutor();
    }

    @GET
    @Path("fazenda")
    public List<Fazenda> buscarFazenda(@QueryParam("idProdutor") Long idProdutor) {
	return produtorNegocio.buscarFazenda(idProdutor);
    }

    @POST
    @Path("produtor")
    public void inserirProdutor(Produtor produtor) throws CpfCnpjJaExisteException {
	produtorNegocio.inserirProdutor(produtor);
    }

    @POST
    @Path("fazenda")
    public void inserirFazenda(Fazenda fazenda) {
	produtorNegocio.inserirFazenda(fazenda);
    }

    @PUT
    @Path("produtor")
    public void alterarProdutor(Produtor produtor) {
	produtorNegocio.alterarProdutor(produtor);
    }

    @PUT
    @Path("fazenda")
    public void alterarFazenda(Fazenda fazenda) {
	produtorNegocio.alterarFazenda(fazenda);
    }

}
