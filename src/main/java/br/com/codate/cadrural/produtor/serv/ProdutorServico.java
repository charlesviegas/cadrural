package br.com.codate.cadrural.produtor.serv;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
    public List<Produtor> buscarProdutor() {
	return produtorNegocio.buscarProdutor();
    }

    @POST
    public void inserirProdutor(Produtor produtor) throws CpfCnpjJaExisteException {
	produtorNegocio.inserirProdutor(produtor);
    }

    @PUT
    public void alterarProdutor(Produtor produtor) {
	produtorNegocio.alterarProdutor(produtor);
    }

    @DELETE
    public void excluirProdutor(@QueryParam("idProdutor") Long idProdutor) {
	produtorNegocio.excluirProdutor(idProdutor);
    }

}
