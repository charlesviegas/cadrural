package br.com.codate.cadrural.produtor.ent;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.ForeignKey;

import br.com.codate.cadrural.produtor.enuns.EstadoEnum;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "TB_FAZENDA")
@SequenceGenerator(name = "SEQ_FAZENDA", sequenceName = "SEQ_FAZENDA", allocationSize = 1, initialValue = 1)
@NamedQueries({
        @NamedQuery(name = Fazenda.BUSCA_FAZENDA_POR_PRODUTOR, query = "SELECT new Fazenda(f.id, f.nome) FROM Fazenda f INNER JOIN f.produtor p WHERE p.id = ?1"),
        @NamedQuery(name = Fazenda.EXCLUI_FAZENDA_POR_ID, query = "DELETE FROM Fazenda f WHERE f.id = ?1"),
        @NamedQuery(name = Fazenda.EXCLUI_FAZENDA_POR_PRODUTOR, query = "DELETE FROM Fazenda f WHERE f.produtor.id = ?1") })
public class Fazenda implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String BUSCA_FAZENDA_POR_PRODUTOR = "BUSCA_FAZENDA_POR_PRODUTOR";
    public static final String EXCLUI_FAZENDA_POR_ID = "EXCLUI_FAZENDA_POR_ID";
    public static final String EXCLUI_FAZENDA_POR_PRODUTOR = "EXCLUI_FAZENDA_POR_PRODUTOR";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_FAZENDA")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "AREA", precision = 10, scale = 2)
    private BigDecimal area;

    @Column(name = "MATRICULA", nullable = false, length = 50)
    private String matricula;

    @Column(name = "MUNICIPIO", nullable = false, length = 50)
    private String municipio;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false, length = 50)
    private EstadoEnum estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PRODUTOR", nullable = false)
    @ForeignKey(name = "FK_FAZENDA_PRODUTOR")
    public Produtor produtor;

    public Fazenda() {
	super();
    }

    public Fazenda(Long id, String nome) {
	super();
	this.id = id;
	this.nome = nome;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public BigDecimal getArea() {
	return area;
    }

    public void setArea(BigDecimal area) {
	this.area = area;
    }

    public String getMatricula() {
	return matricula;
    }

    public void setMatricula(String matricula) {
	this.matricula = matricula;
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

    public Produtor getProdutor() {
	return produtor;
    }

    public void setProdutor(Produtor produtor) {
	this.produtor = produtor;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Fazenda [id=");
	builder.append(id);
	builder.append(", nome=");
	builder.append(nome);
	builder.append(", area=");
	builder.append(area);
	builder.append(", matricula=");
	builder.append(matricula);
	builder.append(", municipio=");
	builder.append(municipio);
	builder.append(", estado=");
	builder.append(estado);
	builder.append("]");
	return builder.toString();
    }
}
