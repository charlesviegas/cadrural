package br.com.codate.cadrural.produtor.neg;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.codate.cadrural.produtor.ent.Fazenda;
import br.com.codate.cadrural.produtor.ent.Produtor;
import br.com.codate.cadrural.produtor.exc.CpfCnpjJaExisteException;
import br.com.codate.cadrural.util.generic.DAO;
import br.com.codate.cadrural.util.generic.GenericDAO;

@LocalBean
@Stateless
public class ProdutorNegocio {

    @DAO
    @Inject
    private GenericDAO<Long> longDAO;

    @DAO
    @Inject
    private GenericDAO<Produtor> produtorDAO;
    

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Produtor> buscarProdutor() {
	return produtorDAO.queryList(Produtor.BUSCA_TODOS_PRODUTORES);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Produtor inserirProdutor(Produtor produtor) throws CpfCnpjJaExisteException {
	Long existe = longDAO.querySingle(Produtor.BUSCA_PRODUTOR_POR_CPF_CNPJ, produtor.getCpfCnpj());
	if (existe > 0) {
	    throw new CpfCnpjJaExisteException("Ja existe um produtor com mesmo cpf/cnpj");
	}
	Produtor produtorInserido = produtorDAO.insert(produtor);
	return buscarProdutorPorId(produtorInserido.getId());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Produtor alterarProdutor(Produtor produtor) {
	Produtor produtorAlterado = produtorDAO.update(produtor);
	return buscarProdutorPorId(produtorAlterado.getId());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void excluirProdutor(Long idProdutor) {
	produtorDAO.executeUpdate(Fazenda.EXCLUI_FAZENDA_POR_PRODUTOR, idProdutor);
	produtorDAO.executeUpdate(Produtor.EXCLUI_PRODUTOR_POR_ID, idProdutor);
    }
    
    private Produtor buscarProdutorPorId(Long id){
	return produtorDAO.querySingle(Produtor.BUSCA_PRODUTOR_POR_ID, id);
    }

}
