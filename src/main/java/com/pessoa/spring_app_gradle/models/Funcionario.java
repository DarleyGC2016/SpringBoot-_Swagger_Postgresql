package com.pessoa.spring_app_gradle.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name ="TB_Funcionario")
@ApiModel(value = "Funcionário", description = " ")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private long id;
	
	@ApiModelProperty(position=1)
	@Column(nullable = false)
	@NotEmpty(message="Nome não pode ser vazio!!")	
	private String nome;
	
	@Column(name="Data_Aniv",nullable = false)	
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(position=2)
	private Date dataAniv;
	
	@Column(nullable = false)
	@Size(min=8,max=9,message = "Tamanho de sexo")
	@ApiModelProperty(position=3)
	private String sexo;
		
	@Column(nullable = false)
	@CPF
	@ApiModelProperty(position=4)
	private String cpf;
	
	@Column(nullable = false) 
	@Size(min=2,max=50,message = "Tamanho de Cargo")
	@ApiModelProperty(position=5)
	private String cargo;
	
	@Column(nullable = false)
	@ApiModelProperty(position=6)
	private String rg;

	@Column(nullable = true)
	@Size(min=2,max=50,message = "Tamanho de atividade")
	@ApiModelProperty(position=7)
	private String atividade;
	
	/**
	 * @Column com o parametro nullable está habilitado para ser nulo.
	 * @Size para validar epi.
	 * */	
	@Column(nullable = true)
	@Size(min=2,max=50,message = "Tamanho de epi")
	@ApiModelProperty(position=8)
	private String epi;
	/**
	 * @Column com o parametro nullable está habilitado para ser nulo.
	 * @Min e @Max para validar 'ca'.
	 * */
	@Column(nullable = true)
	@Min(value = 0, message = "Não usa CA")
    @Max(value = 9999, message = "CA só podera ter 4 número no máximo ")
	@ApiModelProperty(position=9)
	private long ca;
	
	@Column(nullable = false)
	@ApiModelProperty(position=10)
	private boolean usaEpi;
	
	@Basic(fetch=FetchType.LAZY)
	@Column(nullable = false)
	@Lob 
	@ApiModelProperty(position=11)
	private byte[] foto;

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataAniv() {
		return dataAniv;
	}

	public void setDataAniv(Date dataAniv) {
		this.dataAniv = dataAniv;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public String getEpi() {
		return epi;
	}

	public void setEpi(String epi) {
		this.epi = epi;
	}

	public long getCa() {
		return ca;
	}

	public void setCa(long ca) {
		this.ca = ca;
	}

	public boolean isUsaEpi() {
		return usaEpi;
	}
	
	public void setUsaEpi(boolean usaEpi) {
		this.usaEpi = usaEpi;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public Funcionario() {
		super();
	}
	
	
	
	
}
