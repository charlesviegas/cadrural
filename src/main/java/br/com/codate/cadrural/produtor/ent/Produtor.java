package br.com.codate.cadrural.produtor.ent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.com.codate.cadrural.produtor.enuns.EstadoEnum;
import br.com.codate.cadrural.produtor.enuns.TipoPessoaEnum;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "TB_PRODUTOR")
@SequenceGenerator(name = "SEQ_PRODUTOR", sequenceName = "SEQ_PRODUTOR", allocationSize = 1, initialValue = 1)
@NamedQueries({
        @NamedQuery(name = Produtor.BUSCA_PRODUTOR_POR_CPF_CNPJ, query = "SELECT COUNT(p) FROM Produtor p WHERE p.cpfCnpj = ?1"),
        @NamedQuery(name = Produtor.BUSCA_PRODUTOR_POR_ID, query = "SELECT new Produtor(p.id, p.tipoPessoa, p.cpfCnpj, p.nomeRazao, p.logradouro, p.numero, p.municipio, p.estado, p.telefone, p.email, (SELECT COUNT(f) FROM Fazenda f WHERE f.produtor.id = p.id)) FROM Produtor p WHERE p.id = ?1"),
        @NamedQuery(name = Produtor.BUSCA_TODOS_PRODUTORES, query = "SELECT new Produtor(p.id, p.tipoPessoa, p.cpfCnpj, p.nomeRazao, p.logradouro, p.numero, p.municipio, p.estado, p.telefone, p.email, (SELECT COUNT(f) FROM Fazenda f WHERE f.produtor.id = p.id)) FROM Produtor p ORDER BY p.nomeRazao ASC"),
        @NamedQuery(name = Produtor.EXCLUI_PRODUTOR_POR_ID, query = "DELETE FROM Produtor p WHERE p.id = ?1") })
public class Produtor implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String EXCLUI_PRODUTOR_POR_ID = "EXCLUI_PRODUTOR_POR_ID";
    public static final String BUSCA_TODOS_PRODUTORES = "BUSCA_TODOS_PRODUTORES";
    public static final String BUSCA_PRODUTOR_POR_ID = "BUSCA_PRODUTOR_POR_ID";
    public static final String BUSCA_PRODUTOR_POR_CPF_CNPJ = "BUSCA_PRODUTOR_POR_CPF_CNPJ";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PRODUTOR")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_PESSOA", nullable = false, length = 20)
    private TipoPessoaEnum tipoPessoa;

    @Column(name = "CPFCNPJ", nullable = false, length = 20)
    private String cpfCnpj;

    @Column(name = "NOMERAZAO", nullable = false, length = 200)
    private String nomeRazao;

    @Column(name = "LOGRADOURO", nullable = false, length = 250)
    private String logradouro;

    @Column(name = "NUMERO", nullable = true, length = 20)
    private String numero;

    @Column(name = "MUNICIPIO", nullable = false, length = 100)
    private String municipio;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false, length = 100)
    private EstadoEnum estado;

    @Column(name = "TELEFONE", nullable = false, length = 20)
    private String telefone;

    @XmlTransient
    @Column(name = "EMAIL", nullable = true, length = 100)
    private String email;

    // Permite ser usado numa estrutura hierarquica
    @Transient
    private Boolean possuiFazenda = Boolean.FALSE;

    public Produtor() {
	super();
    }

    public Produtor(Long id, TipoPessoaEnum tipoPessoa, String cpfCnpj, String nomeRazao, String logradouro, String numero, String municipio,
	    EstadoEnum estado, String telefone, String email, long qtdFazenda) {
	super();
	this.id = id;
	this.tipoPessoa = tipoPessoa;
	this.cpfCnpj = cpfCnpj;
	this.nomeRazao = nomeRazao;
	this.logradouro = logradouro;
	this.numero = numero;
	this.municipio = municipio;
	this.estado = estado;
	this.telefone = telefone;
	this.email = email;
	this.possuiFazenda = (qtdFazenda > 0);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public TipoPessoaEnum getTipoPessoa() {
	return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
	this.tipoPessoa = tipoPessoa;
    }

    public String getCpfCnpj() {
	return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
	this.cpfCnpj = cpfCnpj;
    }

    public String getNomeRazao() {
	return nomeRazao;
    }

    public void setNomeRazao(String nomeRazao) {
	this.nomeRazao = nomeRazao;
    }

    public String getLogradouro() {
	return logradouro;
    }

    public void setLogradouro(String logradouro) {
	this.logradouro = logradouro;
    }

    public String getNumero() {
	return numero;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public String getMunicipio() {
	return municipio;
    }

    public void setMunicipio(String municipio) {
	this.municipio = municipio;
    }

    public EstadoEnum getEstado() {
	return estado;
    }

    public void setEstado(EstadoEnum estado) {
	this.estado = estado;
    }

    public String getTelefone() {
	return telefone;
    }

    public void setTelefone(String telefone) {
	this.telefone = telefone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Boolean getPossuiFazenda() {
	return possuiFazenda;
    }

    public void setPossuiFazenda(Boolean possuiFazenda) {
	this.possuiFazenda = possuiFazenda;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Produtor [id=");
	builder.append(id);
	builder.append(", tipoPessoa=");
	builder.append(tipoPessoa);
	builder.append(", cpfCnpj=");
	builder.append(cpfCnpj);
	builder.append(", nomeRazao=");
	builder.append(nomeRazao);
	builder.append(", logradouro=");
	builder.append(logradouro);
	builder.append(", numero=");
	builder.append(numero);
	builder.append(", municipio=");
	builder.append(municipio);
	builder.append(", estado=");
	builder.append(estado);
	builder.append(", telefone=");
	builder.append(telefone);
	builder.append(", email=");
	builder.append(email);
	builder.append("]");
	return builder.toString();
    }

}
