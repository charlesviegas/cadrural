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
@NamedQueries({ @NamedQuery(name = Produtor.BUSCA_PRODUTOR_POR_CPF_CNPJ, query = "SELECT COUNT(p) FROM Produtor p WHERE p.cpfCnpj = ?1"),
        @NamedQuery(name = Produtor.EXCLUI_PRODUTOR_POR_ID, query = "DELETE FROM Produtor p WHERE p.id = ?1") })
public class Produtor implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String EXCLUI_PRODUTOR_POR_ID = "EXCLUI_PRODUTOR_POR_ID";
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

    @Column(name = "INSCESTADUAL", nullable = true, length = 100)
    private String inscricaoEstadual;

    @Column(name = "NOMERAZAO", nullable = false, length = 200)
    private String nomeRazao;

    @Column(name = "LOGRADOURO", nullable = false, length = 250)
    private String logradouro;

    @Column(name = "NUMERO", nullable = true, length = 20)
    private String numero;

    @Column(name = "COMPLEMENTO", nullable = true, length = 100)
    private String complemento;

    @Column(name = "CEP", nullable = true, length = 20)
    private String cep;

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

    public String getInscricaoEstadual() {
	return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
	this.inscricaoEstadual = inscricaoEstadual;
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

    public String getComplemento() {
	return complemento;
    }

    public void setComplemento(String complemento) {
	this.complemento = complemento;
    }

    public String getCep() {
	return cep;
    }

    public void setCep(String cep) {
	this.cep = cep;
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

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Produtor [id=");
	builder.append(id);
	builder.append(", tipoPessoa=");
	builder.append(tipoPessoa);
	builder.append(", cpfCnpj=");
	builder.append(cpfCnpj);
	builder.append(", inscricaoEstadual=");
	builder.append(inscricaoEstadual);
	builder.append(", nomeRazao=");
	builder.append(nomeRazao);
	builder.append(", logradouro=");
	builder.append(logradouro);
	builder.append(", numero=");
	builder.append(numero);
	builder.append(", complemento=");
	builder.append(complemento);
	builder.append(", cep=");
	builder.append(cep);
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
