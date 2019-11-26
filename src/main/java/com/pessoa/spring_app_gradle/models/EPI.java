package com.pessoa.spring_app_gradle.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "TB_EPI")
@ApiModel(value = "EPI", description = " ")
public class EPI implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;
	
	@Column(nullable = true)
	@Size(min = 1, max = 50, message = "Descrição do EPI")
	@Length(min=3,max=55)
	@NotEmpty(message="Descrição: Não pode ser vazio!!")	
	@ApiModelProperty(position = 1)
	private String descricao;
	/**
	 * @Column com o parametro nullable está habilitado para ser nulo.
	 * @Min e @Max para validar 'ca'.
	 * */
	@Column(nullable = true)
	@Min(value = 0, message = "Não usa CA")
    @Max(value = 9999, message = "CA só podera ter 4 número no máximo ")	
	@Range(min= 0, max=9999)
	@Digits(integer=4, fraction = 0)
	@ApiModelProperty(position=2)
	private Long ca;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCa() {
		return ca;
	}

	public void setCa(Long ca) {
		this.ca = ca;
	}

	public EPI() {
	}
	
	
}
