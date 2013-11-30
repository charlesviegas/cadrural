package br.com.codate.cadrural.produtor.neg;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.codate.cadrural.produtor.ent.Fazenda;
import br.com.codate.cadrural.util.generic.DAO;
import br.com.codate.cadrural.util.generic.GenericDAO;

@LocalBean
@Stateless
public class FazendaNegocio {

    @DAO
    @Inject
    private GenericDAO<Fazenda> fazendaDAO;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Fazenda> buscarFazenda(Long idProdutor) {
	return fazendaDAO.queryList(Fazenda.BUSCA_FAZENDA_POR_PRODUTOR, idProdutor);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Fazenda inserirFazenda(Fazenda fazenda) {
	Fazenda fazendaInserido = fazendaDAO.insert(fazenda);
	return fazendaInserido;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Fazenda alterarFazenda(Fazenda fazenda) {
	Fazenda fazendaAlterado = fazendaDAO.update(fazenda);
	return fazendaAlterado;
    }

}
