package com.pessoa.spring_app_gradle.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name ="TB_FUNCIONARIO")
@ApiModel(value = "Funcionário", description = " ")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private long id;
	
	@ApiModelProperty(position=1)
	@Column(nullable = false)
	@NotEmpty(message="Nome: Não pode ser vazio!!")	
	@Length(min=3,max=55)
	@NotNull(message="Nome: Não pode ser Nulo.")
	private String nome;
	
	//@DateTimeFormat(pattern = "dd/MM/yyyy kk:mm")
	@Column(name="dataNascimento",nullable = false)	
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(position=2)
	@NotNull(message="Data: Não pode ser nula.")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento = new Date();
	
	@Column(nullable = false)
	@Size(min=1,max=9,message = "Tamanho de sexo")
	@Length(min=8,max = 9, message = "Sexo tem um limite do nome do Sexo.")
	@NotEmpty(message="Sexo: Não pode ser vazio!!")	
	@NotNull(message="Sexo: Não pode ser Nulo.")
	@ApiModelProperty(position=3)
	private String sexo;
		
	@Column(nullable = false)
	@CPF
	@ApiModelProperty(position=4)
	@NotEmpty(message="CPF: Não pode ser vazio!!")	
	@NotNull(message="CPF: Não pode ser Nulo.")
	private String cpf;
	
	
	@ManyToOne
	@JoinColumn(name="cargo_id")
	@ApiModelProperty(position=5)
	@NotNull(message="Cargo: Não pode ser Nulo.")
	private Cargo cargo;

	
	@Column(nullable = false)
	@ApiModelProperty(position=6)
	@NotEmpty(message="RG: Não pode ser vazia!!")	
	@NotNull(message="RG: Não pode ser Nulo.")
	@Length(max = 7, message = "RG: tem um limite de 7 números.")
	private String rg;

	@OneToMany(fetch=FetchType.LAZY)
//	@JoinColumn(name="atividade_epi_id")
	@ApiModelProperty(position=7)
	@NotEmpty(message="Lista: Não pode está vazio.")
	private List<EpiAtividade> listaEpiAtividades;
	
	@Column(nullable = false)
	@ApiModelProperty(position=8)
	@NotNull(message="usaEpi: Não pode ser Nulo.")
	private boolean usaEpi;
	
	@Basic(fetch=FetchType.LAZY)
	@Column(nullable = true)
	@Lob
	@ApiModelProperty(position=9)
	@NotEmpty(message="Foto: Não pode ser vazia!!")	
	@NotNull(message="Foto: Não pode ser Nulo.")
	private String foto;

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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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


	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}


	public boolean isUsaEpi() {
		return usaEpi;
	}
	
	public void setUsaEpi(boolean usaEpi) {
		this.usaEpi = usaEpi;
	}

	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Funcionario() {
		super();
	}

	public List<EpiAtividade> getListaEpiAtividades() {
		return listaEpiAtividades;
	}

	public void setListaEpiAtividades(List<EpiAtividade> listaEpiAtividades) {
		this.listaEpiAtividades = listaEpiAtividades;
	}

}
