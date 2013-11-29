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

    @DAO
    @Inject
    private GenericDAO<Fazenda> fazendaDAO;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Produtor> buscarProdutor() {
	return produtorDAO.findAll();
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Fazenda> buscarFazenda(Long idProdutor) {
	return fazendaDAO.queryList(Fazenda.BUSCA_FAZENDA_POR_PRODUTOR, idProdutor);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Produtor inserirProdutor(Produtor produtor) throws CpfCnpjJaExisteException {
	Long existe = longDAO.querySingle(Produtor.BUSCA_PRODUTOR_POR_CPF_CNPJ, produtor.getCpfCnpj());
	if (existe > 0) {
	    throw new CpfCnpjJaExisteException("Ja existe um produtor com mesmo cpf/cnpj");
	}
	Produtor produtorInserido = produtorDAO.insert(produtor);
	return produtorInserido;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Fazenda inserirFazenda(Fazenda fazenda) {
	Fazenda fazendaInserido = fazendaDAO.insert(fazenda);
	return fazendaInserido;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Produtor alterarProdutor(Produtor produtor) {
	Produtor produtorAlterado = produtorDAO.update(produtor);
	return produtorAlterado;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Fazenda alterarFazenda(Fazenda fazenda) {
	Fazenda fazendaAlterado = fazendaDAO.update(fazenda);
	return fazendaAlterado;
    }

}
